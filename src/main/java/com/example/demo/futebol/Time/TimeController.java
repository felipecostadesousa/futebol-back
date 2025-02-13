package com.example.demo.futebol.Time;

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

import com.example.demo.futebol.Estadio.Estadio;
import com.example.demo.futebol.Estadio.EstadioService;
import com.example.demo.futebol.Localizacao.Localizacao;
import com.example.demo.futebol.Localizacao.LocalizacaoService;

@RestController
@RequestMapping("/time")
public class TimeController {

    private TimeService service;
    private LocalizacaoService localizacaoService;
    private EstadioService estadioService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeController.class);

    public TimeController(TimeService service, LocalizacaoService localizacaoService, EstadioService estadioService) {
        this.service = service;
        this.localizacaoService = localizacaoService;
        this.estadioService = estadioService;
    }


    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<?> save(@RequestBody TimeRequest request){
        LOGGER.info("Iniciando criação de um novo Time para Localizacao com ID: {}");

        Integer possivelEstadio = request.getIdEstadio();
        Optional<Estadio> estadio = estadioService.findById(possivelEstadio);

        if(estadio.isPresent()){
            Estadio estadioFinal = estadio.get();
            Localizacao localizacao = estadioFinal.getLocalizacao();
            Time time = request.transform(estadioFinal, localizacao);
            service.save(time);
            LOGGER.info("Time criado para Localizacao com sucesso!");
            return ResponseEntity.ok().header("Custom-Header", "foo").body(time);
        }
        else{
            LOGGER.info("Não foi possível criar time");
            return ResponseEntity.notFound().header("not-found-id").build();
        }
    }
    @GetMapping(produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAll() {
        LOGGER.info("Buscando lista de Times");
        List<Time> list = service.findAll();
        LOGGER.info("Times encontrados: {}", list.size());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(list);
    }

    @GetMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findById(@PathVariable("id") Integer id){
        LOGGER.info("Buscando Time por id: {}", id);
        if(id == null){
            return ResponseEntity.badRequest().body("id inválido");
        }
        Optional<Time> time = service.findById(id);
        if (time.isPresent()) {
            LOGGER.info("Time encontrado com sucesso: {}");
            return ResponseEntity.ok().header("Custom-header", "foo").body(time.get());
        }else{
            LOGGER.info("Time não encontrado pelo id: {}", id);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }
                       
    @PutMapping(value="/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@RequestBody TimeRequest request) {
        LOGGER.info("Iniciando atualização de Time pelo id: {}", request.getId());

        Integer possivelEstadio = request.getIdEstadio();
        Optional<Estadio> estadio = estadioService.findById(possivelEstadio);
        Optional<Time> possivelTime = service.findById(request.getId());
        
        if(possivelTime.isPresent() && estadio.isPresent()){
            Estadio estadioFinal = estadio.get();
            Time time = request.transform(estadioFinal, estadioFinal.getLocalizacao());
            service.update(time);
            LOGGER.info("Time atualizado(a) com sucesso: {}");
            return ResponseEntity.ok().header("Custom-Header", "foo").body(time);
        } else{
            LOGGER.info("Time não encontrado(a) pelo id: {}", request.getId());
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(request.getId())).build();
        }
    }

    @DeleteMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        LOGGER.info("Iniciando deleção de Time pelo id: {}", id);
        Optional<Time> possivelTime = service.findById(id);
        Time time = possivelTime.get();
        service.delete(time);
        LOGGER.info("Time deletado(a) com sucesso: {}");
        return ResponseEntity.ok().header("Custom-Header", "foo").body(time);
    }
}
