package com.example.demo.futebol.JogoTime;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class JogoTimeService {

    
    private static final Logger LOGGER = LoggerFactory.getLogger(JogoTimeService.class);

    private JogoTimeDao repository;

    public JogoTimeService(JogoTimeDao repository) {

        super();
        this.repository = repository;
    }

    @Transactional
    public List<JogoTime> findAll() {
        try{
            LOGGER.info("Buscando todos JogoTime (service).");
            List<JogoTime> list = this.repository.findAll();
            LOGGER.info("Tamanho da lista recuperada (service): {}", list.size());
            return list;
        }catch (Exception e){
            LOGGER.error("Erro ao buscar todos JogoTime (service).", e);
            throw e;
        }
    }

    @Transactional
    public Optional<JogoTime> findById(Integer id) {
        try{
            LOGGER.info("Buscando JogoTime com ID (service): {}", id);
            Optional<JogoTime> jogoTime = this.repository.findById(id);
                if (jogoTime.isPresent()) {
                    LOGGER.info("JogoTime encontrado (service): {}");
                } else {
                    LOGGER.warn("JogoTime  com ID {} não encontrado (service)", id);
                }
            return jogoTime ;
        } catch (Exception e){
            LOGGER.error("Erro ao buscar JogoTime com ID (service): {}", id, e);
            throw e;
        }

    }

    @Transactional
    public void save(JogoTime jogoTime) {
        try {
            LOGGER.info("Criando o JogoTime (service): {}");
            this.repository.save(jogoTime);
            LOGGER.info("JogoTime criado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao criar JogoTime (service): {}", e);
            throw e;
        }
        this.repository.save(jogoTime);
    }

    @Transactional
    public void update(JogoTime jogoTime) {
        try {
            LOGGER.info("Atualizando JogoTime (service): {}");
            this.repository.update(jogoTime);
            LOGGER.info("JogoTime atualizado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar JogoTime (service): {}", e);
            throw e;
        }
        this.repository.update(jogoTime);
    }

    @Transactional
    public void delete(JogoTime jogoTime) {
        try {
            LOGGER.info("Deletando JogoTime (service): {}");
            this.repository.delete(jogoTime);
            LOGGER.info("JogoTime deletado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao deletar JogoTime (service): {}", e);
            throw e;
        }
        this.repository.delete(jogoTime);
    }

}
