package com.example.demo.futebol.Estadio;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class EstadioRepository implements EstadioDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EstadioRepository.class);

    @PersistenceContext
    private EntityManager em;

    public EstadioRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    @Override
    public void save(Estadio estadio) {
        try {
            em.persist(estadio);
            LOGGER.info("Estádio salvo com sucesso: {}", estadio);
        } catch (PersistenceException e) {
            LOGGER.error("Erro de persistência: não foi possível criar estádio", e);
            throw e;  // Relança a exceção para que o Spring possa tratá-la corretamente
        }
    }

    @Transactional
    @Override
    public void delete(Estadio estadio) {
      try {
        LOGGER.info("Removendo estadio do banco de dados: {}", estadio.getId());
        em.remove(estadio);
        } catch (PersistenceException e) {
            LOGGER.error("Erro de persistência: não foi possível deletar o estadio com ID {} => {}", estadio.getId(), e.getMessage());
          throw e;
        }
    }
    @Override
    public void update(Estadio estadio) {
      try {
          Estadio existente = em.find(Estadio.class, estadio.getId());
          if (existente != null) {
              em.merge(estadio);
              LOGGER.info("Estádio atualizado com sucesso: {}", estadio);
          } else {
              LOGGER.warn("Estádio com ID {} não encontrado para atualização", estadio.getId());
          }
      } catch (PersistenceException e) {
          LOGGER.error("Erro de persistência: não foi possível atualizar estádio", e);
          throw e;
      }
}


    @Override
    public Optional<Estadio> findById(Integer id) {
        try {
            Estadio estadio = em.find(Estadio.class, id);
            if (estadio != null) {
                return Optional.of(estadio);
            } else {
                LOGGER.warn("Estádio com ID {} não encontrado", id);
            }
        } catch (Exception e) {
            LOGGER.error("Erro de persistência: não foi possível recuperar o estádio com ID {}", id, e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Estadio> findByName(String nome) {
        String jpql = "SELECT estadio FROM Estadio estadio WHERE estadio.nome = :nome_estadio";
        TypedQuery<Estadio> query = em.createQuery(jpql, Estadio.class);
        query.setParameter("nome_estadio", nome);

        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            LOGGER.warn("Erro ao buscar estádio pelo nome '{}'", nome, e);
        }

        return Optional.empty();
    }

    @Override
    public List<Estadio> findAll() {
        String jpql = "SELECT estadio FROM Estadio estadio";
        TypedQuery<Estadio> query = em.createQuery(jpql, Estadio.class);

        try {
            return query.getResultList();
        } catch (PersistenceException e) {
            LOGGER.error("Erro de persistência: não foi possível recuperar todos os estádios", e);
        }

        return Collections.emptyList();
    }

}
