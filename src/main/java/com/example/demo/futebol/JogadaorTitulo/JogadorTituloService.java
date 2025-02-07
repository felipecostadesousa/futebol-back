package com.example.demo.futebol.JogadaorTitulo;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class JogadorTituloService {

    
    private static final Logger LOGGER = LoggerFactory.getLogger(JogadorTituloService.class);

    private JogadorTituloDao repository;

    public JogadorTituloService(JogadorTituloDao repository) {
        super();
        this.repository = repository;
    }

    @Transactional
    public List<JogadorTitulo> findAll() {
        try{
            LOGGER.info("Buscando todos JogadorTitulo (service).");
            List<JogadorTitulo> list = this.repository.findAll();
            LOGGER.info("Tamanho da lista recuperada (service): {}", list.size());
            return list;
        }catch (Exception e){
            LOGGER.error("Erro ao buscar todos JogadorTitulo (service).", e);
            throw e;
        }
    }

    @Transactional
    public Optional<JogadorTitulo> findById(Integer id) {
        try{
            LOGGER.info("Buscando JogadorTitulo com ID (service): {}", id);
            Optional<JogadorTitulo> jogadorTitulo = this.repository.findById(id);
                if (jogadorTitulo.isPresent()) {
                    LOGGER.info("JogadorTitulo encontrado (service): {}");
                } else {
                    LOGGER.warn("JogadorTitulo  com ID {} não encontrado (service)", id);
                }
            return jogadorTitulo ;
        } catch (Exception e){
            LOGGER.error("Erro ao buscar JogadorTitulo com ID (service): {}", id, e);
            throw e;
        }

    }

    @Transactional
    public void save(JogadorTitulo jogadorTitulo) {
        try {
            LOGGER.info("Criando o JogadorTitulo (service): {}");
            this.repository.save(jogadorTitulo);
            LOGGER.info("JogadorTitulo criado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao criar JogadorTitulo (service): {}", e);
            throw e;
        }
        this.repository.save(jogadorTitulo);
    }

    @Transactional
    public void update(JogadorTitulo jogadorTitulo) {
        try {
            LOGGER.info("Atualizando JogadorTitulo (service): {}");
            this.repository.update(jogadorTitulo);
            LOGGER.info("JogadorTitulo atualizado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar JogadorTitulo (service): {}", e);
            throw e;
        }
        this.repository.update(jogadorTitulo);
    }

    @Transactional
    public void delete(JogadorTitulo jogadorTitulo) {
        try {
            LOGGER.info("Deletando JogadorTitulo (service): {}");
            this.repository.delete(jogadorTitulo);
            LOGGER.info("JogadorTitulo deletado com sucesso (service): {}");
        } catch (Exception e) {
            LOGGER.error("Erro ao deletar JogadorTitulo (service): {}", e);
            throw e;
        }
        this.repository.delete(jogadorTitulo);
    }

}
