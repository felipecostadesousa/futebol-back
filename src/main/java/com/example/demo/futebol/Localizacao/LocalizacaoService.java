package com.example.demo.futebol.Localizacao;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LocalizacaoService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LocalizacaoService.class);

    private LocalizacaoDao repository;

    public LocalizacaoService(LocalizacaoDao repository) {

        super();
        this.repository = repository;
    }

    @Transactional
    public List<Localizacao> findAll() {
        try{
            LOGGER.info("Buscando todos Localizacao (service).");
            List<Localizacao> list = this.repository.findAll();
            LOGGER.info("Tamanho da lista recuperada (service): {}", list.size());
            return list;
        }catch (Exception e){
            LOGGER.error("Erro ao buscar todos Localizacao (service).", e);
            throw e;
        }
    }

    @Transactional
    public Optional<Localizacao> findById(Integer id) {
        try{
            LOGGER.info("Buscando Localizacao com ID (service): {}", id);
            Optional<Localizacao> localizacao = this.repository.findById(id);
                if (localizacao.isPresent()) {
                    LOGGER.info("Localizacao encontrado (service): {}");
                } else {
                    LOGGER.warn("Localizacao  com ID {} não encontrado (service)", id);
                }
            return localizacao ;
        } catch (Exception e){
            LOGGER.error("Erro ao buscar Localizacao com ID (service): {}", id, e);
            throw e;
        }

    }

    @Transactional
    public void save(Localizacao localizacao) {
        try {
            LOGGER.info("Criando o Localizacao (service): {}");
            this.repository.save(localizacao);
            LOGGER.info("Localizacao criado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao criar Localizacao (service): {}", e);
            throw e;
        }
        this.repository.save(localizacao);
    }

    @Transactional
    public void update(Localizacao localizacao) {
        try {
            LOGGER.info("Atualizando Localizacao (service): {}");
            this.repository.update(localizacao);
            LOGGER.info("Localizacao atualizado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar Localizacao (service): {}", e);
            throw e;
        }
        this.repository.update(localizacao);
    }

    @Transactional
    public void delete(Localizacao localizacao) {
        try {
            LOGGER.info("Deletando Localizacao (service): {}");
            this.repository.delete(localizacao);
            LOGGER.info("Localizacao deletado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao deletar Localizacao (service): {}", e);
            throw e;
        }
        this.repository.delete(localizacao);
    }

}
