package com.example.demo.futebol.Pessoa;

import java.util.List;
import java.util.Optional;

public interface PessoaDao {

    void save(Pessoa pessoa);
    void delete(Pessoa pessoa);
    void update(Pessoa pessoa);
    Optional<Pessoa> findById(Integer id);
    List<Pessoa> findAll();

}
