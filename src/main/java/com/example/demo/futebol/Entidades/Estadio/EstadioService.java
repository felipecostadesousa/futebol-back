package com.example.demo.futebol.Entidades.Estadio;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@Service
public class EstadioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EstadioService.class.getName());
    private EstadioDao estadioDao;

    public EstadioService(EstadioDao estadioDao){
        this.estadioDao = estadioDao;
    } 

    @Transactional
    public void save(Estadio estadio){
        try{
            LOGGER.info("Service: salvando estadio");
            estadioDao.save(estadio);
            LOGGER.info("Service: estadio salvo com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: erro ao salvar estadio", e.getMessage());
        }
    }

    @Transactional
    public void delete(Estadio estadio){
        try{
            LOGGER.info("Service: deletando estadio");
            estadioDao.delete(estadio);
            LOGGER.info("Service: estadio deletado com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: erro ao deletar estadio", e.getMessage());
        }
    }

    @Transactional
    public void update(Estadio estadio){
        try{
            LOGGER.info("Service: atualizando estadio");
            estadioDao.save(estadio);
            LOGGER.info("Service: estadio atualizado com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: erro ao atualizar estadio", e.getMessage());
        }
    }

    @Transactional 
    public Optional<Estadio> findById(Integer id){
        try{
            LOGGER.info("Service: buscanco estadio com id {}", id);
            Optional<Estadio> estadio = estadioDao.findById(id);
            if(estadio.isPresent()){
                LOGGER.info("Service: estadio recuperado com sucesso");
                return estadio;
            }else{
                LOGGER.warn("Service: estádio não encontrado pelo id {}", id);
            }
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível encontrar estádio com o id {}", id);
        }
        
        return Optional.empty();
    }

    @Transactional 
    public Optional<Estadio> findByName(String name){
        try{
            LOGGER.info("Service: buscanco estadio com nome {}", name);
            Optional<Estadio> estadio = estadioDao.findByName(name);
            if(estadio.isPresent()){
                LOGGER.info("Service: estadio recuperado com sucesso");
                return estadio;
            }else{
                LOGGER.warn("Service: estádio não encontrado pelo nome {}", name);
            }
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível encontrar estádio com o nome {}", name);
        }
        
        return Optional.empty();
    }

    @Transactional 
    public List<Estadio> findAll(){
        try{
            LOGGER.info("Service: buscanco estadios");
            List<Estadio> estadios = estadioDao.findAll();
            LOGGER.info("Service: lista de estadios recuperada com sucesso", estadios.size());
            return estadios;
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível recuperar lista de estadios");
        }
        
        return Collections.emptyList();
    }
    
}
