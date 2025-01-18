package com.example.demo.futebol.JogoTime;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

@Repository
public class JogoTimeRepository implements JogoTimeDao{

    private EntityManager em;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JogoTimeRepository.class.getName());

    @Override
    public void save(JogoTime jogoTime) {
        try{
            em.persist(jogoTime);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível criar jogoTime", e.getMessage());
        }
    }

    @Override
    public void delete(JogoTime jogoTime) {
        try{
            em.remove(jogoTime);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível deletar jogoTime", e.getMessage());
        }
    }

    @Override
    public void update(JogoTime jogoTime) {
        try{
            em.merge(jogoTime);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível atualizar jogoTime", e.getMessage());
        }
    }

    @Override
    public Optional<JogoTime> findById(Integer id) {
        String jpql = "SELECT jogo_time FROM Jogo_Time jogo_time WHERE jogo_time.id = :id_jogo_time";

        TypedQuery<JogoTime> query = em.createQuery(jpql, JogoTime.class);
        query.setParameter("id_jogo_time", id);

        try{
            JogoTime jogoTime = query.getSingleResult();
            return Optional.of(jogoTime);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar jogoTime com o id {} => {}", id, e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public Optional<JogoTime> findByTime(Integer idTime) {
        String jpql = "SELECT jogo_time FROM Jogo_Time jogo_time WHERE jogo_time.time_id = :id_time";

        TypedQuery<JogoTime> query = em.createQuery(jpql, JogoTime.class);
        query.setParameter("id_time", idTime);

        try{
            JogoTime jogoTime = query.getSingleResult();
            return Optional.of(jogoTime);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar jogoTime com o id do time {} => {}", idTime, e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<JogoTime> findAll() {
        String jpql = "SELECT jogo_time FROM Jogo_Time jogo_time";

        TypedQuery<JogoTime> query = em.createQuery(jpql, JogoTime.class);

        try{
            query.getSingleResult();
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar jogosTimes => {}", e.getMessage());
        }

        return Collections.emptyList();
    }

}
