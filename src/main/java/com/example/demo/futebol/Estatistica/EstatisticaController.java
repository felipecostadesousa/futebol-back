package com.example.demo.futebol.Estatistica;

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

import com.example.demo.futebol.Jogador.Jogador;
import com.example.demo.futebol.Jogador.JogadorService;

@RestController
@RequestMapping("/jogador")
public class EstatisticaController {

    private EstatisticaService service;
    private JogadorService jogadorService;
    private static final Logger LOGGER = LoggerFactory.getLogger(EstatisticaController.class);

    public EstatisticaController(EstatisticaService service, JogadorService jogadorService) {
        this.service = service;
        this.jogadorService = jogadorService;
    }

    @PostMapping(value="/{id_jogador}/Estatistica", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<?> save(@PathVariable("id_jogador") Integer idJogador, @RequestBody EstatisticaRequest request){
        LOGGER.info("Iniciando criação de um novo Estatistica para Jogador com ID: {}", idJogador);
        if (idJogador == null){
            return ResponseEntity.badRequest().body("idJogador está inválido");
        }

        Optional<Jogador> possivelJogador = this.jogadorService.findById(idJogador);
        if(possivelJogador.isPresent()){
            Jogador jogador = possivelJogador.get();
            Estatistica estatistica = request.transform(jogador);
            service.save(estatistica);
            LOGGER.info("Estatistica criado para Jogador com sucesso!");
            return ResponseEntity.ok().header("Custom-Header", "foo").body(estatistica);
        }
        else{
            LOGGER.info("jogador não encontrado com ID: {}", idJogador);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(idJogador)).build();
        }

    }

    @GetMapping(value="/{id_jogador}/Estatistica", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAll(@PathVariable("id_jogador") Integer idJogador) {
        LOGGER.info("Buscando lista de Estatistica");

        if (idJogador == null){
            return ResponseEntity.badRequest().body("idJogador está inválido");
        }

        List<Estatistica> list = service.findAll();
        LOGGER.info("Estatistica encontrados: {}", list.size());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(list);
    }

    @GetMapping(value="/{id_jogador}/Estatistica/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findById(@PathVariable("id_jogador") Integer idJogador, @PathVariable("id") Integer id){
        LOGGER.info("Buscando Estatistica por id: {}", id);

        if (idJogador == null){
            return ResponseEntity.badRequest().body("idJogador está inválido");
        }

        if(id == null){
            return ResponseEntity.badRequest().body("id inválido");
        }

        Optional<Estatistica> estatistica = service.findById(id);
        if (estatistica.isPresent()) {
            LOGGER.info("Estatistica encontrado com sucesso: {}");
            return ResponseEntity.ok().header("Custom-header", "foo").body(estatistica.get());
        }else{
            LOGGER.info("Estatistica não encontrado pelo id: {}", id);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }
           
    @PutMapping(value="/{id_jogador}/Estatistica", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@PathVariable("id_jogador") Integer idJogador, @RequestBody EstatisticaRequest request) {
        LOGGER.info("Iniciando atualização de Estatistica pelo id: {}", request.getId());

        Optional<Jogador> possivelJogador = this.jogadorService.findById(idJogador);
        Optional<Estatistica> possivelEstatistica = service.findById(request.getId());
        if(possivelEstatistica.isPresent()){
            Estatistica estatistica = request.transform(possivelJogador.get());
            service.update(estatistica);
            LOGGER.info("Estatistica atualizado(a) com sucesso: {}");
            return ResponseEntity.ok().header("Custom-Header", "foo").body(estatistica);
        } else{
            LOGGER.info("Estatistica não encontrado(a) pelo id: {}", request.getId());
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(request.getId())).build();
        }
    }


    @DeleteMapping(value="/{id_jogador}/Estatistica/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id_jogador") Integer idJogador, @PathVariable("id") Integer id) {
        LOGGER.info("Iniciando deleção de Estatistica pelo id: {}", id);
        Optional<Estatistica> possivelEstatistica = service.findById(id);
        Estatistica estatistica = possivelEstatistica.get();
        service.delete(estatistica);
        LOGGER.info("Estatistica deletado(a) com sucesso: {}");
        return ResponseEntity.ok().header("Custom-Header", "foo").body(estatistica);
    }

}
