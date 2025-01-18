package com.example.demo.futebol.Titulo;

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
public class TituloRepository implements TituloDao{

    private EntityManager em;
    private static final Logger LOGGER = LoggerFactory.getLogger(TituloRepository.class.getName());

    @Override
    public void save(Titulo titulo) {
        try{
            em.persist(titulo);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível criar título", e.getMessage());
        }
    }

    @Override
    public void delete(Titulo titulo) {
        try{
            em.remove(titulo);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível deletar título", e.getMessage());
        }
    }

    @Override
    public void update(Titulo titulo) {
        try{
            em.merge(titulo);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível atualizar título", e.getMessage());
        }
    }

    @Override
    public Optional<Titulo> findById(Integer id) {
        String jpql = "SELECT titulo FROM Titulo titulo WHERE titulo.id = :id_titulo";

        TypedQuery<Titulo> query = em.createQuery(jpql, Titulo.class);
        query.setParameter("id_titulo", id);

        try{
            Titulo titulo = query.getSingleResult();
            return Optional.of(titulo);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar título com o id {} => {}", id, e.getMessage());
        }

        return Optional.empty();
        
    }

    @Override
    public List<Titulo> findAll() {
        String jpql = "SELECT titulo FROM Titulo titulo";

        TypedQuery<Titulo> query = em.createQuery(jpql, Titulo.class);

        try{
            return query.getResultList();
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar títulos => {}", e.getMessage());
        }

        return Collections.emptyList();
    }

}
