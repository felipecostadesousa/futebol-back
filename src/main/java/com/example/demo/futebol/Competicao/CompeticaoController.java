package com.example.demo.futebol.Competicao;

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

@RestController
@RequestMapping("//=competicao")
public class CompeticaoController {

private static final Logger LOGGER = LoggerFactory.getLogger(CompeticaoController.class);

private CompeticaoService service;

    public CompeticaoController(CompeticaoService service) {
        this.service = service;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> save(@RequestBody CompeticaoRequest request){
        LOGGER.info("Iniciando criação de um novo Competicao");

        if(request == null){
            return ResponseEntity.badRequest().body("Não há dados para serem cadastrados");
        }

        Competicao competicao = request.transform( request.getNome(), request.getAno(), request.getConfederacao());
        service.save(competicao);
        return ResponseEntity.ok().header("Custom-Header", "foo").body(competicao);
    }

    @GetMapping(produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAll() {
        LOGGER.info("Buscando lista de Competicao");
        List<Competicao> list = service.findAll();
        return ResponseEntity.ok().header("Custom-Header", "foo").body(list);
    }

    @GetMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<?> findById(@PathVariable("id") Integer id){
        LOGGER.info("Buscando Competicao por id: {}", id);
        if(id == null){
            return ResponseEntity.badRequest().body("id inválido");
        }

        Optional<Competicao> competicao = service.findById(id);
        if (competicao.isPresent()) {
            LOGGER.info("Competicao encontrado com sucesso: {}");
            return ResponseEntity.ok().header("Custom-header", "foo").body(competicao.get());
        }else{
            LOGGER.info("Competicao não encontrado pelo id: {}", id);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }

                 
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@RequestBody CompeticaoRequest request) {
        LOGGER.info("Iniciando atualização de Competicao pelo id: {}", request.getId());

        Optional<Competicao> possivelObject = service.findById(request.getId());

        Competicao competicao = possivelObject.get();
        competicao.transform(request);
        LOGGER.info("Competicao encontrado com sucesso (controller): {}");
        service.update(competicao);
        LOGGER.info("Competicao atualizado(a) com sucesso: {}");
        return ResponseEntity.ok().header("Custom-Header", "foo").body(competicao);

    }


    @DeleteMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        LOGGER.info("Iniciando deleção de Competicao pelo id: {}", id);
        Optional<Competicao> possivelCompeticao = service.findById(id);
        if (possivelCompeticao.isPresent()) {
            Competicao competicao = possivelCompeticao.get();
            service.delete(competicao);
            LOGGER.info("Competicao deletado(a) com sucesso: {}");
            return ResponseEntity.ok().header("Custom-Header", "foo").body(competicao);
        }else{
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }

}
