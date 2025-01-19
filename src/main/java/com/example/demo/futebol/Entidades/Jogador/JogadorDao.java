package com.example.demo.futebol.Entidades.Jogador;

import java.util.List;
import java.util.Optional;

public interface JogadorDao {

    void save(Jogador jogador);
    void delete(Jogador jogador);
    void update(Jogador jogador);
    Optional<Jogador> findById(Integer id);
    Optional<Jogador> findByName(String nome);
    List<Jogador> findAll();

}
