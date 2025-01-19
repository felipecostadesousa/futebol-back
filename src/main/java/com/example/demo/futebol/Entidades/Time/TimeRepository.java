package com.example.demo.futebol.Entidades.Time;

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
public class TimeRepository implements TimeDao{

    private EntityManager em;
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeRepository.class.getName());

    @Override
    public void save(Time time) {
        try{
            em.persist(time);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível salvar time", e.getMessage());
        }
    }

    @Override
    public void delete(Time time) {
        try{
            em.remove(time);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível deletar time", e.getMessage());
        }
    }

    @Override
    public void update(Time time) {
        try{
            em.merge(time);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível atualizar time", e.getMessage());
        }
    }

    @Override
    public Optional<Time> findById(Integer id) {
        String jpql = "SELECT time FROM Time time WHERE time.id = :id_time";

        TypedQuery<Time> query = em.createQuery(jpql, Time.class);
        query.setParameter("id_time", id);

        try{
            Time time = query.getSingleResult();
            return Optional.of(time);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar time com o id {} => {}", id, e.getMessage());
        }
        
        return Optional.empty();
    }

    @Override
    public Optional<Time> findByName(String name) {
        String jpql = "SELECT time FROM Time time WHERE time.nome = :nome_time";

        TypedQuery<Time> query = em.createQuery(jpql, Time.class);
        query.setParameter("nome_time", name);

        try{
            Time time = query.getSingleResult();
            return Optional.of(time);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar time com o nome {} => {}", name, e.getMessage());
        }
        
        return Optional.empty();
    }

    @Override
    public List<Time> findAll() {
        String jpql = "SELECT time FROM Time time";

        TypedQuery<Time> query = em.createQuery(jpql, Time.class);

        try{
            return query.getResultList();
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar times => {}", e.getMessage());
        }

        return Collections.emptyList();
    }

}
