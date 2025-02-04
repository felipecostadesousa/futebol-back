package com.example.demo.futebol.JogadorTitulo.rest;


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

                                
import org.demo.Titulo;
import org.demo.TituloService;

import org.demo.JogadorTitulo;
import org.demo.JogadorTituloService;


@RestController
@RequestMapping("/titulo")
public class JogadorTituloRestController {

    private JogadorTituloService service;
    private TituloService tituloService;
    private static final Logger LOGGER = LoggerFactory.getLogger(JogadorTituloRestController.class);

    public JogadorTituloRestController(JogadorTituloService service, TituloService tituloService) {
        this.service = service;
        this.tituloService = tituloService;
    }

    @PostMapping(value="/{id_titulo}/JogadorTitulo", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<?> save(@PathVariable("id_titulo") Long idTitulo, @RequestBody JogadorTituloRequest request){
        LOGGER.info("Iniciando criação de um novo JogadorTitulo para Titulo com ID: {}", idTitulo);
        if (idTitulo == null){
            return ResponseEntity.badRequest().body("idTitulo está inválido");
        }

        Set< String > validations = request.isValidOnStage(ValidOnCreation.class);
        if(request == null){
            return ResponseEntity.badRequest().body("Não há dados para serem cadastrados");
        }
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        Optional<Titulo> possivelTitulo = this.tituloService.findById(idTitulo);
        if(possivelTitulo.isPresent()){
            Titulo titulo = possivelTitulo.get();
            JogadorTitulo object = request.transform(titulo);
            service.save(object);
            LOGGER.info("JogadorTitulo criado para Titulo com sucesso!");
            return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
        }
        else{
            LOGGER.info("titulo não encontrado com ID: {}", idTitulo);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(idTitulo)).build();
        }

    }

    @GetMapping(value="/{id_titulo}/JogadorTitulo", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAll(@PathVariable("id_titulo") Long idTitulo) {
        LOGGER.info("Buscando lista de JogadorTitulo");

        if (idTitulo == null){
            return ResponseEntity.badRequest().body("idTitulo está inválido");
        }

        List<JogadorTitulo> list = service.findAll();
        LOGGER.info("JogadorTitulo encontrados: {}", list.size());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(list);
    }

    @GetMapping(value="/{id_titulo}/JogadorTitulo/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findById(@PathVariable("id_titulo") Long idTitulo, @PathVariable("id") Long id){
        LOGGER.info("Buscando JogadorTitulo por id: {}", id);

        if (idTitulo == null){
            return ResponseEntity.badRequest().body("idTitulo está inválido");
        }

        if(id == null){
            return ResponseEntity.badRequest().body("id inválido");
        }

        Optional<JogadorTitulo> object = service.findById(id);
        if (object.isPresent()) {
            LOGGER.info("JogadorTitulo encontrado com sucesso: {}");
            return ResponseEntity.ok().header("Custom-header", "foo").body(object.get());
        }else{
            LOGGER.info("JogadorTitulo não encontrado pelo id: {}", id);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }
       
    @PutMapping(value="/{id_titulo}/JogadorTitulo", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@PathVariable("id_titulo") Long idTitulo, @RequestBody JogadorTituloRequest request) {
        LOGGER.info("Iniciando atualização de JogadorTitulo pelo id: {}", request.getId());

        Set< String > validations = request.isValidOnStage(ValidOnUpdate.class);
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        Optional<Titulo> possivelTitulo = this.tituloService.findById(idTitulo);
        Optional<JogadorTitulo> possivelObject = service.findById(request.getId());
        if(possivelObject.isPresent()){
            JogadorTitulo object = request.transform(possivelTitulo.get());
            service.update(object);
            LOGGER.info("JogadorTitulo atualizado(a) com sucesso: {}", object.logString());
            return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
        } else{
            LOGGER.info("JogadorTitulo não encontrado(a) pelo id: {}", request.getId());
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(request.getId())).build();
        }
    }


    @DeleteMapping(value="/{id_titulo}/JogadorTitulo/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id_titulo") Long idTitulo, @PathVariable("id") Long id) {
        LOGGER.info("Iniciando deleção de JogadorTitulo pelo id: {}", id);
        Optional<JogadorTitulo> possivelObject = service.findById(id);
        JogadorTitulo object = possivelObject.get();
        service.delete(object);
        LOGGER.info("JogadorTitulo deletado(a) com sucesso: {}", object.logString());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
    }

}


