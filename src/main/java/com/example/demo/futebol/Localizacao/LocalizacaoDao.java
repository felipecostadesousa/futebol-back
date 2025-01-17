package com.example.demo.futebol.Localizacao;

import java.util.List;
import java.util.Optional;

public interface LocalizacaoDao {

    void save(Localizacao localizacao);
    void delete(Localizacao localizacao);
    void update(Localizacao localizacao);
    Optional<Localizacao> findById(Integer id);
    List<Localizacao> findAll();

}
