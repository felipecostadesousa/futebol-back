package com.example.demo.futebol.JogadaorTitulo;

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
public class JogadorTituloRepository implements JogadorTituloDao{

    private EntityManager em;
    private static final Logger LOGGER = LoggerFactory.getLogger(JogadorTituloRepository.class.getName());

    @Override
    public void save(JogadorTitulo jogadorTitulo) {
        try{
            em.persist(jogadorTitulo);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível criar jogador_titulo", e.getMessage());
        }
    }

    @Override
    public void delete(JogadorTitulo jogadorTitulo) {
        try{
            em.remove(jogadorTitulo);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível deletar jogador_titulo", e.getMessage());
        }
    }

    @Override
    public void update(JogadorTitulo jogadorTitulo) {
        try{
            em.merge(jogadorTitulo);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível atualizar jogador_titulo", e.getMessage());
        }
    }

    @Override
    public Optional<JogadorTitulo> findById(Integer id) {
        String jpql = "SELECT jogador_titulo FROM JogadorTitulo jogador_titulo where jogador_titulo.id =: id_jogador_titulo";
        
        TypedQuery<JogadorTitulo> query = em.createQuery(jpql, JogadorTitulo.class);
        query.setParameter("id_jogador_titulo", id);

        try{
            JogadorTitulo jogadorTitulo = query.getSingleResult();
            return Optional.of(jogadorTitulo);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar jogador_titulo com o id {} => {}", id, e.getMessage());
        }

        return Optional.empty();

    }

    @Override
    public List<JogadorTitulo> findAll() {
        String jpql = "SELECT jogador_titulo FROM JogadorTitulo jogador_titulo";

        TypedQuery<JogadorTitulo> query = em.createQuery(jpql, JogadorTitulo.class);

        try{
            return query.getResultList();
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar todos os jogadores_titulos => {}", e.getMessage());
        }

        return Collections.emptyList();
    }

    @Override
    public Optional<JogadorTitulo> findByJogadorId(Integer jogadorId) {
        String jpql = "SELECT jogador_titulo FROM JogadorTitulo jogador_titulo where jogador_id =: id_jogador";
        
        TypedQuery<JogadorTitulo> query = em.createQuery(jpql, JogadorTitulo.class);
        query.setParameter("id_jogador", jogadorId);

        try{
            JogadorTitulo jogadorTitulo = query.getSingleResult();
            return Optional.of(jogadorTitulo);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar jogador_titulo com o id {} => {}", jogadorId, e.getMessage());
        }
       
        return Optional.empty();
    }

    @Override
    public Optional<JogadorTitulo> findByTituloId(Integer tituloId) {
        String jpql = "SELECT jogador_titulo FROM JogadorTitulo jogador_titulo where titulo_id =: id_titulo";
        
        TypedQuery<JogadorTitulo> query = em.createQuery(jpql, JogadorTitulo.class);
        query.setParameter("id_titulo", tituloId);

        try{
            JogadorTitulo jogadorTitulo = query.getSingleResult();
            return Optional.of(jogadorTitulo);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar jogador_titulo com o id {} => {}", tituloId, e.getMessage());
        }
        
        return Optional.empty();
    }

}
