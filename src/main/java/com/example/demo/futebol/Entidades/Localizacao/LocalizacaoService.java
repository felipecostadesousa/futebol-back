package com.example.demo.futebol.Entidades.Localizacao;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@Service
public class LocalizacaoService {

    private LocalizacaoDao localizacaoDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(LocalizacaoService.class.getName());

    public LocalizacaoService(LocalizacaoDao localizacaoDao){
        this.localizacaoDao = localizacaoDao;
    }

    @Transactional
    public void save(Localizacao localizacao) {
        try{
            LOGGER.info("Service: salvando localização");
            localizacaoDao.save(localizacao);
            LOGGER.info("Service: localização salva com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível salvar a localização", e.getMessage());
        }
    }

    @Transactional
    public void delete(Localizacao localizacao) {
        try{
            LOGGER.info("Service: deletando localização");
            localizacaoDao.delete(localizacao);
            LOGGER.info("Service: localização deletada com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível deletar a localização", e.getMessage());
        }
    }

    @Transactional
    public void update(Localizacao localizacao) {
        try{
            LOGGER.info("Service: atualizando localização");
            localizacaoDao.update(localizacao);
            LOGGER.info("Service: localização atualizada com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível atualizar a localização", e.getMessage());
        }
    }

    @Transactional
    public Optional<Localizacao> findById(Integer id) {
        try{
            LOGGER.info("Service: buscando localização com id {}", id);
            Optional<Localizacao> localizacao = localizacaoDao.findById(id);

            if(localizacao.isPresent()){
                LOGGER.info("Service: localização encontrada com sucesso");
                return localizacao;
            }else{
                LOGGER.warn("Service: não foi possível encontrar localização");
            }
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível encontrar localização com id {}", id, e.getMessage());
        }

        return Optional.empty();
    }

    @Transactional
    public List<Localizacao> findAll() {
        try{
            LOGGER.info("Service: buscando todas as localizações");
            List<Localizacao> localizacoes = localizacaoDao.findAll();
            LOGGER.info("Service: localizações encontradas com sucesso", localizacoes.size());
            return localizacoes;
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível recuperar lista de localizações", e.getMessage());
        }

        return Collections.emptyList();
    }
}


