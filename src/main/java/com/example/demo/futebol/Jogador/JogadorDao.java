package com.example.demo.futebol.Jogador;

import java.util.List;
import java.util.Optional;

import com.example.demo.futebol.Time.Time;

public interface JogadorDao {

    void save(Jogador jogador);
    void delete(Jogador jogador);
    void update(Jogador jogador);
    void updateTime(Integer jogadorId, Integer timeId);
    Optional<Jogador> findById(Integer id);
    Optional<Jogador> findByName(String nome);
    List<Jogador> findAll();

}
