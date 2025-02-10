package com.example.demo.futebol.Tecnico;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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

import com.example.demo.futebol.Time.Time;
import com.example.demo.futebol.Time.TimeService;

@RestController
@RequestMapping("/time")
public class TecnicoController {

    private TecnicoService service;
    private TimeService timeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(TecnicoController.class);

    public TecnicoController(TecnicoService service, TimeService timeService) {
        this.service = service;
        this.timeService = timeService;
    }

    @PostMapping(value="/tecnico", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> save(@RequestBody TecnicoRequest request){
        Integer idTime = request.getIdTime();
        LOGGER.info("Iniciando criação de um novo Jogador para Time com ID: {}", idTime);

        if (idTime == null){
            return ResponseEntity.badRequest().body("idTime está inválido");
        }

        Optional<Time> possivelTime = this.timeService.findById(idTime);
        if(possivelTime.isPresent()){
            Time time = possivelTime.get();
            Tecnico tecnico = request.transform(time);
            service.save(tecnico);
            LOGGER.info("Tecnico criado para Time com sucesso!");
            return ResponseEntity.ok().header("Custom-Header", "foo").body(tecnico);
        }
        else{
            LOGGER.info("time não encontrado com ID: {}", idTime);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(idTime)).build();
        }

    }

    @GetMapping(value="/tecnico", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAll() {
        try {
            LOGGER.info("Buscando lista de Tecnico");

            List<Tecnico> list = service.findAll();
            
            if (list == null || list.isEmpty()) {
                return ResponseEntity.noContent().build();  // Retorna 204 se não houver técnicos
            }
            
            LOGGER.info("Tecnico encontrados: {}", list.size());
            return ResponseEntity.ok().header("Custom-Header", "foo").body(list);
        } catch (Exception e) {
            LOGGER.error("Erro ao buscar lista de Tecnico", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar técnicos");
        }
    }


    @GetMapping(value="/tecnico/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findById(@PathVariable("id") Integer id){
        LOGGER.info("Buscando Tecnico por id: {}", id);

        if(id == null){
            return ResponseEntity.badRequest().body("id inválido");
        }

        Optional<Tecnico> tecnico = service.findById(id);
        if (tecnico.isPresent()) {
            LOGGER.info("Tecnico encontrado com sucesso: {}");
            return ResponseEntity.ok().header("Custom-header", "foo").body(tecnico.get());
        }else{
            LOGGER.info("Tecnico não encontrado pelo id: {}", id);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }
           
    @PutMapping(value="/tecnico/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody TecnicoRequest request) {
        LOGGER.info("Iniciando atualização de Tecnico pelo id: {}", id);

        Integer idTime = request.getIdTime();

        Optional<Time> possivelTime = this.timeService.findById(idTime);
        Optional<Tecnico> possivelTecnico = service.findById(id);
        if(possivelTecnico.isPresent()){
            Tecnico tecnico = request.transform(possivelTime.get());
            service.update(tecnico);
            LOGGER.info("Tecnico atualizado(a) com sucesso: {}");
            return ResponseEntity.ok().header("Custom-Header", "foo").body(tecnico);
        } else{
            LOGGER.info("Tecnico não encontrado(a) pelo id: {}", request.getId());
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(request.getId())).build();
        }
    }

    @DeleteMapping(value="/tecnico/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {

        LOGGER.info("Iniciando deleção de Tecnico pelo id: {}", id);
        Optional<Tecnico> possivelTecnico = service.findById(id);
        Tecnico tecnico = possivelTecnico.get();
        service.delete(tecnico);
        LOGGER.info("Tecnico deletado(a) com sucesso: {}");
        return ResponseEntity.ok().header("Custom-Header", "foo").body(tecnico);
    }

}
