package com.example.demo.futebol.Entidades.Localizacao;

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
public class LocalizacaoRepository implements LocalizacaoDao{

    private EntityManager em;
    private static final Logger LOGGER = LoggerFactory.getLogger(LocalizacaoRepository.class.getName());

    @Override
    public void save(Localizacao localizacao) {
        try{
            em.persist(localizacao);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível salvar a localização", e.getMessage());
        }
    }

    @Override
    public void delete(Localizacao localizacao) {
        try{
            em.remove(localizacao);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível deletar a localização", e.getMessage());
        }
    }

    @Override
    public void update(Localizacao localizacao) {
        try{
            em.merge(localizacao);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível atualizar a localização", e.getMessage());
        }
    }

    @Override
    public Optional<Localizacao> findById(Integer id) {
        String jpql = "SELECT localizacao FROM Localizacao localizacao where localizacao.id =: id_localizacao";

        TypedQuery<Localizacao> query = em.createQuery(jpql, Localizacao.class);
        query.setParameter("id_localizacao", id);
        
        try{
            Localizacao localizacao = query.getSingleResult();
            return Optional.of(localizacao);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar a localização com o id {} => {}", id, e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Localizacao> findAll() {
        String jpql = "SELECT localizacao FROM Localizacao localizacao";

        TypedQuery<Localizacao> query = em.createQuery(jpql, Localizacao.class);

        try{
            return query.getResultList();
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar todas as localizações => {}", e.getMessage());
        }

        return Collections.emptyList();
    }

}
