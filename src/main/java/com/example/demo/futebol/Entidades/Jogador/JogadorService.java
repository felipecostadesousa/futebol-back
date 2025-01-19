package com.example.demo.futebol.Entidades.Jogador;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@Service
public class JogadorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JogadorService.class.getName());
    private JogadorDao jogadorDao;

    public JogadorService(JogadorDao jogadorDao){
        this.jogadorDao = jogadorDao;
    }

    @Transactional
    public void save(Jogador jogador){
        try{
            LOGGER.info("Service: salvando jogador");
            jogadorDao.save(jogador);
            LOGGER.info("Service: jogador salvo com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: erro ao salvar jogador", e.getMessage());
        }
    }

    @Transactional
    public void delete(Jogador jogador){
        try{
            LOGGER.info("Service: deletando jogador");
            jogadorDao.delete(jogador);
            LOGGER.info("Service: jogador deletado com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: erro ao deletar jogador", e.getMessage());
        }
    }

    @Transactional
    public void update(Jogador jogador){
        try{
            LOGGER.info("Service: atualizando jogador");
            jogadorDao.save(jogador);
            LOGGER.info("Service: jogador atualizado com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: erro ao atualizar jogador", e.getMessage());
        }
    }

    @Transactional 
    public Optional<Jogador> findById(Integer id){
        try{
            LOGGER.info("Service: buscanco jogador com id {}", id);
            Optional<Jogador> jogador = jogadorDao.findById(id);
            if(jogador.isPresent()){
                LOGGER.info("Service: jogador recuperadO com sucesso");
                return jogador;
            }else{
                LOGGER.warn("Service: jogador não encontradO pelo id {}", id);
            }
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível encontrar jogador com o id {}", id);
        }
        
        return Optional.empty();
    }

    @Transactional 
    public List<Jogador> findAll(){
        try{
            LOGGER.info("Service: buscanco jogador");
            List<Jogador> jogadores = jogadorDao.findAll();
            LOGGER.info("Service: lista de jogadores recuperada com sucesso", jogadores.size());
            return jogadores;
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível recuperar lista de jogadores");
        }
        
        return Collections.emptyList();
    }


}
