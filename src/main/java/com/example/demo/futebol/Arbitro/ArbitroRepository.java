package com.example.demo.futebol.Arbitro;

import java.util.List;
import java.util.Optional;

public class ArbitroRepository implements ArbitroDao {

    @Override
    public void save(Arbitro arbitro) {
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void delete(Arbitro arbitro) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void update(Arbitro arbitro) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Optional<Arbitro> findById(Integer id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Optional<Arbitro> findByName(String nome) {
        throw new UnsupportedOperationException("Unimplemented method 'findByName'");
    }

    @Override
    public List<Arbitro> findAll() {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

}
