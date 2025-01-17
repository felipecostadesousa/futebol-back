package com.example.demo.futebol.Competicao;

import java.util.List;
import java.util.Optional;

public interface CompeticaoDao {

    void save(Competicao competicao);
    void delete(Competicao competicao);
    void update(Competicao competicao);
    Optional<Competicao> findById(Integer id);
    Optional<Competicao> findByName(String name);
    List<Competicao> findAll(); 

}
