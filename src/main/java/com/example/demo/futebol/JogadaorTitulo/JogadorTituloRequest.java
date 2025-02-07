package com.example.demo.futebol.JogadaorTitulo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.example.demo.futebol.Jogador.Jogador;
import com.example.demo.futebol.Titulo.Titulo;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;

public class JogadorTituloRequest {

    @NotNull(message = "id de JogadorTitulo está invalido.")
    @JsonProperty("id")
    private Integer id;   
                 
    public JogadorTitulo transform(Jogador jogador, Titulo titulo){
        JogadorTitulo objJogadorTitulo = new JogadorTitulo(this.id, jogador, titulo);
        return objJogadorTitulo;
    }

    public JogadorTitulo transform(Titulo titulo){
        JogadorTitulo objJogadorTitulo = new JogadorTitulo(titulo);
        return objJogadorTitulo;
    }
     
    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
        return this.id;
    }
          
    @Override
    public String toString() {
        return "JogadorTituloDTO [id=" + id + "]";
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
