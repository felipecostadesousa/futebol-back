package com.example.demo.futebol.Entidades.Tecnico;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@Service
public class TecnicoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TecnicoService.class.getName());
    private TecnicoDao tecnicoDao;

    public TecnicoService(TecnicoDao tecnicoDao){
        this.tecnicoDao = tecnicoDao;
    }

    @Transactional
    public void save(Tecnico tecnico){
        try{
            LOGGER.info("Service: salvando tecnico");
            tecnicoDao.save(tecnico);
            LOGGER.info("Service: tecnico salvo com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível salvar técnico");
        }
    }

    @Transactional
    public void delete(Tecnico tecnico){
        try{
            LOGGER.info("Service: deletando tecnico");
            tecnicoDao.delete(tecnico);
            LOGGER.info("Service: tecnico deletado com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível deletar técnico");
        }
    }

    @Transactional
    public void update(Tecnico tecnico){
        try{
            LOGGER.info("Service: atualizando tecnico");
            tecnicoDao.update(tecnico);
            LOGGER.info("Service: tecnico atualizado com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível atualizar técnico");
        }
    }

    @Transactional
    public void updateTecnicoTime(Integer idTecnico, Integer idTime){
        try{
            LOGGER.info("Service: atualizando time do tecnico");
            tecnicoDao.updateTecnicoTime(idTecnico, idTime);
            LOGGER.info("Service: time do tecnico atualizado com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível atualizar time do técnico");
        }
    }

    @Transactional
    public void updateTecnicoPessoa(Integer idTecnico, Integer idPessoa){
        try{
            LOGGER.info("Service: atualizando pessoa relacionada ao tecnico");
            tecnicoDao.updateTecnicoPessoa(idTecnico, idPessoa);
            LOGGER.info("Service: pessoa relacionada ao tecnico atualizado com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível atualizar pessoa relacionada ao técnico");
        }
    }

    @Transactional
    public Optional<Tecnico> findById(Integer id){
        try {
            LOGGER.info("Service: buscando técnico pelo id: {}", id);
            Optional<Tecnico> tecnico = tecnicoDao.findById(id);

            if(tecnico.isPresent()){
                LOGGER.info("Service: técnico encontrado com sucessso");
                return tecnico;
            }else{
                LOGGER.warn("Service: não foi possivel encontrar técnico");
            }
        } catch (PersistenceException e) {
            LOGGER.info("Service: não foi possível encontrar técnico pelo id {}", id, e.getMessage());
        }

        return Optional.empty();
    }

    @Transactional
    public Optional<Tecnico> findByTime(Integer idTime){
        try {
            LOGGER.info("Service: buscando técnico pelo idTime: {}", idTime);
            Optional<Tecnico> tecnico = tecnicoDao.findByTime(idTime);

            if(tecnico.isPresent()){
                LOGGER.info("Service: técnico do time encontrado com sucessso");
                return tecnico;
            }else{
                LOGGER.warn("Service: não foi possivel encontrar técnico do time");
            }
        } catch (PersistenceException e) {
            LOGGER.error("Service: não foi possível encontrar técnico do time pelo idTime {}", idTime, e.getMessage());
        }

        return Optional.empty();
    }

    @Transactional
    public List<Tecnico> findAll(){
        try{
            LOGGER.info("Service: recuperando lista de técnicos");
            List<Tecnico> tecnicos = tecnicoDao.findAll();
            LOGGER.info("Service: lista de técnicos recuperada com sucesso");
            return tecnicos;
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível recuperar lista de técnicos", e.getMessage());
        }

        return Collections.emptyList();
    }


}
