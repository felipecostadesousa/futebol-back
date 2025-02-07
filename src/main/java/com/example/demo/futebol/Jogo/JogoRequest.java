package com.example.demo.futebol.Jogo;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.example.demo.futebol.Arbitro.Arbitro;
import com.example.demo.futebol.Competicao.Competicao;
import com.example.demo.futebol.Estadio.Estadio;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;

public class JogoRequest {

        
    @NotNull(message = "id de Jogo está invalido.")
    @JsonProperty("id")
    private Integer id;
    
    @JsonProperty("dataJogo")
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataJogo;
    
    @JsonProperty("golsTimeCasa")
    private Integer golsTimeCasa; 
   
    @JsonProperty("golsTimeVisitante")
    private Integer golsTimeVisitante; 

    public Jogo transform(Estadio estadio, Arbitro arbitro, Competicao competicao){
        Jogo objJogo = new Jogo(  this.id,  this.dataJogo,  this.golsTimeCasa,  this.golsTimeVisitante, estadio, arbitro, competicao);
        return objJogo;
    }
     
    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
        return this.id;
    }
           
    public void setDataJogo( LocalDate dataJogo ) {
        this.dataJogo = dataJogo ;
    }
    public LocalDate getDataJogo() {
        return this.dataJogo;
    }
           
    public void setGolsTimeCasa( Integer golsTimeCasa ) {
        this.golsTimeCasa = golsTimeCasa ;
    }
    public Integer getGolsTimeCasa() {
        return this.golsTimeCasa;
    }
           
    public void setGolsTimeVisitante( Integer golsTimeVisitante ) {
        this.golsTimeVisitante = golsTimeVisitante ;
    }
    public Integer getGolsTimeVisitante() {
        return this.golsTimeVisitante;
    }
     
    @Override
    public String toString() {
        return "JogoDTO [id=" + id + ",   dataJogo=" + dataJogo + ",   golsTimeCasa=" + golsTimeCasa + ",   golsTimeVisitante=" + golsTimeVisitante + "]";
    }
                                     
    @Override
    public int hashCode(){ return Objects.hash(id, dataJogo, golsTimeCasa, golsTimeVisitante); }

    @Override
    public boolean equals (Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JogoRequest other = (JogoRequest) obj;

        return Objects.equals(this.id, other.id) && Objects.equals(this.dataJogo, other.dataJogo) && 
                Objects.equals(this.golsTimeCasa, other.golsTimeCasa) && Objects.equals(this.golsTimeVisitante, other.golsTimeVisitante);     
    }

    public Set< String >isValidOnStage (Class<?> stage) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();


        Set< ConstraintViolation< JogoRequest >>violacoes = validator.validate(this, stage);

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
