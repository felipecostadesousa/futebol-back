package com.example.demo.futebol.Jogo;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class JogoService {

    
    private static final Logger LOGGER = LoggerFactory.getLogger(JogoService.class);

    private JogoDao repository;

    public JogoService(JogoDao repository) {

        super();
        this.repository = repository;
    }

    @Transactional
    public List<Jogo> findAll() {
        try{
            LOGGER.info("Buscando todos Jogo (service).");
            List<Jogo> list = this.repository.findAll();
            LOGGER.info("Tamanho da lista recuperada (service): {}", list.size());
            return list;
        }catch (Exception e){
            LOGGER.error("Erro ao buscar todos Jogo (service).", e);
            throw e;
        }
    }

    @Transactional
    public Optional<Jogo> findById(Integer id) {
        try{
            LOGGER.info("Buscando Jogo com ID (service): {}", id);
            Optional<Jogo> jogo = this.repository.findById(id);
                if (jogo.isPresent()) {
                    LOGGER.info("Jogo encontrado (service): {}");
                } else {
                    LOGGER.warn("Jogo  com ID {} não encontrado (service)", id);
                }
            return jogo ;
        } catch (Exception e){
            LOGGER.error("Erro ao buscar Jogo com ID (service): {}", id, e);
            throw e;
        }

    }

    @Transactional
    public void save(Jogo jogo) {
        try {
            LOGGER.info("Criando o Jogo (service): {}");
            this.repository.save(jogo);
            LOGGER.info("Jogo criado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao criar Jogo (service): {}", e);
            throw e;
        }
        this.repository.save(jogo);
    }

    @Transactional
    public void update(Jogo jogo) {
        try {
            LOGGER.info("Atualizando Jogo (service): {}");
            this.repository.update(jogo);
            LOGGER.info("Jogo atualizado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar Jogo (service): {}", e);
            throw e;
        }
        this.repository.update(jogo);
    }

    @Transactional
    public void delete(Jogo jogo) {
        try {
            LOGGER.info("Deletando Jogo (service): {}");
            this.repository.delete(jogo);
            LOGGER.info("Jogo deletado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao deletar Jogo (service): {}", e);
            throw e;
        }
        this.repository.delete(jogo);
    }


}
