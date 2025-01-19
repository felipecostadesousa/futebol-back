package com.example.demo.futebol.Entidades.Estatistica;

import java.util.List;
import java.util.Optional;

public interface EstatisticaDao {

    void save(Estatistica estatistica);
    void delete(Estatistica estatistica);
    void update(Estatistica estatistica);
    Optional<Estatistica> findById(Integer id);
    List<Estatistica> findAll();

}
