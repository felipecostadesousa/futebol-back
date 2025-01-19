package com.example.demo.futebol.Entidades.Estatistica;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

@Repository
public class EstatisticaRepository implements EstatisticaDao{

    private EntityManager em;

    private static final Logger LOGGER = LoggerFactory.getLogger(EstatisticaRepository.class.getName());

    @Override
    public void save(Estatistica estatistica) {
        try{
            em.persist(estatistica);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível criar estatística", e.getMessage());
        }
    }

    @Override
    public void delete(Estatistica estatistica) {
        try{
            em.remove(estatistica);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível deletar estatística", e.getMessage());
        }
    }

    @Override
    public void update(Estatistica estatistica) {
        try{
            em.merge(estatistica);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível atualizar estatística", e.getMessage());
        }
    }

    @Override
    public Optional<Estatistica> findById(Integer id) {
        String jpql = "SELECT estatistica FROM Estatistica estatistica where estatistica.id =: id_estatistica";

        TypedQuery <Estatistica> query = em.createQuery(jpql, Estatistica.class);
        query.setParameter("id_estatistica", id);

        try{
            Estatistica estatistica = query.getSingleResult();
            return Optional.of(estatistica);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar estatística com o id {} => {}", id, e.getMessage());
        }
        
        return Optional.empty();
    }

    @Override
    public List<Estatistica> findAll() {
        String jpql = "SELECT estatistica FROM Estatistica estatistica";

        TypedQuery <Estatistica> query = em.createQuery(jpql, Estatistica.class);

        try{
            return query.getResultList();
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar estatísticas => {}", e.getMessage());
        }

        return Collections.emptyList();
    }

}
