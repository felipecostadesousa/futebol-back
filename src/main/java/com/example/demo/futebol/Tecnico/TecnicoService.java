package com.example.demo.futebol.Tecnico;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TecnicoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TecnicoService.class);

    private TecnicoDao repository;

    public TecnicoService(TecnicoDao repository) {

        super();
        this.repository = repository;
    }

    @Transactional
    public List<Tecnico> findAll() {
        try{
            LOGGER.info("Buscando todos Tecnico (service).");
            List<Tecnico> list = this.repository.findAll();
            LOGGER.info("Tamanho da lista recuperada (service): {}", list.size());
            return list;
        }catch (Exception e){
            LOGGER.error("Erro ao buscar todos Tecnico (service).", e);
            throw e;
        }
    }

    @Transactional
    public Optional<Tecnico> findById(Integer id) {
        try{
            LOGGER.info("Buscando Tecnico com ID (service): {}", id);
            Optional<Tecnico> tecnico = this.repository.findById(id);
                if (tecnico.isPresent()) {
                    LOGGER.info("Tecnico encontrado (service): {}");
                } else {
                    LOGGER.warn("Tecnico  com ID {} não encontrado (service)", id);
                }
            return tecnico ;
        } catch (Exception e){
            LOGGER.error("Erro ao buscar Tecnico com ID (service): {}", id, e);
            throw e;
        }

    }

    @Transactional
    public void save(Tecnico tecnico) {
        try {
            LOGGER.info("Criando o Tecnico (service): {}");
            this.repository.save(tecnico);
            LOGGER.info("Tecnico criado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao criar Tecnico (service): {}", e);
            throw e;
        }
        this.repository.save(tecnico);
    }

    @Transactional
    public void update(Tecnico tecnico) {
        try {
            LOGGER.info("Atualizando Tecnico (service): {}");
            this.repository.update(tecnico);
            LOGGER.info("Tecnico atualizado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar Tecnico (service): {}", e);
            throw e;
        }
        this.repository.update(tecnico);
    }

    @Transactional
    public void delete(Tecnico tecnico) {
        try {
            LOGGER.info("Deletando Tecnico (service): {}");
            this.repository.delete(tecnico);
            LOGGER.info("Tecnico deletado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao deletar Tecnico (service): {}", e);
            throw e;
        }
        this.repository.delete(tecnico);
    }

}
