package com.example.demo.futebol.Entidades.Titulo;

import java.util.List;
import java.util.Optional;

public interface TituloDao {

    void save(Titulo titulo);
    void delete(Titulo titulo);
    void update(Titulo titulo);
    Optional<Titulo> findById(Integer id);
    List<Titulo> findAll();

}
