package com.example.demo.futebol.Entidades.Titulo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@Service
public class TituloService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TituloService.class.getName());
    private TituloDao tituloDao;

    public TituloService(TituloDao tituloDao){
        this.tituloDao = tituloDao;
    }

    @Transactional
    public void save(Titulo titulo){
        try{
            LOGGER.info("Service: salvando titulo");
            tituloDao.save(titulo);
            LOGGER.info("Service: titulo salvo com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível salvar título");
        }
    }

    @Transactional
    public void delete(Titulo titulo){
        try{
            LOGGER.info("Service: deletando titulo");
            tituloDao.delete(titulo);
            LOGGER.info("Service: titulo deletado com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível deletar título");
        }
    }

    @Transactional
    public void update(Titulo titulo){
        try{
            LOGGER.info("Service: atualizando titulo");
            tituloDao.update(titulo);
            LOGGER.info("Service: titulo atualizado com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível atualizar título");
        }
    }

    @Transactional
    public Optional<Titulo> findById(Integer id){
        try {
            LOGGER.info("Service: buscando titulo pelo id: {}", id);
            Optional<Titulo> titulo = tituloDao.findById(id);

            if(titulo.isPresent()){
                LOGGER.info("Service: titulo encontrado com sucesso");
                return titulo;
            }else{
                LOGGER.warn("Service: não foi possível encontrar título");
            }
        } catch (PersistenceException e) {
            LOGGER.error("Service: não foi possível recuperar titulo pelo id {}", id, e.getMessage());
        }

        return Optional.empty();
    }

    @Transactional
    public List<Titulo> findAll(){
        try {
            LOGGER.info("Service: buscando por todos os titulos");
            List<Titulo> titulos = tituloDao.findAll();
            LOGGER.info("Service: lista de titulos recuperada com sucesso", titulos.size());
            return titulos;
        } catch (PersistenceException e) {
            LOGGER.error("Service: não foi possível recuperar lista de títulos");
        }
        
        return Collections.emptyList();
    }

}
