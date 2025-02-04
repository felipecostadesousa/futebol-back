package com.example.demo.futebol.JogoTime.rest;


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

import org.demo.JogoTime;
import org.demo.JogoTimeService;


@RestController
@RequestMapping("/time")
public class JogoTimeRestController {

    private JogoTimeService service;
    private TimeService timeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(JogoTimeRestController.class);

    public JogoTimeRestController(JogoTimeService service, TimeService timeService) {
        this.service = service;
        this.timeService = timeService;
    }

    @PostMapping(value="/{id_time}/JogoTime", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<?> save(@PathVariable("id_time") Long idTime, @RequestBody JogoTimeRequest request){
        LOGGER.info("Iniciando criação de um novo JogoTime para Time com ID: {}", idTime);
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
            JogoTime object = request.transform(time);
            service.save(object);
            LOGGER.info("JogoTime criado para Time com sucesso!");
            return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
        }
        else{
            LOGGER.info("time não encontrado com ID: {}", idTime);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(idTime)).build();
        }

    }

    @GetMapping(value="/{id_time}/JogoTime", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAll(@PathVariable("id_time") Long idTime) {
        LOGGER.info("Buscando lista de JogoTime");

        if (idTime == null){
            return ResponseEntity.badRequest().body("idTime está inválido");
        }

        List<JogoTime> list = service.findAll();
        LOGGER.info("JogoTime encontrados: {}", list.size());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(list);
    }

    @GetMapping(value="/{id_time}/JogoTime/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findById(@PathVariable("id_time") Long idTime, @PathVariable("id") Long id){
        LOGGER.info("Buscando JogoTime por id: {}", id);

        if (idTime == null){
            return ResponseEntity.badRequest().body("idTime está inválido");
        }

        if(id == null){
            return ResponseEntity.badRequest().body("id inválido");
        }

        Optional<JogoTime> object = service.findById(id);
        if (object.isPresent()) {
            LOGGER.info("JogoTime encontrado com sucesso: {}");
            return ResponseEntity.ok().header("Custom-header", "foo").body(object.get());
        }else{
            LOGGER.info("JogoTime não encontrado pelo id: {}", id);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }
       
    @PutMapping(value="/{id_time}/JogoTime", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@PathVariable("id_time") Long idTime, @RequestBody JogoTimeRequest request) {
        LOGGER.info("Iniciando atualização de JogoTime pelo id: {}", request.getId());

        Set< String > validations = request.isValidOnStage(ValidOnUpdate.class);
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        Optional<Time> possivelTime = this.timeService.findById(idTime);
        Optional<JogoTime> possivelObject = service.findById(request.getId());
        if(possivelObject.isPresent()){
            JogoTime object = request.transform(possivelTime.get());
            service.update(object);
            LOGGER.info("JogoTime atualizado(a) com sucesso: {}", object.logString());
            return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
        } else{
            LOGGER.info("JogoTime não encontrado(a) pelo id: {}", request.getId());
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(request.getId())).build();
        }
    }


    @DeleteMapping(value="/{id_time}/JogoTime/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id_time") Long idTime, @PathVariable("id") Long id) {
        LOGGER.info("Iniciando deleção de JogoTime pelo id: {}", id);
        Optional<JogoTime> possivelObject = service.findById(id);
        JogoTime object = possivelObject.get();
        service.delete(object);
        LOGGER.info("JogoTime deletado(a) com sucesso: {}", object.logString());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
    }

}


