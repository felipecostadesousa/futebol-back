package com.example.demo.futebol.Localizacao;

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
@RequestMapping("/localizacao")
public class LocalizacaoController {

private static final Logger LOGGER = LoggerFactory.getLogger(LocalizacaoController.class);

private LocalizacaoService service;

    public LocalizacaoController(LocalizacaoService service) {
        this.service = service;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> save(@RequestBody LocalizacaoRequest request){
        LOGGER.info("Iniciando criação de um novo Localizacao");

        Localizacao object = request.transform(request.getPais(), request.getRegiao(), request.getEstado(), request.getCidade());
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
        public ResponseEntity<?> findById(@PathVariable("id") Integer id){
        LOGGER.info("Buscando Localizacao por id: {}", id);
        if(id == null){
            return ResponseEntity.badRequest().body("id inválido");
        }

        Optional<Localizacao> possivelLocalizacao = service.findById(id);
        if (possivelLocalizacao.isPresent()) {
            LOGGER.info("Localizacao encontrado com sucesso: {}");
            return ResponseEntity.ok().header("Custom-header", "foo").body(possivelLocalizacao.get());
        }else{
            LOGGER.info("Localizacao não encontrado pelo id: {}", id);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }

                    
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@RequestBody LocalizacaoRequest request) {
        LOGGER.info("Iniciando atualização de Localizacao pelo id: {}", request.getId());

        Optional<Localizacao> possivelLocalizacao = service.findById(request.getId());

        Localizacao localizacao = possivelLocalizacao.get();
        localizacao.transform(request);
        LOGGER.info("Localizacao encontrado com sucesso (controller): {}");
        service.update(localizacao);
        LOGGER.info("Localizacao atualizado(a) com sucesso: {}");
        return ResponseEntity.ok().header("Custom-Header", "foo").body(localizacao);

    }


    @DeleteMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        LOGGER.info("Iniciando deleção de Localizacao pelo id: {}", id);
        Optional<Localizacao> possivelLocalizacao = service.findById(id);
        if (possivelLocalizacao.isPresent()) {
            Localizacao localizacao = possivelLocalizacao.get();
            service.delete(localizacao);
            LOGGER.info("Localizacao deletado(a) com sucesso: {}");
            return ResponseEntity.ok().header("Custom-Header", "foo").body(localizacao);
        }else{
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }

}
