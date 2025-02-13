package com.example.demo.futebol.view;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/estatisticas_jogadores")
public class ViewController {

    private ViewService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewController.class);

    public ViewController(ViewService service) {
        this.service = service;
    }

    @GetMapping(produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAll() {
        LOGGER.info("Buscando estatisticas de jogadores");

        List<EstatisticasJogadoresView> list = service.findView();
        LOGGER.info("Estatisticas encontradas: {}", list.size());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(list);
    }

}