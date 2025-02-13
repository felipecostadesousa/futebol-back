package com.example.demo.futebol.Jogador;

import java.util.List;
import java.util.Optional;


import com.example.demo.futebol.view.EstatisticasJogadoresView;

import jakarta.persistence.PersistenceException;

public interface JogadorDao {

    void save(Jogador jogador);
    void delete(Jogador jogador);
    void update(Jogador jogador);
    void updateTime(Integer jogadorId, Integer timeId);
    Optional<Jogador> findById(Integer id);
    Optional<Jogador> findByName(String nome);
    List<Jogador> findAll();
    List<EstatisticasJogadoresView> findView();
    void trocarTime(Integer id_jogador, Integer id_time_destino) throws PersistenceException;

}
