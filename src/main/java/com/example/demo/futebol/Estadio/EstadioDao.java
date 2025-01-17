package com.example.demo.futebol.Estadio;

import java.util.List;
import java.util.Optional;

public interface EstadioDao {

    void save(Estadio estadio);
    void delete(Estadio estadio);
    void update(Estadio estadio);
    Optional<Estadio> findById(Integer id);
    Optional<Estadio> findByName(String nome);
    List<Estadio> findAll();

}
