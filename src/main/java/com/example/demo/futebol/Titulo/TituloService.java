package com.example.demo.futebol.Titulo;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TituloService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TituloService.class);

    private TituloDao repository;

    public TituloService(TituloDao repository) {

        super();
        this.repository = repository;
    }

    @Transactional
    public List<Titulo> findAll() {
        try{
            LOGGER.info("Buscando todos Titulo (service).");
            List<Titulo> list = this.repository.findAll();
            LOGGER.info("Tamanho da lista recuperada (service): {}", list.size());
            return list;
        }catch (Exception e){
            LOGGER.error("Erro ao buscar todos Titulo (service).", e);
            throw e;
        }
    }

    @Transactional
    public Optional<Titulo> findById(Integer id) {
        try{
            LOGGER.info("Buscando Titulo com ID (service): {}", id);
            Optional<Titulo> titulo = this.repository.findById(id);
                if (titulo.isPresent()) {
                    LOGGER.info("Titulo encontrado (service): {}");
                } else {
                    LOGGER.warn("Titulo  com ID {} não encontrado (service)", id);
                }
            return titulo ;
        } catch (Exception e){
            LOGGER.error("Erro ao buscar Titulo com ID (service): {}", id, e);
            throw e;
        }

    }

    @Transactional
    public void save(Titulo titulo) {
        try {
            LOGGER.info("Criando o Titulo (service): {}");
            this.repository.save(titulo);
            LOGGER.info("Titulo criado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao criar Titulo (service): {}", e);
            throw e;
        }
        this.repository.save(titulo);
    }

    @Transactional
    public void update(Titulo titulo) {
        try {
            LOGGER.info("Atualizando Titulo (service): {}");
            this.repository.update(titulo);
            LOGGER.info("Titulo atualizado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar Titulo (service): {}", e);
            throw e;
        }
        this.repository.update(titulo);
    }

    @Transactional
    public void delete(Titulo titulo) {
        try {
            LOGGER.info("Deletando Titulo (service): {}");
            this.repository.delete(titulo);
            LOGGER.info("Titulo deletado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao deletar Titulo (service): {}", e);
            throw e;
        }
        this.repository.delete(titulo);
    }

}
