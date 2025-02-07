package com.example.demo.futebol.Titulo;

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

public class TituloRequest {

    @NotNull( message = "id de Titulo está invalido.")
    @JsonProperty("id")
    private Integer id;
    
    @JsonProperty("nome")
    private String nome; 
  
    public Titulo transform(String nome){
        Titulo titulo = new Titulo(nome);
        return titulo;
    }

    public void setId(Integer id) {
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

      
    @Override
    public String toString() {
        return "TituloDTO [id=" + id + ",   nome=" + nome + "]";
      }

                      
    @Override
    public int hashCode(){ return Objects.hash(id, nome); }

    @Override
    public boolean equals (Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TituloRequest other = (TituloRequest) obj;

        return Objects.equals(this.id, other.id) && Objects.equals(this.nome, other.nome);    
    }

    public Set< String >isValidOnStage (Class<?> stage) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();


        Set< ConstraintViolation< TituloRequest >>violacoes = validator.validate(this, stage);

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
