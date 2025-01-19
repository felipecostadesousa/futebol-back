package com.example.demo.futebol.Entidades.Competicao;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@Service
public class CompeticaoService {

    private CompeticaoDao competicaoDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(CompeticaoService.class.getName());

    public CompeticaoService(CompeticaoDao competicaoDao){
        this.competicaoDao = competicaoDao;
    }

    @Transactional
    public void save(Competicao competicao){
        try{    
            LOGGER.info("Service: salvando competição");
            competicaoDao.save(competicao);
            LOGGER.info("Service: competição salva com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível salvar competição");
        }
    }

    @Transactional
    public void delete(Competicao competicao){
        try{
            LOGGER.info("Service: deletando competição");
            competicaoDao.delete(competicao);
            LOGGER.info("Service: competição deletada com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível deletar a competição");
        }
    }

    @Transactional
    public void update(Competicao competicao){
        try{
            LOGGER.info("Service: atualizando competição");
            competicaoDao.update(competicao);
            LOGGER.info("Service: competição atualizada com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível deletar a competição");
        }
    }

    @Transactional
    public Optional<Competicao> findById(Integer id){
        try{
            LOGGER.info("Service: buscando competição com o id {}", id);
            Optional<Competicao> competicao = this.competicaoDao.findById(id);
            if(competicao.isPresent()){
                LOGGER.info("Service: competição recuperada com sucesso");
                
            }else{
                LOGGER.error("Service: não foi possível encontrar competição com o id {}", id);
            }
            return competicao;
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível encontrar competição com o id {}", id);
        }

        return Optional.empty();
    }

    @Transactional
    public Optional<Competicao> findByName(String name){
        try{
            LOGGER.info("Service: buscando competição com o nome {}", name);
            Optional<Competicao> competicao = this.competicaoDao.findByName(name);
            if(competicao.isPresent()){
                LOGGER.info("Service: competição recuperada com sucesso");
                
            }else{
                LOGGER.error("Service: não foi possível encontrar competição com o nome {}", name);
            }
            return competicao;
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível encontrar competição com o nome {}", name);
        }

        return Optional.empty();
    }

    @Transactional
    public List<Competicao> findAll(){
        try{
            LOGGER.info("Service: buscando todas as competições");
            List<Competicao> competicoes = competicaoDao.findAll();
            LOGGER.info("Service: competições encontradas com sucesso {}", competicoes.size());
            return competicoes;
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível recuperar lista de competições", e.getMessage());
        }

        return Collections.emptyList();
    }

}
