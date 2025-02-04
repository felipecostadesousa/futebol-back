package com.example.demo.futebol.Jogdor.rest;


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

                        
import org.demo.Time;
import org.demo.TimeService;

import org.demo.Jogador;
import org.demo.JogadorService;


@RestController
@RequestMapping("/time")
public class JogadorRestController {

    private JogadorService service;
    private TimeService timeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(JogadorRestController.class);

    public JogadorRestController(JogadorService service, TimeService timeService) {
        this.service = service;
        this.timeService = timeService;
    }

    @PostMapping(value="/{id_time}/Jogador", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<?> save(@PathVariable("id_time") Long idTime, @RequestBody JogadorRequest request){
        LOGGER.info("Iniciando criação de um novo Jogador para Time com ID: {}", idTime);
        if (idTime == null){
            return ResponseEntity.badRequest().body("idTime está inválido");
        }

        Set< String > validations = request.isValidOnStage(ValidOnCreation.class);
        if(request == null){
            return ResponseEntity.badRequest().body("Não há dados para serem cadastrados");
        }
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        Optional<Time> possivelTime = this.timeService.findById(idTime);
        if(possivelTime.isPresent()){
            Time time = possivelTime.get();
            Jogador object = request.transform(time);
            service.save(object);
            LOGGER.info("Jogador criado para Time com sucesso!");
            return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
        }
        else{
            LOGGER.info("time não encontrado com ID: {}", idTime);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(idTime)).build();
        }

    }

    @GetMapping(value="/{id_time}/Jogador", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAll(@PathVariable("id_time") Long idTime) {
        LOGGER.info("Buscando lista de Jogador");

        if (idTime == null){
            return ResponseEntity.badRequest().body("idTime está inválido");
        }

        List<Jogador> list = service.findAll();
        LOGGER.info("Jogador encontrados: {}", list.size());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(list);
    }

    @GetMapping(value="/{id_time}/Jogador/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findById(@PathVariable("id_time") Long idTime, @PathVariable("id") Long id){
        LOGGER.info("Buscando Jogador por id: {}", id);

        if (idTime == null){
            return ResponseEntity.badRequest().body("idTime está inválido");
        }

        if(id == null){
            return ResponseEntity.badRequest().body("id inválido");
        }

        Optional<Jogador> object = service.findById(id);
        if (object.isPresent()) {
            LOGGER.info("Jogador encontrado com sucesso: {}");
            return ResponseEntity.ok().header("Custom-header", "foo").body(object.get());
        }else{
            LOGGER.info("Jogador não encontrado pelo id: {}", id);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }
                           
    @PutMapping(value="/{id_time}/Jogador", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@PathVariable("id_time") Long idTime, @RequestBody JogadorRequest request) {
        LOGGER.info("Iniciando atualização de Jogador pelo id: {}", request.getId());

        Set< String > validations = request.isValidOnStage(ValidOnUpdate.class);
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        Optional<Time> possivelTime = this.timeService.findById(idTime);
        Optional<Jogador> possivelObject = service.findById(request.getId());
        if(possivelObject.isPresent()){
            Jogador object = request.transform(possivelTime.get());
            service.update(object);
            LOGGER.info("Jogador atualizado(a) com sucesso: {}", object.logString());
            return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
        } else{
            LOGGER.info("Jogador não encontrado(a) pelo id: {}", request.getId());
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(request.getId())).build();
        }
    }


    @DeleteMapping(value="/{id_time}/Jogador/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id_time") Long idTime, @PathVariable("id") Long id) {
        LOGGER.info("Iniciando deleção de Jogador pelo id: {}", id);
        Optional<Jogador> possivelObject = service.findById(id);
        Jogador object = possivelObject.get();
        service.delete(object);
        LOGGER.info("Jogador deletado(a) com sucesso: {}", object.logString());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
    }

}


