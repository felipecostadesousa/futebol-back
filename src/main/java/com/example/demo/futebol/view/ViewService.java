package com.example.demo.futebol.view;

import org.springframework.stereotype.Service;

import com.example.demo.futebol.Jogador.JogadorDao;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class ViewService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewService.class);
    private final JogadorDao repository;

    public ViewService(JogadorDao repository) {
        this.repository = repository;
    }

    @Transactional
    public List<EstatisticasJogadoresView> findView() {
        try {
            LOGGER.info("Buscando todos estatisticas de jogadores view (service).");
            List<EstatisticasJogadoresView> list = repository.findView();
            LOGGER.info("Tamanho da lista recuperada: {}", list.size());
            return list;
        } catch (Exception e) {
            LOGGER.error("Erro ao buscar todos os estádios (service): ", e);
            throw e;
        }
    }

}