package com.example.demo.futebol.Jogo;

import java.util.List;
import java.util.Optional;

public interface JogoDao {

    void save(Jogo jogo);
    void delete(Jogo jogo);
    void update(Jogo jogo);
    Optional<Jogo> findById(Integer id);
    List<Jogo> findAll();

}
