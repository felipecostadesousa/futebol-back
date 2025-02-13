package com.example.demo.futebol.Competicao;

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
public class CompeticaoRepository implements CompeticaoDao{

    private EntityManager em;
    private static final Logger LOGGER = LoggerFactory.getLogger(CompeticaoRepository.class.getName());

    public CompeticaoRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public void save(Competicao competicao) {
        try {
            em.persist(competicao);
        } catch (Exception e) {
            LOGGER.error("Erro de persistência: não foi possível criar competição", e.getMessage());
        }
    }

    @Override
    public void delete(Competicao competicao) {
        try {
            em.remove(competicao);
        } catch (Exception e) {
            LOGGER.error("Erro de persistência: não foi possível deletar competição", e.getMessage());
        }
    }

    @Override
    public void update(Competicao competicao) {
        try {
            em.merge(competicao);
        } catch (Exception e) {
            LOGGER.error("Erro de persistência: não foi possível atualizar competição", e.getMessage());
        }
    }

    @Override
    public Optional<Competicao> findById(Integer id) {
        String jpql = "SELECT competicao FROM Competicao competicao where arbitro_id =: id_arbitro";

        TypedQuery<Competicao> query = em.createQuery(jpql, Competicao.class);
        query.setParameter("id_arbitro", id);

        try{
            Competicao competicao = query.getSingleResult();
            return Optional.of(competicao);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar competição com o id {} => {}", id, e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public Optional<Competicao> findByName(String name) {
        String jpql = "SELECT competicao FROM Competicao competicao where competicao.nome =: nome_competicao";

        TypedQuery<Competicao> query = em.createQuery(jpql, Competicao.class);
        query.setParameter("nome_competicao", name);

        try{
            Competicao competicao = query.getSingleResult();
            return Optional.of(competicao);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar competição com o nome {} => {}", name, e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Competicao> findAll() {
        String jpql = "SELECT competicao FROM Competicao competicao";

        TypedQuery<Competicao> query = em.createQuery(jpql, Competicao.class);
      
        try{
            return query.getResultList();
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar a lista de competições", e.getMessage());
        }

        return Collections.emptyList();
    }

}
