package com.example.demo.futebol.Entidades.Time;

import java.util.List;
import java.util.Optional;

public interface TimeDao {

    void save(Time time);
    void delete(Time time);
    void update(Time time); 
    Optional<Time> findById(Integer id);
    Optional<Time> findByName(String name);
    List<Time> findAll();

}
