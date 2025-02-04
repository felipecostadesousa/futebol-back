
package com.example.demo.futebol.Arbitro.rest;


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

import org.demo.Arbitro;
import org.demo.ArbitroService;
        
            
@RestController
@RequestMapping("/arbitro")
public class ArbitroRestController {

private static final Logger LOGGER = LoggerFactory.getLogger(ArbitroRestController.class);

private ArbitroService service;

    public ArbitroRestController(ArbitroService service) {
        this.service = service;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> save(@RequestBody ArbitroRequest request){
        LOGGER.info("Iniciando criação de um novo Arbitro");

        Set< String > validations = request.isValidOnStage(ValidOnCreation.class);

        if(request == null){
            return ResponseEntity.badRequest().body("Não há dados para serem cadastrados");
        }

        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        Arbitro arbitro = request.transform( request.getContratoInicio(), request.getNome(), request.getApelido(), request.getDataNascimento(),
                request.getNacionaliade(), request.getImagem()  );
        service.save(arbitro);
        return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
    }

    @GetMapping(produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAll() {
        LOGGER.info("Buscando lista de Arbitro");
        List<Arbitro> list = service.findAll();
        return ResponseEntity.ok().header("Custom-Header", "foo").body(list);
    }

    @GetMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<?> findById(@PathVariable("id") Long id){
        LOGGER.info("Buscando Arbitro por id: {}", id);
        if(id == null){
            return ResponseEntity.badRequest().body("id inválido");
        }

        Optional<Arbitro> object = service.findById(id);
        if (object.isPresent()) {
            LOGGER.info("Arbitro encontrado com sucesso: {}");
            return ResponseEntity.ok().header("Custom-header", "foo").body(object.get());
        }else{
            LOGGER.info("Arbitro não encontrado pelo id: {}", id);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@RequestBody ArbitroRequest request) {
        LOGGER.info("Iniciando atualização de Arbitro pelo id: {}", request.getId());

        Set<String> validations = request.isValidOnStage(ValidOnUpdate.class);
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        Optional<Arbitro> possivelObject = service.findById(request.getId());

        Arbitro object = possivelObject.get();
        object.transform(request);
        LOGGER.info("Arbitro encontrado com sucesso (controller): {}", object.logString());
        service.update(object);
        LOGGER.info("Arbitro atualizado(a) com sucesso: {}", object.logString());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
    }

    @DeleteMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        LOGGER.info("Iniciando deleção de Arbitro pelo id: {}", id);
        Optional<Arbitro> possivelObject = service.findById(id);
        if (possivelObject.isPresent()) {
            Arbitro object = possivelObject.get();
            service.delete(object);
            LOGGER.info("Arbitro deletado(a) com sucesso: {}", object.logString());
            return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
        }else{
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }

}
