package com.example.demo.futebol.Competicao.rest;


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

import org.demo.Competicao;
import org.demo.CompeticaoService;
        
            
@RestController
@RequestMapping("/competicao")
public class CompeticaoRestController {

private static final Logger LOGGER = LoggerFactory.getLogger(CompeticaoRestController.class);

private CompeticaoService service;

    public CompeticaoRestController(CompeticaoService service) {
        this.service = service;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> save(@RequestBody CompeticaoRequest request){
        LOGGER.info("Iniciando criação de um novo Competicao");

        Set< String > validations = request.isValidOnStage(ValidOnCreation.class);

        if(request == null){
            return ResponseEntity.badRequest().body("Não há dados para serem cadastrados");
        }

        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        Competicao object = request.transform(
                 request.getNome(),         request.getAno(),  request.getConfederacao()  );
        service.save(object);
        return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
    }

    @GetMapping(produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAll() {
        LOGGER.info("Buscando lista de Competicao");
        List<Competicao> list = service.findAll();
        return ResponseEntity.ok().header("Custom-Header", "foo").body(list);
    }

    @GetMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<?> findById(@PathVariable("id") Long id){
        LOGGER.info("Buscando Competicao por id: {}", id);
        if(id == null){
            return ResponseEntity.badRequest().body("id inválido");
        }

        Optional<Competicao> object = service.findById(id);
        if (object.isPresent()) {
            LOGGER.info("Competicao encontrado com sucesso: {}");
            return ResponseEntity.ok().header("Custom-header", "foo").body(object.get());
        }else{
            LOGGER.info("Competicao não encontrado pelo id: {}", id);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }

                 
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@RequestBody CompeticaoRequest request) {
        LOGGER.info("Iniciando atualização de Competicao pelo id: {}", request.getId());

        Set<String> validations = request.isValidOnStage(ValidOnUpdate.class);
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        Optional<Competicao> possivelObject = service.findById(request.getId());

        Competicao object = possivelObject.get();
        object.transform(request);
        LOGGER.info("Competicao encontrado com sucesso (controller): {}", object.logString());
        service.update(object);
        LOGGER.info("Competicao atualizado(a) com sucesso: {}", object.logString());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(object);

    }


    @DeleteMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        LOGGER.info("Iniciando deleção de Competicao pelo id: {}", id);
        Optional<Competicao> possivelObject = service.findById(id);
        if (possivelObject.isPresent()) {
            Competicao object = possivelObject.get();
            service.delete(object);
            LOGGER.info("Competicao deletado(a) com sucesso: {}", object.logString());
            return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
        }else{
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }

}
