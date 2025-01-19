package com.example.demo.futebol.Entidades.Time;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@Service
public class TimeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeService.class.getName());
    private TimeDao timeDao;

    public TimeService(TimeDao timeDao){
        this.timeDao = timeDao;
    }

    @Transactional
    public void save(Time time){
        try {
            LOGGER.info("Service: salvando time");
            timeDao.save(time);
            LOGGER.info("Service: time salvo com sucesso");
        } catch (PersistenceException e) {
            LOGGER.error("Service: não foi possível salvar time", e.getMessage());
        }
    }

    @Transactional
    public void delete(Time time){
        try {
            LOGGER.info("Service: deletando time");
            timeDao.delete(time);
            LOGGER.info("Service: time deletado com sucesso");
        } catch (PersistenceException e) {
            LOGGER.error("Service: não foi possível deletar time", e.getMessage());
        }
    }

    @Transactional
    public void update(Time time){
        try {
            LOGGER.info("Service: atualizando time");
            timeDao.update(time);
            LOGGER.info("Service: time atualizado com sucesso");
        } catch (PersistenceException e) {
            LOGGER.error("Service: não foi possível atualizar time", e.getMessage());
        }
    }

    @Transactional
    public Optional<Time> findById(Integer id){
        try{
            LOGGER.info("Service: buscando time com id {}", id);
            Optional<Time> time = timeDao.findById(id);

            if(time.isPresent()){
                LOGGER.info("Service: time recuperado com sucesso");
                return time;
            }else{
                LOGGER.warn("Service: não foi possível encontrar time");
            }
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível recuperar time com id {}", id, e.getMessage());
        }

        return Optional.empty();
    }

    @Transactional
    public Optional<Time> findByName(String name){
        try{
            LOGGER.info("Service: buscando time com o nome {}", name);
            Optional<Time> time = timeDao.findByName(name);

            if(time.isPresent()){
                LOGGER.info("Service: time recuperado com sucesso");
                return time;
            }else{
                LOGGER.warn("Service: não foi possível encontrar time");
            }
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível recuperar time com o nome {}", name, e.getMessage());
        }

        return Optional.empty();
    }

    @Transactional
    public List<Time> findAll(){
        try {
            LOGGER.info("Service: buscando por todos os times");
            List<Time> times = timeDao.findAll();
            LOGGER.info("Service: lista de times recuperada com sucesso", times.size());
            return times;
        } catch (Exception e) {
            LOGGER.error("Service: não foi possível recuperar lista de times", e.getMessage());
        }

        return Collections.emptyList();
    }

}
