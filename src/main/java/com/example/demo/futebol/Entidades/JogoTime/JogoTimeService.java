package com.example.demo.futebol.Entidades.JogoTime;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@Service
public class JogoTimeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JogoTimeService.class.getName());
    private JogoTimeDao jogoTimeDao;

    public JogoTimeService(JogoTimeDao jogoTimeDao){
        this.jogoTimeDao = jogoTimeDao;
    }

    @Transactional
    public void save(JogoTime jogoTime){
        try{
            LOGGER.info("Service: salvando jogo-time");
            jogoTimeDao.save(jogoTime);
            LOGGER.info("Service: jogo-time salvo com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível salvar jogo-time", e.getMessage());
        }
    }

    @Transactional 
    public void delete(JogoTime jogoTime){
        try{
            LOGGER.info("Service: deletando jogo-time");
            jogoTimeDao.delete(jogoTime);
            LOGGER.info("Service: jogo-time deletado com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível deletar jogo-time");
        }
    }

    @Transactional
    public void update(JogoTime jogoTime){
        try{
            LOGGER.info("Service: atualizando jogo-time");
            jogoTimeDao.update(jogoTime);
            LOGGER.info("Service: jogo-time atualizado com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível atualizar jogo-time", e.getMessage());
        }
    }

    @Transactional
    public Optional<JogoTime> findById(Integer id){
        try{
            LOGGER.info("Service: buscando jogo-time com id {}", id);
            Optional<JogoTime> jogoTime = jogoTimeDao.findById(id);
            
            if(jogoTime.isPresent()){
                LOGGER.info("Service: jogo-time encontrado com sucesso");
                return jogoTime;
            }else{
                LOGGER.warn("Service: não foi possível encontrar jogo-time");
            }
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível recuperar jogo-time pelo id {}", id);
        }

        return Optional.empty();
    }

    @Transactional
    public Optional<JogoTime> findByTime(Integer idTime){
        try{
            LOGGER.info("Service: buscando jogo-time com o time {}", idTime);
            Optional<JogoTime> jogoTime = jogoTimeDao.findByTime(idTime);
            
            if(jogoTime.isPresent()){
                LOGGER.info("Service: jogo-time relacionado a time encontrado com sucesso");
                return jogoTime;
            }else{
                LOGGER.warn("Service: não foi possível encontrar jogo-time relacionado a time");
            }
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível recuperar jogo-time relacionado a time pelo id {}", idTime, e.getMessage());
        }

        return Optional.empty();
    }

    @Transactional
    public List<JogoTime> findAll(){
        try{
            LOGGER.info("Service: buscando lista de jogo-time");
            List<JogoTime> jogoTime = jogoTimeDao.findAll();
            LOGGER.info("Service: lista de jogo-time recuperada com sucesso", jogoTime.size());
            return jogoTime;
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível recuperar lista de jogo-time");
        }

        return Collections.emptyList();
    }

}
