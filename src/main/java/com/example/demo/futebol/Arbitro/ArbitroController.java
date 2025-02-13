package com.example.demo.futebol.Arbitro;

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
@RequestMapping("/arbitro")
public class ArbitroController {
    
private static final Logger LOGGER = LoggerFactory.getLogger(ArbitroController.class);
private ArbitroService service;

    public ArbitroController(ArbitroService service) {
        this.service = service;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> save(@RequestBody ArbitroRequest request){
        LOGGER.info("Iniciando criação de um novo Arbitro");
        if(request == null){
            return ResponseEntity.badRequest().body("Não há dados para serem cadastrados");
        }
        
        Arbitro object = request.transform(request.getContratoInicio(), request.getNome(), request.getApelido(), request.getDataNascimento(), request.getNacionalidade(), request.getImagem() );
        service.save(object);
        return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
    }

    @GetMapping(produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAll() {
        LOGGER.info("Buscando lista de Arbitro");
        List<Arbitro> list = service.findAll();
        return ResponseEntity.ok().header("Custom-Header", "foo").body(list);
    }

    

}