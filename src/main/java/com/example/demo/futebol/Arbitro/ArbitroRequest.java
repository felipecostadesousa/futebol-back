package com.example.demo.futebol.Arbitro;

import java.time.LocalDate;
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

public class ArbitroRequest {

    @NotNull(message = "id de Arbitro está invalido.")
    @JsonProperty("id")
    private Integer id;
    
    @JsonProperty("contratoInicio")
    private LocalDate contratoInicio; 

    public Arbitro transform( LocalDate contratoInicio, String nome, String apelido, LocalDate dataNascimento, String nacionalidade, String imagem  ){
        Arbitro arbitro = new Arbitro(contratoInicio, nome, apelido, dataNascimento, nacionalidade, imagem);
        return arbitro;
    }

     
    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
        return this.id;
    }
           
    public void setContratoInicio( Integer contratoInicio ) {
        this.contratoInicio = contratoInicio ;
    }
    public Integer getContratoInicio() {
        return this.contratoInicio;
    }

      
    @Override
    public String toString() {
        return "ArbitroDTO [id=" + id + ",   contratoInicio=" + contratoInicio + "]";
      }

                      
    @Override
    public int hashCode(){ return Objects.hash(id, contratoInicio); }

    @Override
    public boolean equals (Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ArbitroRequest other = (ArbitroRequest) obj;

        return Objects.equals(this.id, other.id) && Objects.equals(this.contratoInicio, other.contratoInicio);     
    }

    public Set< String >isValidOnStage (Class<?> stage) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();


        Set< ConstraintViolation< ArbitroRequest >>violacoes = validator.validate(this, stage);

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
