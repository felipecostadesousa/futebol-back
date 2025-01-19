package com.example.demo.futebol.Entidades.Pessoa;

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
public class PessoaRepository implements PessoaDao{

    private EntityManager em;
    private static final Logger LOGGER = LoggerFactory.getLogger(PessoaRepository.class.getName());

    @Override
    public void save(Pessoa pessoa) {
        try{
            em.persist(pessoa);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível criar pessoa", e.getMessage());    
        }
    }

    @Override
    public void delete(Pessoa pessoa) {
        try{
            em.remove(pessoa);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível deletar pessoa", e.getMessage());
        }
    }

    @Override
    public void update(Pessoa pessoa) {
        try{
            em.merge(pessoa);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível atualizar pessoa", e.getMessage());
        }
    }

    @Override
    public Optional<Pessoa> findById(Integer id) {
        String jpql = "SELECT pessoa FROM Pessoa pessoa WHERE pessoa.id = :id_pessoa";

        TypedQuery<Pessoa> query = em.createQuery(jpql, Pessoa.class);
        query.setParameter("id_pessoa", id);

        try{
            Pessoa pessoa = query.getSingleResult();
            return Optional.of(pessoa);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar pessoa com o id {} => {}", id, e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Pessoa> findAll() {
        String jpql = "SELECT pessoa FROM Pessoa pessoa";

        TypedQuery<Pessoa> query = em.createQuery(jpql, Pessoa.class);

        try{
            List<Pessoa> pessoas = query.getResultList();
            return pessoas;
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar pessoas => {}", e.getMessage());
        }

        return Collections.emptyList();
    }

}
