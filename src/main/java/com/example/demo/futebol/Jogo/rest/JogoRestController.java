
package com.example.demo.futebol.Jogo.rest;

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

                                        
import org.demo.Estadio;
import org.demo.EstadioService;

import org.demo.Jogo;
import org.demo.JogoService;


@RestController
@RequestMapping("/estadio")
public class JogoRestController {

    private JogoService service;
    private EstadioService estadioService;
    private static final Logger LOGGER = LoggerFactory.getLogger(JogoRestController.class);

    public JogoRestController(JogoService service, EstadioService estadioService) {
        this.service = service;
        this.estadioService = estadioService;
    }

    @PostMapping(value="/{id_estadio}/Jogo", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<?> save(@PathVariable("id_estadio") Long idEstadio, @RequestBody JogoRequest request){
        LOGGER.info("Iniciando criação de um novo Jogo para Estadio com ID: {}", idEstadio);
        if (idEstadio == null){
            return ResponseEntity.badRequest().body("idEstadio está inválido");
        }

        Set< String > validations = request.isValidOnStage(ValidOnCreation.class);
        if(request == null){
            return ResponseEntity.badRequest().body("Não há dados para serem cadastrados");
        }
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        Optional<Estadio> possivelEstadio = this.estadioService.findById(idEstadio);
        if(possivelEstadio.isPresent()){
            Estadio estadio = possivelEstadio.get();
            Jogo object = request.transform(estadio);
            service.save(object);
            LOGGER.info("Jogo criado para Estadio com sucesso!");
            return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
        }
        else{
            LOGGER.info("estadio não encontrado com ID: {}", idEstadio);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(idEstadio)).build();
        }

    }

    @GetMapping(value="/{id_estadio}/Jogo", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAll(@PathVariable("id_estadio") Long idEstadio) {
        LOGGER.info("Buscando lista de Jogo");

        if (idEstadio == null){
            return ResponseEntity.badRequest().body("idEstadio está inválido");
        }

        List<Jogo> list = service.findAll();
        LOGGER.info("Jogo encontrados: {}", list.size());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(list);
    }

    @GetMapping(value="/{id_estadio}/Jogo/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findById(@PathVariable("id_estadio") Long idEstadio, @PathVariable("id") Long id){
        LOGGER.info("Buscando Jogo por id: {}", id);

        if (idEstadio == null){
            return ResponseEntity.badRequest().body("idEstadio está inválido");
        }

        if(id == null){
            return ResponseEntity.badRequest().body("id inválido");
        }

        Optional<Jogo> object = service.findById(id);
        if (object.isPresent()) {
            LOGGER.info("Jogo encontrado com sucesso: {}");
            return ResponseEntity.ok().header("Custom-header", "foo").body(object.get());
        }else{
            LOGGER.info("Jogo não encontrado pelo id: {}", id);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }
               
    @PutMapping(value="/{id_estadio}/Jogo", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@PathVariable("id_estadio") Long idEstadio, @RequestBody JogoRequest request) {
        LOGGER.info("Iniciando atualização de Jogo pelo id: {}", request.getId());

        Set< String > validations = request.isValidOnStage(ValidOnUpdate.class);
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        Optional<Estadio> possivelEstadio = this.estadioService.findById(idEstadio);
        Optional<Jogo> possivelObject = service.findById(request.getId());
        if(possivelObject.isPresent()){
            Jogo object = request.transform(possivelEstadio.get());
            service.update(object);
            LOGGER.info("Jogo atualizado(a) com sucesso: {}", object.logString());
            return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
        } else{
            LOGGER.info("Jogo não encontrado(a) pelo id: {}", request.getId());
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(request.getId())).build();
        }
    }


    @DeleteMapping(value="/{id_estadio}/Jogo/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id_estadio") Long idEstadio, @PathVariable("id") Long id) {
        LOGGER.info("Iniciando deleção de Jogo pelo id: {}", id);
        Optional<Jogo> possivelObject = service.findById(id);
        Jogo object = possivelObject.get();
        service.delete(object);
        LOGGER.info("Jogo deletado(a) com sucesso: {}", object.logString());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
    }

}


