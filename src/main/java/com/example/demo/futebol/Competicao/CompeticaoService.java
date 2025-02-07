package com.example.demo.futebol.Competicao;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CompeticaoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompeticaoService.class);

    private CompeticaoDao repository;

    public CompeticaoService(CompeticaoDao repository) {
        super();
        this.repository = repository;
    }

    @Transactional
    public List<Competicao> findAll() {
        try{
            LOGGER.info("Buscando todos Competicao (service).");
            List<Competicao> list = this.repository.findAll();
            LOGGER.info("Tamanho da lista recuperada (service): {}", list.size());
            return list;
        }catch (Exception e){
            LOGGER.error("Erro ao buscar todos Competicao (service).", e);
            throw e;
        }
    }

    @Transactional
    public Optional<Competicao> findById(Integer id) {
        try{
            LOGGER.info("Buscando Competicao com ID (service): {}", id);
            Optional<Competicao> competicao = this.repository.findById(id);
                if (competicao.isPresent()) {
                    LOGGER.info("Competicao encontrado (service): {}");
                } else {
                    LOGGER.warn("Competicao  com ID {} não encontrado (service)", id);
                }
            return competicao ;
        } catch (Exception e){
            LOGGER.error("Erro ao buscar Competicao com ID (service): {}", id, e);
            throw e;
        }

    }

    @Transactional
    public void save(Competicao competicao) {
        try {
            LOGGER.info("Criando o Competicao (service): {}");
            this.repository.save(competicao);
            LOGGER.info("Competicao criado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao criar Competicao (service): {}", e);
            throw e;
        }
        this.repository.save(competicao);
    }

    @Transactional
    public void update(Competicao competicao) {
        try {
            LOGGER.info("Atualizando Competicao (service): {}");
            this.repository.update(competicao);
            LOGGER.info("Competicao atualizado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar Competicao (service): {}", e);
            throw e;
        }
        this.repository.update(competicao);
    }

    @Transactional
    public void delete(Competicao Competicao) {
        try {
            LOGGER.info("Deletando Competicao (service): {}");
            this.repository.delete(Competicao);
            LOGGER.info("Competicao deletado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao deletar Competicao (service): {}", e);
            throw e;
        }
        this.repository.delete(Competicao);
    }

}
