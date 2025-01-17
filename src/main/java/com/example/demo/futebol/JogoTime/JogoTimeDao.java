package com.example.demo.futebol.JogoTime;

import java.util.List;
import java.util.Optional;

public interface JogoTimeDao {

    void save(JogoTime jogoTime);
    void delete(JogoTime jogoTime);
    void update(JogoTime jogoTime);
    Optional<JogoTime> findById(Integer id);
    Optional<JogoTime> findByTime(Integer idTime);
    List<JogoTime> findAll();

}
