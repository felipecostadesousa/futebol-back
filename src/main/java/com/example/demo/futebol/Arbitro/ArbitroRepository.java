package com.example.demo.futebol.Arbitro;

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
public class ArbitroRepository implements ArbitroDao {

    private EntityManager em;
    private static final Logger LOGGER = LoggerFactory.getLogger(ArbitroRepository.class);

    public ArbitroRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Arbitro arbitro) {
        try{
            this.em.persist(arbitro);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência ao salvar arbitro", e.getMessage());
        }
    }

    @Override
    public void delete(Arbitro arbitro) {
        try{
            this.em.remove(arbitro);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência ao deletar arbitro", e.getMessage());
        }
    }

    @Override
    public void update(Arbitro arbitro) {
        try{
            this.em.merge(arbitro);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência ao atualizar arbitro", e.getMessage());
        }
    }

    @Override
    public Optional<Arbitro> findById(Integer id) {
        String jpql = "SELECT arbitro FROM Arbitro arbitro WHERE arbitro.id = :id_arbitro";

        TypedQuery<Arbitro> query = em.createQuery(jpql, Arbitro.class);
        query.setParameter("id_arbitro", id);

        try{
            Arbitro arbitro = query.getSingleResult();
            return Optional.of(arbitro);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência ao recuperar arbitro com id {} => {}", id, e.getMessage());
        }
        
        return Optional.empty();
    }

    @Override
    public Optional<Arbitro> findByName(String nome) {
        String jpql = "SELECT arbitro FROM Arbitro arbitro WHERE arbitro.pessoa.nome = :nome_arbitro";

        TypedQuery<Arbitro> query = em.createQuery(jpql, Arbitro.class);
        query.setParameter("nome_arbitro", nome);

        try{
            Arbitro arbitro = query.getSingleResult();
            return Optional.of(arbitro);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência ao recuperar arbitro com nome {} => {}", nome, e.getMessage());
        }
        
        return Optional.empty();
    }

    @Override
    public List<Arbitro> findAll() {
        String jpql = "SELECT arbitro FROM Arbitro arbitro";

        TypedQuery<Arbitro> query = em.createQuery(jpql, Arbitro.class);

        try{
            return query.getResultList();
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência ao recuperar todos os arbitros => {}", e.getMessage());
        }

        return Collections.emptyList();
    }

}
