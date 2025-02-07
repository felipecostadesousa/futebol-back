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

import com.example.demo.futebol.Localizacao.Localizacao;
import com.example.demo.futebol.Localizacao.LocalizacaoService;

@RestController
@RequestMapping("/localizacao")
public class TimeController {

    private TimeService service;
    private LocalizacaoService localizacaoService;
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeController.class);

    public TimeController(TimeService service, LocalizacaoService localizacaoService) {
        this.service = service;
        this.localizacaoService = localizacaoService;
    }

    @PostMapping(value="/{id_localizacao}/Time", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<?> save(@PathVariable("id_localizacao") Integer idLocalizacao, @RequestBody TimeRequest request){
        LOGGER.info("Iniciando criação de um novo Time para Localizacao com ID: {}", idLocalizacao);
        if (idLocalizacao == null){
            return ResponseEntity.badRequest().body("idLocalizacao está inválido");
        }

        Optional<Localizacao> possivelLocalizacao = this.localizacaoService.findById(idLocalizacao);
        if(possivelLocalizacao.isPresent()){
            Localizacao localizacao = possivelLocalizacao.get();
            Time time = request.transform(localizacao);
            service.save(time);
            LOGGER.info("Time criado para Localizacao com sucesso!");
            return ResponseEntity.ok().header("Custom-Header", "foo").body(time);
        }
        else{
            LOGGER.info("localizacao não encontrado com ID: {}", idLocalizacao);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(idLocalizacao)).build();
        }

    }

    @GetMapping(value="/{id_localizacao}/Time", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAll(@PathVariable("id_localizacao") Integer idLocalizacao) {
        LOGGER.info("Buscando lista de Time");

        if (idLocalizacao == null){
            return ResponseEntity.badRequest().body("idLocalizacao está inválido");
        }

        List<Time> list = service.findAll();
        LOGGER.info("Time encontrados: {}", list.size());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(list);
    }

    @GetMapping(value="/{id_localizacao}/Time/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findById(@PathVariable("id_localizacao") Integer idLocalizacao, @PathVariable("id") Integer id){
        LOGGER.info("Buscando Time por id: {}", id);

        if (idLocalizacao == null){
            return ResponseEntity.badRequest().body("idLocalizacao está inválido");
        }

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
                       
    @PutMapping(value="/{id_localizacao}/Time", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@PathVariable("id_localizacao") Integer idLocalizacao, @RequestBody TimeRequest request) {
        LOGGER.info("Iniciando atualização de Time pelo id: {}", request.getId());

        Optional<Localizacao> possivelLocalizacao = this.localizacaoService.findById(idLocalizacao);
        Optional<Time> possivelTime = service.findById(request.getId());
        if(possivelTime.isPresent() && possivelLocalizacao.isPresent()){

            Localizacao localizacao = localizacao.transform(possivelLocalizacao.get());
            Time time = request.transform(possivelLocalizacao.get());
            service.update(time);
            LOGGER.info("Time atualizado(a) com sucesso: {}");
            return ResponseEntity.ok().header("Custom-Header", "foo").body(time);
        } else{
            LOGGER.info("Time não encontrado(a) pelo id: {}", request.getId());
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(request.getId())).build();
        }
    }


    @DeleteMapping(value="/{id_localizacao}/Time/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id_localizacao") Integer idLocalizacao, @PathVariable("id") Integer id) {
        LOGGER.info("Iniciando deleção de Time pelo id: {}", id);
        Optional<Time> possivelTime = service.findById(id);
        Time time = possivelTime.get();
        service.delete(time);
        LOGGER.info("Time deletado(a) com sucesso: {}");
        return ResponseEntity.ok().header("Custom-Header", "foo").body(time);
    }

}

