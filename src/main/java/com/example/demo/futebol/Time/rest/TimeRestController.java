package com.example.demo.futebol.Time.rest;


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

import org.demo.Time;
import org.demo.TimeService;


@RestController
@RequestMapping("/localizacao")
public class TimeRestController {

    private TimeService service;
    private LocalizacaoService localizacaoService;
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeRestController.class);

    public TimeRestController(TimeService service, LocalizacaoService localizacaoService) {
        this.service = service;
        this.localizacaoService = localizacaoService;
    }

    @PostMapping(value="/{id_localizacao}/Time", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<?> save(@PathVariable("id_localizacao") Long idLocalizacao, @RequestBody TimeRequest request){
        LOGGER.info("Iniciando criação de um novo Time para Localizacao com ID: {}", idLocalizacao);
        if (idLocalizacao == null){
            return ResponseEntity.badRequest().body("idLocalizacao está inválido");
        }

        Set< String > validations = request.isValidOnStage(ValidOnCreation.class);
        if(request == null){
            return ResponseEntity.badRequest().body("Não há dados para serem cadastrados");
        }
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        Optional<Localizacao> possivelLocalizacao = this.localizacaoService.findById(idLocalizacao);
        if(possivelLocalizacao.isPresent()){
            Localizacao localizacao = possivelLocalizacao.get();
            Time object = request.transform(localizacao);
            service.save(object);
            LOGGER.info("Time criado para Localizacao com sucesso!");
            return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
        }
        else{
            LOGGER.info("localizacao não encontrado com ID: {}", idLocalizacao);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(idLocalizacao)).build();
        }

    }

    @GetMapping(value="/{id_localizacao}/Time", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAll(@PathVariable("id_localizacao") Long idLocalizacao) {
        LOGGER.info("Buscando lista de Time");

        if (idLocalizacao == null){
            return ResponseEntity.badRequest().body("idLocalizacao está inválido");
        }

        List<Time> list = service.findAll();
        LOGGER.info("Time encontrados: {}", list.size());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(list);
    }

    @GetMapping(value="/{id_localizacao}/Time/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findById(@PathVariable("id_localizacao") Long idLocalizacao, @PathVariable("id") Long id){
        LOGGER.info("Buscando Time por id: {}", id);

        if (idLocalizacao == null){
            return ResponseEntity.badRequest().body("idLocalizacao está inválido");
        }

        if(id == null){
            return ResponseEntity.badRequest().body("id inválido");
        }

        Optional<Time> object = service.findById(id);
        if (object.isPresent()) {
            LOGGER.info("Time encontrado com sucesso: {}");
            return ResponseEntity.ok().header("Custom-header", "foo").body(object.get());
        }else{
            LOGGER.info("Time não encontrado pelo id: {}", id);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }
                       
    @PutMapping(value="/{id_localizacao}/Time", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@PathVariable("id_localizacao") Long idLocalizacao, @RequestBody TimeRequest request) {
        LOGGER.info("Iniciando atualização de Time pelo id: {}", request.getId());

        Set< String > validations = request.isValidOnStage(ValidOnUpdate.class);
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        Optional<Localizacao> possivelLocalizacao = this.localizacaoService.findById(idLocalizacao);
        Optional<Time> possivelObject = service.findById(request.getId());
        if(possivelObject.isPresent()){
            Time object = request.transform(possivelLocalizacao.get());
            service.update(object);
            LOGGER.info("Time atualizado(a) com sucesso: {}", object.logString());
            return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
        } else{
            LOGGER.info("Time não encontrado(a) pelo id: {}", request.getId());
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(request.getId())).build();
        }
    }


    @DeleteMapping(value="/{id_localizacao}/Time/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id_localizacao") Long idLocalizacao, @PathVariable("id") Long id) {
        LOGGER.info("Iniciando deleção de Time pelo id: {}", id);
        Optional<Time> possivelObject = service.findById(id);
        Time object = possivelObject.get();
        service.delete(object);
        LOGGER.info("Time deletado(a) com sucesso: {}", object.logString());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
    }

}


