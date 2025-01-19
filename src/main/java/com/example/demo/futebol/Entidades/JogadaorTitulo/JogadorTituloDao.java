package com.example.demo.futebol.Entidades.JogadaorTitulo;

import java.util.List;
import java.util.Optional;

public interface JogadorTituloDao {

    void save(JogadorTitulo jogadorTitulo);
    void delete(JogadorTitulo jogadorTitulo);
    void update(JogadorTitulo jogadorTitulo);
    Optional<JogadorTitulo> findById(Integer id);
    List<JogadorTitulo> findAll();
    Optional<JogadorTitulo> findByJogadorId(Integer jogadorId);
    Optional<JogadorTitulo> findByTituloId(Integer tituloId);

}
