package com.example.demo.futebol.JogadorTitulo.rest;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
      
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.demo.JogadorTitulo;

                                 
import org.demo.Titulo;


public class JogadorTituloRequest {

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "id de JogadorTitulo está invalido.")
    @JsonProperty("id")
    private int id;

    public JogadorTitulo transform(Titulo titulo){
        JogadorTitulo objJogadorTitulo = new JogadorTitulo(  this.id,     titulo);
        return objJogadorTitulo;
    }
     
    public void setId( int id ) {
        this.id = id ;
    }
    public int getId() {
        return this.id;
    }

          
    @Override
    public String toString() {
        return "JogadorTituloDTO [ id=" + id + "]";
      }

    @Override
    public int hashCode(){ return Objects.hash(id); }

    @Override
    public boolean equals (Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JogadorTituloRequest other = (JogadorTituloRequest) obj;

        return Objects.equals(this.id, other.id);
    }

    public Set< String >isValidOnStage (Class<?> stage) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();


        Set< ConstraintViolation< JogadorTituloRequest >>violacoes = validator.validate(this, stage);

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