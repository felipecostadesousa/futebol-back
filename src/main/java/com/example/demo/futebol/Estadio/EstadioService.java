package com.example.demo.futebol.Estadio;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EstadioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EstadioService.class);
    private final EstadioDao repository;

    public EstadioService(EstadioDao repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Estadio> findAll() {
        try {
            LOGGER.info("Buscando todos os estádios (service).");
            List<Estadio> list = repository.findAll();
            LOGGER.info("Tamanho da lista recuperada: {}", list.size());
            return list;
        } catch (Exception e) {
            LOGGER.error("Erro ao buscar todos os estádios (service): ", e);
            throw e;
        }
    }

    @Transactional
    public Optional<Estadio> findById(Integer id) {
        try {
            LOGGER.info("Buscando estádio com ID: {}", id);
            Optional<Estadio> estadio = repository.findById(id);
            if (estadio.isPresent()) {
                LOGGER.info("Estádio encontrado: {}", estadio.get());
            } else {
                LOGGER.warn("Estádio com ID {} não encontrado.", id);
            }
            return estadio;
        } catch (Exception e) {
            LOGGER.error("Erro ao buscar estádio com ID {}: ", id, e);
            throw e;
        }
    }

    @Transactional
    public void save(Estadio estadio) {
        try {
            LOGGER.info("Criando o estádio: {}", estadio);
            repository.save(estadio);
            LOGGER.info("Estádio criado com sucesso: {}", estadio);
        } catch (Exception e) {
            LOGGER.error("Erro ao criar estádio: ", e);
            throw e;
        }
    }

    @Transactional
    public void update(Estadio estadio) {
        try {
            LOGGER.info("Atualizando estádio com ID: {}", estadio.getId());
            repository.save(estadio); // `save()` já trata inserção/atualização
            LOGGER.info("Estádio atualizado com sucesso: {}", estadio);
        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar estádio: ", e);
            throw e;
        }
    }

    @Transactional
    public void delete(Estadio estadio) {
        try {
            LOGGER.info("Deletando estádio com ID: {}", estadio.getId());
            this.repository.delete(estadio);
            LOGGER.info("Estádio deletado com sucesso!");
        } catch (Exception e) {
            LOGGER.error("Erro ao deletar estadio com ID {}: {}", estadio.getId(), e.getMessage());
            throw e;
        }
        this.repository.delete(estadio);
    }
}