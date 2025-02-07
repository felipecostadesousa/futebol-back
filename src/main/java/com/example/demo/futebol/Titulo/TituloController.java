package com.example.demo.futebol.Titulo;

import java.util.List;
import java.util.Optional;

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

@RestController
@RequestMapping("/titulo")
public class TituloController {

private static final Logger LOGGER = LoggerFactory.getLogger(TituloController.class);

private TituloService service;

    public TituloController(TituloService service) {
        this.service = service;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> save(@RequestBody TituloRequest request){
        LOGGER.info("Iniciando criação de um novo Titulo");

        if(request == null){
            return ResponseEntity.badRequest().body("Não há dados para serem cadastrados");
        }

        Titulo object = request.transform(request.getNome());
        service.save(object);
        return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
    }

    @GetMapping(produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAll() {
        LOGGER.info("Buscando lista de Titulo");
        List<Titulo> list = service.findAll();
        return ResponseEntity.ok().header("Custom-Header", "foo").body(list);
    }

    @GetMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<?> findById(@PathVariable("id") Integer id){
        LOGGER.info("Buscando Titulo por id: {}", id);
        if(id == null){
            return ResponseEntity.badRequest().body("id inválido");
        }

        Optional<Titulo> possivelTitulo = service.findById(id);
        if (possivelTitulo.isPresent()) {
            LOGGER.info("Titulo encontrado com sucesso: {}");
            return ResponseEntity.ok().header("Custom-header", "foo").body(possivelTitulo.get());
        }else{
            LOGGER.info("Titulo não encontrado pelo id: {}", id);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }

           
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@RequestBody TituloRequest request) {
        LOGGER.info("Iniciando atualização de Titulo pelo id: {}", request.getId());

        Optional<Titulo> possivelTitulo = service.findById(request.getId());

        Titulo titulo = possivelTitulo.get();
        titulo.transform(request);
        LOGGER.info("Titulo encontrado com sucesso (controller): {}");
        service.update(titulo);
        LOGGER.info("Titulo atualizado(a) com sucesso: {}");
        return ResponseEntity.ok().header("Custom-Header", "foo").body(titulo);

    }


    @DeleteMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        LOGGER.info("Iniciando deleção de Titulo pelo id: {}", id);
        Optional<Titulo> possivelTitulo= service.findById(id);
        if (possivelTitulo.isPresent()) {
            Titulo titulo = possivelTitulo.get();
            service.delete(titulo);
            LOGGER.info("Titulo deletado(a) com sucesso: {}");
            return ResponseEntity.ok().header("Custom-Header", "foo").body(titulo);
        }else{
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }

}
