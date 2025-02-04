package com.example.demo.futebol.Localizacao.rest;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.demo.Localizacao;
import org.demo.LocalizacaoService;
        
            
@RestController
@RequestMapping("/localizacao")
public class LocalizacaoRestController {

private static final Logger LOGGER = LoggerFactory.getLogger(LocalizacaoRestController.class);

private LocalizacaoService service;

    public LocalizacaoRestController(LocalizacaoService service) {
        this.service = service;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> save(@RequestBody LocalizacaoRequest request){
        LOGGER.info("Iniciando criação de um novo Localizacao");

        Set< String > validations = request.isValidOnStage(ValidOnCreation.class);

        if(request == null){
            return ResponseEntity.badRequest().body("Não há dados para serem cadastrados");
        }

        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        Localizacao object = request.transform(
                 request.getPais(),         request.getRegiao(),         request.getEstado(),  request.getCidade()  );
        service.save(object);
        return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
    }

    @GetMapping(produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAll() {
        LOGGER.info("Buscando lista de Localizacao");
        List<Localizacao> list = service.findAll();
        return ResponseEntity.ok().header("Custom-Header", "foo").body(list);
    }

    @GetMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<?> findById(@PathVariable("id") Long id){
        LOGGER.info("Buscando Localizacao por id: {}", id);
        if(id == null){
            return ResponseEntity.badRequest().body("id inválido");
        }

        Optional<Localizacao> object = service.findById(id);
        if (object.isPresent()) {
            LOGGER.info("Localizacao encontrado com sucesso: {}");
            return ResponseEntity.ok().header("Custom-header", "foo").body(object.get());
        }else{
            LOGGER.info("Localizacao não encontrado pelo id: {}", id);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }

                    
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@RequestBody LocalizacaoRequest request) {
        LOGGER.info("Iniciando atualização de Localizacao pelo id: {}", request.getId());

        Set<String> validations = request.isValidOnStage(ValidOnUpdate.class);
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        Optional<Localizacao> possivelObject = service.findById(request.getId());

        Localizacao object = possivelObject.get();
        object.transform(request);
        LOGGER.info("Localizacao encontrado com sucesso (controller): {}", object.logString());
        service.update(object);
        LOGGER.info("Localizacao atualizado(a) com sucesso: {}", object.logString());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(object);

    }


    @DeleteMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        LOGGER.info("Iniciando deleção de Localizacao pelo id: {}", id);
        Optional<Localizacao> possivelObject = service.findById(id);
        if (possivelObject.isPresent()) {
            Localizacao object = possivelObject.get();
            service.delete(object);
            LOGGER.info("Localizacao deletado(a) com sucesso: {}", object.logString());
            return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
        }else{
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }

}
