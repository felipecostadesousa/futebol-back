package com.example.demo.futebol.Entidades.Pessoa;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@Service
public class PessoaService {

    private PessoaDao pessoaDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(PessoaService.class.getName());

    @Transactional
    public void save(Pessoa pessoa) {
        try{
            LOGGER.info("Service: salvando pessoa");
            pessoaDao.save(pessoa);
            LOGGER.info("Service: pessoa salva com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível salvar pessoa");
        }
    }

    @Transactional
    public void delete(Pessoa pessoa) {
        try{
            LOGGER.info("Service: deletando pessoa");
            pessoaDao.delete(pessoa);
            LOGGER.info("Service: pessoa deletada com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível deletar pessoa");
        }
    }

    @Transactional
    public void update(Pessoa pessoa) {
        try{
            LOGGER.info("Service: atualizando pessoa");
            pessoaDao.update(pessoa);
            LOGGER.info("Service: pessoa atualizada com sucesso");
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível atualizar pessoa");
        }
    }

    @Transactional
    public Optional<Pessoa> findById(Integer id) {
        try {
            LOGGER.info("Service: buscando pessoa com id {}", id);
            Optional<Pessoa> pessoa = pessoaDao.findById(id);

            if(pessoa.isPresent()){
                LOGGER.info("Service: pessoa encontrada com sucesso");
                return pessoa;
            }else{
                LOGGER.warn("Service: não foi possível encontrar pessoa com id {}", id);
            }
        } catch (PersistenceException e) {
            LOGGER.error("Service: não foi possível recuperar pessoa");
        }

        return Optional.empty();
    }

    @Transactional
    public List<Pessoa> findAll() {
        try{
            LOGGER.info("Service: buscando lista de pessoas");
            List<Pessoa> pessoas = pessoaDao.findAll();
            LOGGER.info("Service: lista de pessoas recuperada com sucesso", pessoas.size());
            return pessoas;
        }catch(PersistenceException e){
            LOGGER.error("Service: não foi possível recuperar lista de pessoas", e.getMessage());
        }

        return Collections.emptyList();
    }


}
