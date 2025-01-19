package com.example.demo.futebol.Entidades.Jogo;

import java.util.Optional;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@Service
public class JogoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JogoService.class.getName());
    private JogoDao jogoDao;
    
    public JogoService(JogoDao jogoDao){
        this.jogoDao = jogoDao;
    }

    @Transactional
    public void save(Jogo jogo){
        try{
            LOGGER.info("Service: salvando jogo");
            jogoDao.save(jogo);
            LOGGER.info("Service: jogo salvo com sucesso"); 
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível salvar jogo", e.getMessage());
        }
    }

    @Transactional
    public void delete(Jogo jogo){
        try{
            LOGGER.info("Service: deletando jogo");
            jogoDao.delete(jogo);
            LOGGER.info("Service: jogo deletado com sucesso"); 
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível deletar jogo", e.getMessage());
        }
    }

    @Transactional
    public void update(Jogo jogo){
        try{
            LOGGER.info("Service: atualizando jogo");
            jogoDao.update(jogo);
            LOGGER.info("Service: jogo atualizado com sucesso"); 
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível atualizar jogo", e.getMessage());
        }
    }

    @Transactional
    public Optional<Jogo> findById(Integer id){
        try{
            LOGGER.info("Service: buscando jogo por id {}", id);
            Optional<Jogo> jogo = jogoDao.findById(id);

            if(jogo.isPresent()){
                LOGGER.info("Service: jogo recuperado com sucesso");
                return jogo;
            }
            else{
                LOGGER.warn("Service: não foi possível encontrar jogo pelo id {}", id);
            }
        }catch(PersistenceException e){
            LOGGER.error("Service: erro ao encontrar jogo com id {}", id, e.getMessage());
        }

        return Optional.empty();
    }

    @Transactional
    public List<Jogo> findAll(){
        try{
            LOGGER.info("Service: buscando por todos os jogos");
            List<Jogo> jogos = jogoDao.findAll();
            LOGGER.info("Service: jogos recuperados com sucesso", jogos.size());
            return jogos;
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível recuperar jogos", e.getMessage());
        }
    
        return Collections.emptyList();
    }

}
