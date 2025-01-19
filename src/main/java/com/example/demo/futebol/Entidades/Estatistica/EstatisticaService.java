package com.example.demo.futebol.Entidades.Estatistica;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@Service
public class EstatisticaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EstatisticaService.class.getName());
    private EstatisticaDao estatisticaDao;

    public EstatisticaService(EstatisticaDao estatisticaDao){
        this.estatisticaDao = estatisticaDao;
    }

    @Transactional
    public void save(Estatistica estatistica){
        try{
            LOGGER.info("Service: salvando estatistica");
            estatisticaDao.save(estatistica);
            LOGGER.info("Service: estatistica salva com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: erro ao salvar estatistica", e.getMessage());
        }
    }

    @Transactional
    public void delete(Estatistica estatistica){
        try{
            LOGGER.info("Service: deletando estatistica");
            estatisticaDao.delete(estatistica);
            LOGGER.info("Service: estatistica deletada com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: erro ao deletar estatistica", e.getMessage());
        }
    }

    @Transactional
    public void update(Estatistica estatistica){
        try{
            LOGGER.info("Service: atualizando estatistica");
            estatisticaDao.save(estatistica);
            LOGGER.info("Service: estatistica atualizada com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: erro ao atualizar estatistica", e.getMessage());
        }
    }

    @Transactional 
    public Optional<Estatistica> findById(Integer id){
        try{
            LOGGER.info("Service: buscanco estatistica com id {}", id);
            Optional<Estatistica> estatistica = estatisticaDao.findById(id);
            if(estatistica.isPresent()){
                LOGGER.info("Service: estatistica recuperada com sucesso");
                return estatistica;
            }else{
                LOGGER.warn("Service: estatistica não encontrada pelo id {}", id);
            }
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível encontrar estatistica com o id {}", id);
        }
        
        return Optional.empty();
    }

    @Transactional 
    public List<Estatistica> findAll(){
        try{
            LOGGER.info("Service: buscanco estatisticaa");
            List<Estatistica> estatisticas = estatisticaDao.findAll();
            LOGGER.info("Service: lista de estatisticas recuperada com sucesso", estatisticas.size());
            return estatisticas;
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível recuperar lista de estatisticas");
        }
        
        return Collections.emptyList();
    }

}
