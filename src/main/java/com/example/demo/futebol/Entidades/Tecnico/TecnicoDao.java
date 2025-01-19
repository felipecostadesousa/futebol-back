package com.example.demo.futebol.Entidades.Tecnico;

import java.util.List;
import java.util.Optional;

public interface TecnicoDao {

    void save(Tecnico tecnico);
    void delete(Tecnico tecnico);
    void update(Tecnico tecnico);
    void updateTecnicoTime(Integer idTecnico, Integer idTime);
    void updateTecnicoPessoa(Integer idTecnico, Integer idPessoa);
    Optional<Tecnico> findById(Integer id);
    Optional<Tecnico> findByTime(Integer idTime);
    List<Tecnico> findAll();
    
}
