package com.example.demo.futebol.Arbitro;

import java.util.List;
import java.util.Optional;

public interface ArbitroDao {

    void save(Arbitro arbitro);
    void delete(Arbitro arbitro);
    void update(Arbitro arbitro);
    Optional<Arbitro> findById(Integer id);
    Optional<Arbitro> findByName(String nome);
    List<Arbitro>findAll();

}
