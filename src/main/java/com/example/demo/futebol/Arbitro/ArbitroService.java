package com.example.demo.futebol.Arbitro;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ArbitroService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArbitroService.class);

    private ArbitroDao repository;

    public ArbitroService(ArbitroDao repository) {

        super();
        this.repository = repository;
    }

    @Transactional
    public List<Arbitro> findAll() {
        try{
            LOGGER.info("Buscando todos Arbitro (service).");
            List<Arbitro> list = this.repository.findAll();
            LOGGER.info("Tamanho da lista recuperada (service): {}", list.size());
            return list;
        }catch (Exception e){
            LOGGER.error("Erro ao buscar todos Arbitro (service).", e);
            throw e;
        }
    }

    @Transactional
    public Optional<Arbitro> findById(Integer id) {
        try{
            LOGGER.info("Buscando Arbitro com ID (service): {}", id);
            Optional<Arbitro> Arbitro = this.repository.findById(id);
                if (Arbitro.isPresent()) {
                    LOGGER.info("Arbitro encontrado (service): {}");
                } else {
                    LOGGER.warn("Arbitro  com ID {} não encontrado (service)", id);
                }
            return Arbitro ;
        } catch (Exception e){
            LOGGER.error("Erro ao buscar Arbitro com ID (service): {}", id, e);
            throw e;
        }

    }

    @Transactional
    public void save(Arbitro Arbitro) {
        try {
            LOGGER.info("Criando o Arbitro (service): {}");
            this.repository.save(Arbitro);
            LOGGER.info("Arbitro criado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao criar Arbitro (service): {}", e);
            throw e;
        }
        this.repository.save(Arbitro);
    }

    @Transactional
    public void update(Arbitro Arbitro) {
        try {
            LOGGER.info("Atualizando Arbitro (service): {}");
            this.repository.update(Arbitro);
            LOGGER.info("Arbitro atualizado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar Arbitro (service): {}", e);
            throw e;
        }
        this.repository.update(Arbitro);
    }

    @Transactional
    public void delete(Arbitro Arbitro) {
        try {
            LOGGER.info("Deletando Arbitro (service): {}");
            this.repository.delete(Arbitro);
            LOGGER.info("Arbitro deletado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao deletar Arbitro (service): {}", e);
            throw e;
        }
        this.repository.delete(Arbitro);
    }

}
