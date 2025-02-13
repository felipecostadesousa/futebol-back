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

}
