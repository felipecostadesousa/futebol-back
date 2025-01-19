package com.example.demo.futebol.Entidades.JogadaorTitulo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@Service
public class JogadorTituloService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JogadorTituloService.class.getName());
    private JogadorTituloDao jogadorTituloDao;

    public JogadorTituloService(JogadorTituloDao jogadorTituloDao){
        this.jogadorTituloDao = jogadorTituloDao;
    }

    @Transactional
    public void save(JogadorTitulo jogadorTitulo){
        try{
            LOGGER.info("Service: salvando jogador-titulo");
            jogadorTituloDao.save(jogadorTitulo);
            LOGGER.info("Service: jogador-titulo salva com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: erro ao salvar jogador-titulo", e.getMessage());
        }
    }

    @Transactional
    public void delete(JogadorTitulo jogadorTitulo){
        try{
            LOGGER.info("Service: deletando jogador-titulo");
            jogadorTituloDao.delete(jogadorTitulo);
            LOGGER.info("Service: jogador-titulo deletada com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: erro ao deletar jogador-titulo", e.getMessage());
        }
    }

    @Transactional
    public void update(JogadorTitulo jogadorTitulo){
        try{
            LOGGER.info("Service: atualizando jogador-titulo");
            jogadorTituloDao.save(jogadorTitulo);
            LOGGER.info("Service: jogador-titulo atualizada com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: erro ao atualizar jogador-titulo", e.getMessage());
        }
    }

    @Transactional 
    public Optional<JogadorTitulo> findById(Integer id){
        try{
            LOGGER.info("Service: buscanco jogador-titulo com id {}", id);
            Optional<JogadorTitulo> jogadorTitulo = jogadorTituloDao.findById(id);
            if(jogadorTitulo.isPresent()){
                LOGGER.info("Service: jogador-titulo recuperada com sucesso");
                return jogadorTitulo;
            }else{
                LOGGER.warn("Service: jogador-titulo não encontrada pelo id {}", id);
            }
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível encontrar jogador-titulo com o id {}", id);
        }
        
        return Optional.empty();
    }

    @Transactional 
    public List<JogadorTitulo> findAll(){
        try{
            LOGGER.info("Service: buscanco jogador-titulo");
            List<JogadorTitulo> jogadorTitulo = jogadorTituloDao.findAll();
            LOGGER.info("Service: lista de jogador-titulo recuperada com sucesso", jogadorTitulo.size());
            return jogadorTitulo;
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível recuperar lista de jogador-titulo");
        }
        
        return Collections.emptyList();
    }

    @Transactional
    public Optional<JogadorTitulo> findByJogadorId(Integer jogadorId) {
        try{
            LOGGER.info("Service: buscando titulo do jogador com id {}", jogadorId);
            Optional<JogadorTitulo> jogadorTitulo = jogadorTituloDao.findByJogadorId(jogadorId);
            LOGGER.info("Service: título de jogador encontrado");
            return jogadorTitulo;
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível encontrar títulos do jogador com id {}", jogadorId);
        }

        return Optional.empty();
    }

    @Transactional
    public Optional<JogadorTitulo> findByTituloId(Integer tituloId) {
        try{
            LOGGER.info("Service: buscando titulo com id {}", tituloId);
            Optional<JogadorTitulo> jogadorTitulo = jogadorTituloDao.findByJogadorId(tituloId);
            LOGGER.info("Service: título encontrado");
            return jogadorTitulo;
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível encontrar título com id {}", tituloId);
        }

        return Optional.empty();
    }




}
