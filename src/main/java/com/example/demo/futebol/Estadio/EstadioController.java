package com.example.demo.futebol.Estadio;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.futebol.Localizacao.Localizacao;
import com.example.demo.futebol.Localizacao.LocalizacaoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/localizacao")
public class EstadioController {

    private EstadioService service;
    private LocalizacaoService localizacaoService;
    private static final Logger LOGGER = LoggerFactory.getLogger(EstadioController.class);

    public EstadioController(EstadioService service, LocalizacaoService localizacaoService) {
        this.service = service;
        this.localizacaoService = localizacaoService;
    }

    @PostMapping(value="/{id_localizacao}/Estadio", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<?> save(@PathVariable("id_localizacao") Integer idLocalizacao, @RequestBody EstadioRequest request){
        LOGGER.info("Iniciando criação de um novo Estadio para Localizacao com ID: {}", idLocalizacao);
        if (idLocalizacao == null){
            return ResponseEntity.badRequest().body("idLocalizacao está inválido");
        }

        Optional<Localizacao> possivelLocalizacao = this.localizacaoService.findById(idLocalizacao);
        if(possivelLocalizacao.isPresent()){
            Localizacao localizacao = possivelLocalizacao.get();
            Estadio estadio = request.transform(localizacao);
            service.save(estadio);
            LOGGER.info("Estadio criado para Localizacao com sucesso!");
            return ResponseEntity.ok().header("Custom-Header", "foo").body(estadio);
        }
        else{
            LOGGER.info("localizacao não encontrado com ID: {}", idLocalizacao);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(idLocalizacao)).build();
        }

    }

    @GetMapping(value="/{id_localizacao}/Estadio", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAll(@PathVariable("id_localizacao") Integer idLocalizacao) {
        LOGGER.info("Buscando lista de Estadio");

        if (idLocalizacao == null){
            return ResponseEntity.badRequest().body("idLocalizacao está inválido");
        }

        List<Estadio> list = service.findAll();
        LOGGER.info("Estadio encontrados: {}", list.size());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(list);
    }

    @GetMapping(value="/{id_localizacao}/Estadio/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findById(@PathVariable("id_localizacao") Integer idLocalizacao, @PathVariable("id") Integer id){
        LOGGER.info("Buscando Estadio por id: {}", id);

        if (idLocalizacao == null){
            return ResponseEntity.badRequest().body("idLocalizacao está inválido");
        }

        if(id == null){
            return ResponseEntity.badRequest().body("id inválido");
        }

        Optional<Estadio> estadio = service.findById(id);
        if (estadio.isPresent()) {
            LOGGER.info("Estadio encontrado com sucesso: {}");
            return ResponseEntity.ok().header("Custom-header", "foo").body(estadio.get());
        }else{
            LOGGER.info("Estadio não encontrado pelo id: {}", id);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }
           
    @PutMapping(value="/{id_localizacao}/Estadio", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@PathVariable("id_localizacao") Integer idLocalizacao, @RequestBody EstadioRequest request) {
        LOGGER.info("Iniciando atualização de Estadio pelo id: {}", request.getId());

        Optional<Localizacao> possivelLocalizacao = this.localizacaoService.findById(idLocalizacao);
        Optional<Estadio> estadio = service.findById(request.getId());
        if(estadio.isPresent()){
            Estadio estadioFinal = request.transform(possivelLocalizacao.get());
            service.update(estadioFinal);
            LOGGER.info("Estadio atualizado(a) com sucesso: {}");
            return ResponseEntity.ok().header("Custom-Header", "foo").body(estadioFinal);
        } else{
            LOGGER.info("Estadio não encontrado(a) pelo id: {}", request.getId());
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(request.getId())).build();
        }
    }


    @DeleteMapping(value="/Estadio/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        LOGGER.info("Iniciando deleção do Estadio com ID: {}", id);

        Optional<Estadio> possivelEstadio = service.findById(id);
        if (possivelEstadio.isEmpty()) {
            LOGGER.warn("Estadio com ID {} não encontrado.", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estadio com ID " + id + " não encontrado.");
        }
        service.delete(possivelEstadio.get());
        LOGGER.info("Estadio deletado com sucesso: ID {}", id);
        return ResponseEntity.ok().body("Estadio removido com sucesso.");
    }

    @GetMapping(value="/Estadio", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAllEstadios() {
        LOGGER.info("Buscando todos os estádios cadastrados");
        List<Estadio> estadios = service.findAll();
        if (estadios.isEmpty()) {
          LOGGER.info("Nenhum estádio encontrado.");
          return ResponseEntity.noContent().build();
        }
        LOGGER.info("Foram encontrados {} estádios.", estadios.size());
        return ResponseEntity.ok().body(estadios);
    }
}