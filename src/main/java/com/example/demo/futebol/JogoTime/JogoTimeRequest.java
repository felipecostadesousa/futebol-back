package com.example.demo.futebol.JogoTime;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.example.demo.futebol.Jogo.Jogo;
import com.example.demo.futebol.Time.Time;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;

public class JogoTimeRequest {

    @NotNull(message = "id de JogoTime está invalido.")
    @JsonProperty("id")
    private Integer id;

    public JogoTime transform(Time time, Jogo jogo){
        JogoTime jogoTime = new JogoTime(this.id, time, jogo);
        return jogoTime;
    }

    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "JogoTimeDTO [id=" + id + "]";
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
        JogoTimeRequest other = (JogoTimeRequest) obj;

        return Objects.equals(this.id, other.id);               
    }

    public Set< String >isValidOnStage (Class<?> stage) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        Set< ConstraintViolation< JogoTimeRequest >>violacoes = validator.validate(this, stage);

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
