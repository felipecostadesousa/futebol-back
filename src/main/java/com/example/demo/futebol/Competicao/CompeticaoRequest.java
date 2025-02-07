package com.example.demo.futebol.Competicao;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;

public class CompeticaoRequest {

     
    @NotNull(message = "id de Competicao está invalido.")
    @JsonProperty("id")
    private Integer id;
    
    @NotNull(message = "nome de Competicao está invalido.")
    @JsonProperty("nome")
    private String nome; 
    
    @JsonProperty("ano")
    private Integer ano; 
    
    @JsonProperty("confederacao")
    private String confederacao; 
  
    public Competicao transform( String nome, Integer ano, String confederacao  ){
        Competicao object = new Competicao( nome, ano, confederacao);
        return object;
    }

     
    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
        return this.id;
    }
           
    public void setNome( String nome ) {
        this.nome = nome ;
    }
    public String getNome() {
        return this.nome;
    }
  
    public void setAno( Integer ano ) {
        this.ano = ano ;
    }
    public Integer getAno() {
        return this.ano;
    }
           
    public void setConfederacao( String confederacao ) {
        this.confederacao = confederacao ;
    }
    public String getConfederacao() {
        return this.confederacao;
    }

    @Override
    public String toString() {
        return "CompeticaoDTO [id=" + id + ",   nome=" + nome + ",   ano=" + ano + ",   confederacao=" + confederacao + "]";
      }
                                        
    @Override
    public int hashCode(){ return Objects.hash(id, nome, ano, confederacao); }

    @Override
    public boolean equals (Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CompeticaoRequest other = (CompeticaoRequest) obj;

        return Objects.equals(this.id, other.id) && Objects.equals(this.nome, other.nome) && Objects.equals(this.ano, other.ano) 
                && Objects.equals(this.confederacao, other.confederacao);     
    }

    public Set< String >isValidOnStage (Class<?> stage) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();


        Set< ConstraintViolation< CompeticaoRequest >>violacoes = validator.validate(this, stage);

        if (violacoes.isEmpty()) {
            return Collections.emptySet();
        }

        Set< String > violations = new HashSet<>() ;
        violacoes.forEach((violacao)->{
            violations.add(violacao.getMessageTemplate());
        });

        return violations;
    }

    public String logString() {
        return ("Adicione uma implmenentação adequada.");
    }

}
