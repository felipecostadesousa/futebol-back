package com.example.demo.futebol.Jogador;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class JogadorService {

    
    private static final Logger LOGGER = LoggerFactory.getLogger(JogadorService.class);

    private JogadorDao repository;

    public JogadorService(JogadorDao repository) {

        super();
        this.repository = repository;
    }

    @Transactional
    public List<Jogador> findAll() {
        try{
            LOGGER.info("Buscando todos Jogador (service).");
            List<Jogador> list = this.repository.findAll();
            LOGGER.info("Tamanho da lista recuperada (service): {}", list.size());
            return list;
        }catch (Exception e){
            LOGGER.error("Erro ao buscar todos Jogador (service).", e);
            throw e;
        }
    }

    @Transactional
    public Optional<Jogador> findById(Integer id) {
        try{
            LOGGER.info("Buscando Jogador com ID (service): {}", id);
            Optional<Jogador> jogador = this.repository.findById(id);
                if (jogador.isPresent()) {
                    LOGGER.info("Jogador encontrado (service): {}");
                } else {
                    LOGGER.warn("Jogador  com ID {} não encontrado (service)", id);
                }
            return jogador ;
        } catch (Exception e){
            LOGGER.error("Erro ao buscar Jogador com ID (service): {}", id, e);
            throw e;
        }

    }

    @Transactional
    public void save(Jogador jogador) {
        try {
            LOGGER.info("Criando o Jogador (service): {}");
            this.repository.save(jogador);
            LOGGER.info("Jogador criado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao criar Jogador (service): {}", e);
            throw e;
        }
        this.repository.save(jogador);
    }

    @Transactional
    public void update(Jogador jogador) {
        try {
            LOGGER.info("Atualizando Jogador (service): {}");
            this.repository.update(jogador);
            LOGGER.info("Jogador atualizado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar Jogador (service): {}", e);
            throw e;
        }
        this.repository.update(jogador);
    }

    @Transactional
    public void delete(Jogador jogador) {
        try {
            LOGGER.info("Deletando Jogador (service): {}");
            this.repository.delete(jogador);
            LOGGER.info("Jogador deletado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao deletar Jogador (service): {}", e);
            throw e;
        }
        this.repository.delete(jogador);
    }


}
