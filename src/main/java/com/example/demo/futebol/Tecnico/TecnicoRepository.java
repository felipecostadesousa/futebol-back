package com.example.demo.futebol.Tecnico;

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
public class TecnicoRepository implements TecnicoDao{

    private EntityManager em;
    private static final Logger LOGGER = LoggerFactory.getLogger(TecnicoRepository.class.getName());

    @Override
    public void save(Tecnico tecnico) {
        try{
            em.persist(tecnico);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível salvar técnico" ,e.getMessage());
        }
    }

    @Override
    public void delete(Tecnico tecnico) {
        try{
            em.remove(tecnico);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível deletar técnico", e.getMessage());
        }
    }

    @Override
    public void update(Tecnico tecnico) {
        try{
            em.merge(tecnico);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível atualizar técnico", e.getMessage());
        }
    }

    @Override
    public void updateTecnicoTime(Integer idTecnico, Integer idTime) {
        String jpql = "UPDATE Tecnico tecnico SET tecnico.time.id = :id_time WHERE tecnico.id = :id_tecnico";   
    
        TypedQuery<Tecnico> query = em.createQuery(jpql, Tecnico.class);
        query.setParameter("id_time", idTime);
        query.setParameter("id_tecnico", idTecnico);

        try{
            int rowsUpdated = query.executeUpdate();
            if(rowsUpdated > 0){
                LOGGER.info("Técnico atualizado com sucesso");
            } else {
                LOGGER.error("Erro de persistência: não foi possível atualizar técnico");
            }
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível atualizar técnico", e.getMessage());
        }
    }


    @Override
    public void updateTecnicoPessoa(Integer idTecnico, Integer idPessoa) {
        String jpql = "UPDATE Tecnico tecnico SET tecnico.pessoa.id = :id_pessoa WHERE tecnico.id = :id_tecnico";   
    
        TypedQuery<Tecnico> query = em.createQuery(jpql, Tecnico.class);
        query.setParameter("id_pessoa", idPessoa);
        query.setParameter("id_tecnico", idTecnico);

        try{
            int rowsUpdated = query.executeUpdate();
            if(rowsUpdated > 0){
                LOGGER.info("Técnico atualizado com sucesso");
            } else {
                LOGGER.error("Erro de persistência: não foi possível atualizar técnico");
            }
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível atualizar técnico", e.getMessage());
        }
    }

    @Override
    public Optional<Tecnico> findById(Integer id) {
        String jpql = "SELECT tecnico FROM Tecnico tecnico WHERE tecnico.id = :id_tecnico";

        TypedQuery<Tecnico> query = em.createQuery(jpql, Tecnico.class);
        query.setParameter("id_tecnico", id);

        try{
            Tecnico tecnico = query.getSingleResult();
            return Optional.of(tecnico);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar técnico com o id {} => {}", id, e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public Optional<Tecnico> findByTime(Integer idTime) {
        String jpql = "SELECT tecnico FROM Tecnico tecnico WHERE tecnico.time.id = :id_time";

        TypedQuery<Tecnico> query = em.createQuery(jpql, Tecnico.class);
        query.setParameter("id_time", idTime);

        try{
            Tecnico tecnico = query.getSingleResult();
            return Optional.of(tecnico);
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar técnico com o id do time {} => {}", idTime, e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Tecnico> findAll() {
        String jpql = "SELECT tecnico FROM Tecnico tecnico";

        TypedQuery<Tecnico> query = em.createQuery(jpql, Tecnico.class);

        try{
            return query.getResultList();
        }catch(PersistenceException e){
            LOGGER.error("Erro de persistência: não foi possível recuperar técnicos => {}", e.getMessage());
        }

        return Collections.emptyList();
    }

}
