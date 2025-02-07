package com.example.demo.futebol.Estatistica;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EstatisticaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EstatisticaService.class);

    private EstatisticaDao repository;

    public EstatisticaService(EstatisticaDao repository) {

        super();
        this.repository = repository;
    }

    @Transactional
    public List<Estatistica> findAll() {
        try{
            LOGGER.info("Buscando todos Estatistica (service).");
            List<Estatistica> list = this.repository.findAll();
            LOGGER.info("Tamanho da lista recuperada (service): {}", list.size());
            return list;
        }catch (Exception e){
            LOGGER.error("Erro ao buscar todos Estatistica (service).", e);
            throw e;
        }
    }

    @Transactional
    public Optional<Estatistica> findById(Integer id) {
        try{
            LOGGER.info("Buscando Estatistica com ID (service): {}", id);
            Optional<Estatistica> estatistica = this.repository.findById(id);
                if (estatistica.isPresent()) {
                    LOGGER.info("Estatistica encontrado (service): {}");
                } else {
                    LOGGER.warn("Estatistica  com ID {} não encontrado (service)", id);
                }
            return estatistica ;
        } catch (Exception e){
            LOGGER.error("Erro ao buscar Estatistica com ID (service): {}", id, e);
            throw e;
        }

    }

    @Transactional
    public void save(Estatistica estatistica) {
        try {
            LOGGER.info("Criando o Estatistica (service): {}");
            this.repository.save(estatistica);
            LOGGER.info("Estatistica criado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao criar Estatistica (service): {}", e);
            throw e;
        }
        this.repository.save(estatistica);
    }

    @Transactional
    public void update(Estatistica estatistica) {
        try {
            LOGGER.info("Atualizando Estatistica (service): {}");
            this.repository.update(estatistica);
            LOGGER.info("Estatistica atualizado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar Estatistica (service): {}", e);
            throw e;
        }
        this.repository.update(estatistica);
    }

    @Transactional
    public void delete(Estatistica estatistica) {
        try {
            LOGGER.info("Deletando Estatistica (service): {}");
            this.repository.delete(estatistica);
            LOGGER.info("Estatistica deletado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao deletar Estatistica (service): {}", e);
            throw e;
        }
        this.repository.delete(estatistica);
    }

}
