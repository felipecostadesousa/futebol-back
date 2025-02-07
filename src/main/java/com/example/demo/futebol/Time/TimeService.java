package com.example.demo.futebol.Time;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TimeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeService.class);

    private TimeDao repository;

    public TimeService(TimeDao repository) {

        super();
        this.repository = repository;
    }

    @Transactional
    public List<Time> findAll() {
        try{
            LOGGER.info("Buscando todos Time (service).");
            List<Time> list = this.repository.findAll();
            LOGGER.info("Tamanho da lista recuperada (service): {}", list.size());
            return list;
        }catch (Exception e){
            LOGGER.error("Erro ao buscar todos Time (service).", e);
            throw e;
        }
    }

    @Transactional
    public Optional<Time> findById(Integer id) {
        try{
            LOGGER.info("Buscando Time com ID (service): {}", id);
            Optional<Time> time = this.repository.findById(id);
                if (time.isPresent()) {
                    LOGGER.info("Time encontrado (service): {}");
                } else {
                    LOGGER.warn("Time  com ID {} não encontrado (service)", id);
                }
            return time ;
        } catch (Exception e){
            LOGGER.error("Erro ao buscar Time com ID (service): {}", id, e);
            throw e;
        }

    }

    @Transactional
    public void save(Time time) {
        try {
            LOGGER.info("Criando o Time (service): {}");
            this.repository.save(time);
            LOGGER.info("Time criado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao criar Time (service): {}", e);
            throw e;
        }
        this.repository.save(time);
    }

    @Transactional
    public void update(Time time) {
        try {
            LOGGER.info("Atualizando Time (service): {}");
            this.repository.update(time);
            LOGGER.info("Time atualizado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar Time (service): {}", e);
            throw e;
        }
        this.repository.update(time);
    }

    @Transactional
    public void delete(Time time) {
        try {
            LOGGER.info("Deletando Time (service): {}");
            this.repository.delete(time);
            LOGGER.info("Time deletado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao deletar Time (service): {}", e);
            throw e;
        }
        this.repository.delete(time);
    }


}
