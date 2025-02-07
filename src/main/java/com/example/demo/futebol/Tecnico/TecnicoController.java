package com.example.demo.futebol.Tecnico;

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

    @PostMapping(value="/{id_time}/Tecnico", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<?> save(@PathVariable("id_time") Integer idTime, @RequestBody TecnicoRequest request){
        LOGGER.info("Iniciando criação de um novo Tecnico para Time com ID: {}", idTime);
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

    @GetMapping(value="/{id_time}/Tecnico", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAll(@PathVariable("id_time") Integer idTime) {
        LOGGER.info("Buscando lista de Tecnico");

        if (idTime == null){
            return ResponseEntity.badRequest().body("idTime está inválido");
        }

        List<Tecnico> list = service.findAll();
        LOGGER.info("Tecnico encontrados: {}", list.size());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(list);
    }

    @GetMapping(value="/{id_time}/Tecnico/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findById(@PathVariable("id_time") Integer idTime, @PathVariable("id") Integer id){
        LOGGER.info("Buscando Tecnico por id: {}", id);

        if (idTime == null){
            return ResponseEntity.badRequest().body("idTime está inválido");
        }

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
           
    @PutMapping(value="/{id_time}/Tecnico", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@PathVariable("id_time") Integer idTime, @RequestBody TecnicoRequest request) {
        LOGGER.info("Iniciando atualização de Tecnico pelo id: {}", request.getId());

        Optional<Time> possivelTime = this.timeService.findById(idTime);
        Optional<Tecnico> possivelTecnico = service.findById(request.getId());
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


    @DeleteMapping(value="/{id_time}/Tecnico/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id_time") Integer idTime, @PathVariable("id") Integer id) {
        LOGGER.info("Iniciando deleção de Tecnico pelo id: {}", id);
        Optional<Tecnico> possivelTecnico = service.findById(id);
        Tecnico tecnico = possivelTecnico.get();
        service.delete(tecnico);
        LOGGER.info("Tecnico deletado(a) com sucesso: {}");
        return ResponseEntity.ok().header("Custom-Header", "foo").body(tecnico);
    }

}
