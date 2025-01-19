package com.example.demo.futebol.Entidades.Arbitro;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@Service
public class ArbitroService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArbitroService.class);
    private ArbitroDao arbitroDao;

    public ArbitroService(ArbitroDao arbitroDao) {
        this.arbitroDao = arbitroDao;
    }

    @Transactional
    public void save(Arbitro arbitro) {
        try{
            LOGGER.info("Salvando arbitro");
            this.arbitroDao.save(arbitro);
            LOGGER.info("Arbitro salvo com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Erro de serviço ao salvar arbitro", e.getMessage());
        }
    }

    @Transactional
    public void delete(Arbitro arbitro){
        try{
            LOGGER.info("Deletando arbitro");
            this.arbitroDao.delete(arbitro);
            LOGGER.info("Arbitro deletado com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Erro de serviço ao deletar arbitro", e.getMessage());
        }
    }

    @Transactional
    public void update(Arbitro arbitro){
        try{
            LOGGER.info("Atualizando arbitro");
            this.arbitroDao.update(arbitro);
            LOGGER.info("Arbitro atualizado com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Erro de serviço ao atualizar arbitro", e.getMessage());
        }
    }

    @Transactional 
    public Optional<Arbitro> finById(Integer id){
        try{
            LOGGER.info("Buscando Arbitro com o id {}", id);
            Optional<Arbitro> arbitro = this.arbitroDao.findById(id);
            if(arbitro.isPresent()){
                LOGGER.info("Arbitro encontrado com sucesso");
                return arbitro;
            }else{
                LOGGER.error("Arbitro não encontrado");
                return Optional.empty();
            }
        }catch(PersistenceException e){
            LOGGER.error("Erro de serviço: não foi possível encontrar arbitro com o id {}", id);
        }
        
        return Optional.empty();   

    }

    @Transactional
    public Optional<Arbitro> findByName(String name){
        try{
            LOGGER.info("Buscando o arbitro com o nome {}", name);
            Optional<Arbitro> arbitro = this.arbitroDao.findByName(name);
            if(arbitro.isPresent()){
                LOGGER.info("Arbitro encontrado com sucesso");
                return arbitro;
            }else{
                LOGGER.error("Arbitro não encontrado");
                return Optional.empty();
            }
        }catch(PersistenceException e){
            LOGGER.error("Erro de serviço: não foi possível encontrar o arbitro com o nome {}", name);
        }

        return Optional.empty();
    }

    @Transactional
    public List<Arbitro> findAll(){
        try{
            LOGGER.info("Buscando todos os arbitros");
            List<Arbitro> arbitros = arbitroDao.findAll();
            LOGGER.info("Todos os arbitros recuperados: {}", arbitros.size());
            return arbitros;
        }catch(PersistenceException e){
            LOGGER.error("Erro de seerviço: não foi possível recuperar a lista de árbitros");
        }
        return Collections.emptyList();
    }

}
