package com.example.demo.futebol.Entidades.Jogo;

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
public class JogoRepository implements JogoDao{

    private EntityManager em;
    private static final Logger LOGGER = LoggerFactory.getLogger(JogoRepository.class.getName());

    @Override
    public void save(Jogo jogo) {
        try{
            em.persist(jogo);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível criar jogo", e.getMessage());
        }   
    }

    @Override
    public void delete(Jogo jogo) {
        try{
            em.remove(jogo);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível deletar jogo", e.getMessage());
        }
    }

    @Override
    public void update(Jogo jogo) {
        try{
            em.merge(jogo);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível atualizar jogo", e.getMessage());
        }
    }

    @Override
    public Optional<Jogo> findById(Integer id) {
        String jpql = "SELECT jogo FROM Jogo jogo WHERE jogo.id = :id_jogo";

        TypedQuery<Jogo> query = em.createQuery(jpql, Jogo.class);
        query.setParameter("id_jogo", id);

        try{
            Jogo jogo = query.getSingleResult();
            return Optional.of(jogo);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar jogo com o id {} => {}", id, e.getMessage());
        }
        
        return Optional.empty();
    }

    @Override
    public List<Jogo> findAll() {
        String jpql = "SELECT jogo FROM Jogo jogo";

        TypedQuery<Jogo> query = em.createQuery(jpql, Jogo.class);
        
        try{
            query.getResultList();
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar jogos => {}", e.getMessage());
        }

        return Collections.emptyList();
    }

}
