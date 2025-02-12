package com.example.demo.futebol.Jogador;

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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.futebol.Time.Time;
import com.example.demo.futebol.Time.TimeService;

import jakarta.persistence.EntityNotFoundException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/time")
public class JogadorController {

    private JogadorService service;
    private TimeService timeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(JogadorController.class);

    public JogadorController(JogadorService service, TimeService timeService) {
        this.service = service;
        this.timeService = timeService;
    }

    @PostMapping(value = "/jogador", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<?> save(@RequestBody JogadorRequest request) {
        Integer idTime = request.getIdTime();
        LOGGER.info("Iniciando criação de um novo Jogador para Time com ID: {}", idTime);
        if (idTime == null){
            return ResponseEntity.badRequest().body("idTime está inválido");
        }

        Optional<Time> possivelTime = this.timeService.findById(idTime);
        if(possivelTime.isPresent()){
            Time time = possivelTime.get();
            Jogador jogador = request.transform(time);
            service.save(jogador);
            LOGGER.info("Jogador criado para Time com sucesso!");
            return ResponseEntity.ok().header("Custom-Header", "foo").body(jogador);
        }
        else{
            LOGGER.info("time não encontrado com ID: {}", idTime);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(idTime)).build();
        }

    }

    @GetMapping(value="/Jogador", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAll() {
        LOGGER.info("Buscando lista de Jogador");

        List<Jogador> list = service.findAll();
        LOGGER.info("Jogador encontrados: {}", list.size());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(list);
    }

    @GetMapping(value="/jogador/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findById(@PathVariable("id") Integer id){
        LOGGER.info("Buscando Jogador por id: {}", id);

        if(id == null){
            return ResponseEntity.badRequest().body("id inválido");
        }

        Optional<Jogador> jogador = service.findById(id);
        if (jogador.isPresent()) {
            LOGGER.info("Jogador encontrado com sucesso: {}");
            return ResponseEntity.ok().header("Custom-header", "foo").body(jogador.get());
        }else{
            LOGGER.info("Jogador não encontrado pelo id: {}", id);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }
  
    @PatchMapping(value="/jogador/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody JogadorRequest request) {
        LOGGER.info("Iniciando atualização de Jogador pelo id: {}", request.getId());

        Integer idTime = request.getIdTime();
        if (idTime == null) {
            return ResponseEntity.badRequest().body("idTime está inválido");
        }

        Optional<Time> possivelTime = this.timeService.findById(idTime);
        Optional<Jogador> possivelJogador = service.findById(id);
        if(possivelJogador.isPresent()){
            Jogador jogador = request.transform(possivelTime.get());
            service.update(jogador);
            LOGGER.info("Jogador atualizado(a) com sucesso: {}");
            return ResponseEntity.ok().header("Custom-Header", "foo").body(jogador);
        } else{
            LOGGER.info("Jogador não encontrado(a) pelo id: {}", request.getId());
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(request.getId())).build();
        }
    }

    @PutMapping(value = "/jogador/{id}/{novoTimeId}", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> atualizarTime(@PathVariable Integer id, @PathVariable Integer novoTimeId) {
      Optional<Jogador> jogador = service.findById(id);
      Optional<Time> time = timeService.findById(novoTimeId);
      if (jogador.isPresent() && time.isPresent()) {
          service.updateTime(id, novoTimeId);
          return ResponseEntity.ok("Time do jogador atualizado com sucesso!");
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogador ou time não encontrado.");
      }
    }


    @DeleteMapping(value="/Jogador/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        LOGGER.info("Iniciando deleção do jogador com ID: {}", id);

        Optional<Jogador> possivelJogador = service.findById(id);
        if (possivelJogador.isEmpty()) {
            LOGGER.warn("Jogador com ID {} não encontrado.", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogador com ID " + id + " não encontrado.");
        }
        service.delete(possivelJogador.get());
        LOGGER.info("Jogador deletado com sucesso: ID {}", id);
        return ResponseEntity.ok().body("Jogador removido com sucesso.");
    }
}