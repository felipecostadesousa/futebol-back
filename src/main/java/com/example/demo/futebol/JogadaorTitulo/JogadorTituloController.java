package com.example.demo.futebol.JogadaorTitulo;

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

import com.example.demo.futebol.Titulo.Titulo;
import com.example.demo.futebol.Titulo.TituloService;

@RestController
@RequestMapping("//titulo")
public class JogadorTituloController {

    private JogadorTituloService service;
    private TituloService tituloService;
    private static final Logger LOGGER = LoggerFactory.getLogger(JogadorTituloController.class);

    public JogadorTituloController(JogadorTituloService service, TituloService tituloService) {
        this.service = service;
        this.tituloService = tituloService;
    }

    @PostMapping(value="/{id_titulo}/JogadorTitulo", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<?> save(@PathVariable("id_titulo") Integer idTitulo, @RequestBody JogadorTituloRequest request){
        LOGGER.info("Iniciando criação de um novo JogadorTitulo para Titulo com ID: {}", idTitulo);
        if (idTitulo == null){
            return ResponseEntity.badRequest().body("idTitulo está inválido");
        }

        Optional<Titulo> possivelTitulo = this.tituloService.findById(idTitulo);
        if(possivelTitulo.isPresent()){
            Titulo titulo = possivelTitulo.get();
            JogadorTitulo jogadorTitulo = request.transform(titulo);
            service.save(jogadorTitulo);
            LOGGER.info("JogadorTitulo criado para Titulo com sucesso!");
            return ResponseEntity.ok().header("Custom-Header", "foo").body(jogadorTitulo);
        }
        else{
            LOGGER.info("titulo não encontrado com ID: {}", idTitulo);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(idTitulo)).build();
        }

    }

    @GetMapping(value="/{id_titulo}/JogadorTitulo", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAll(@PathVariable("id_titulo") Integer idTitulo) {
        LOGGER.info("Buscando lista de JogadorTitulo");

        if (idTitulo == null){
            return ResponseEntity.badRequest().body("idTitulo está inválido");
        }

        List<JogadorTitulo> list = service.findAll();
        LOGGER.info("JogadorTitulo encontrados: {}", list.size());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(list);
    }

    @GetMapping(value="/{id_titulo}/JogadorTitulo/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findById(@PathVariable("id_titulo") Integer idTitulo, @PathVariable("id") Integer id){
        LOGGER.info("Buscando JogadorTitulo por id: {}", id);

        if (idTitulo == null){
            return ResponseEntity.badRequest().body("idTitulo está inválido");
        }

        if(id == null){
            return ResponseEntity.badRequest().body("id inválido");
        }

        Optional<JogadorTitulo> jogadorTitulo = service.findById(id);
        if (jogadorTitulo.isPresent()) {
            LOGGER.info("JogadorTitulo encontrado com sucesso: {}");
            return ResponseEntity.ok().header("Custom-header", "foo").body(jogadorTitulo.get());
        }else{
            LOGGER.info("JogadorTitulo não encontrado pelo id: {}", id);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }
       
    @PutMapping(value="/{id_titulo}/JogadorTitulo", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@PathVariable("id_titulo") Integer idTitulo, @RequestBody JogadorTituloRequest request) {
        LOGGER.info("Iniciando atualização de JogadorTitulo pelo id: {}", request.getId());

        Optional<Titulo> possivelTitulo = this.tituloService.findById(idTitulo);
        Optional<JogadorTitulo> possivelJogadorTitulo = service.findById(request.getId());
        if(possivelJogadorTitulo.isPresent()){
            JogadorTitulo jogadorTitulo = request.transform(possivelTitulo.get());
            service.update(jogadorTitulo);
            LOGGER.info("JogadorTitulo atualizado(a) com sucesso: {}");
            return ResponseEntity.ok().header("Custom-Header", "foo").body(jogadorTitulo);
        } else{
            LOGGER.info("JogadorTitulo não encontrado(a) pelo id: {}", request.getId());
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(request.getId())).build();
        }
    }


    @DeleteMapping(value="/{id_titulo}/JogadorTitulo/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id_titulo") Integer idTitulo, @PathVariable("id") Integer id) {
        LOGGER.info("Iniciando deleção de JogadorTitulo pelo id: {}", id);
        Optional<JogadorTitulo> possivelJogadorTitulo = service.findById(id);
        JogadorTitulo jogadorTitulo = possivelJogadorTitulo.get();
        service.delete(jogadorTitulo);
        LOGGER.info("JogadorTitulo deletado(a) com sucesso: {}");
        return ResponseEntity.ok().header("Custom-Header", "foo").body(jogadorTitulo);
    }

}