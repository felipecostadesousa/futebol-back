package com.example.demo.futebol.Tecnico;

import java.util.List;
import java.util.Optional;

public class TecnicoRepository implements TecnicoDao{

    @Override
    public void save(Tecnico tecnico) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void delete(Tecnico tecnico) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void update(Tecnico tecnico) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void updateTecnicoTime(Integer idTecnico, Integer idTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTecnicoTime'");
    }

    @Override
    public void updateTecnicoPessoa(Integer idTecnico, Integer idPessoa) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTecnicoPessoa'");
    }

    @Override
    public Optional<Tecnico> findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Optional<Tecnico> findByTime(Integer idTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByTime'");
    }

    @Override
    public List<Tecnico> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

}
