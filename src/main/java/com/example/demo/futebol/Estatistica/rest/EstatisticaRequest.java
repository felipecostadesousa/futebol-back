
package com.example.demo.futebol.Estatistica.rest;

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
import org.demo.Estatistica;

                         
import org.demo.Jogador;


public class EstatisticaRequest {

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "id de Estatistica está invalido.")
    @JsonProperty("id")
    private int id;

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "quantidadeJogosJogados de Estatistica está invalido.")
    @JsonProperty("quantidadeJogosJogados")
    private Integer quantidadeJogosJogados;

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "quantidadeGolsMarcados de Estatistica está invalido.")
    @JsonProperty("quantidadeGolsMarcados")
    private Integer quantidadeGolsMarcados;

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "quantidadeAssistenciasGols de Estatistica está invalido.")
    @JsonProperty("quantidadeAssistenciasGols")
    private Integer quantidadeAssistenciasGols;

    public Estatistica transform(Jogador jogador){
        Estatistica objEstatistica = new Estatistica(  this.id,  this.quantidadeJogosJogados,  this.quantidadeGolsMarcados,  this.quantidadeAssistenciasGols,    jogador);
        return objEstatistica;
    }

    public void setId( int id ) {
        this.id = id ;
    }

    public int getId() {
        return this.id;
    }

    public void setQuantidadeJogosJogados( Integer quantidadeJogosJogados ) {
        this.quantidadeJogosJogados = quantidadeJogosJogados ;
    }
    public Integer getQuantidadeJogosJogados() {
        return this.quantidadeJogosJogados;
    }
           
    public void setQuantidadeGolsMarcados( Integer quantidadeGolsMarcados ) {
        this.quantidadeGolsMarcados = quantidadeGolsMarcados ;
    }
    public Integer getQuantidadeGolsMarcados() {
        return this.quantidadeGolsMarcados;
    }

    public void setQuantidadeAssistenciasGols( Integer quantidadeAssistenciasGols ) {
        this.quantidadeAssistenciasGols = quantidadeAssistenciasGols ;
    }
    public Integer getQuantidadeAssistenciasGols() {
        return this.quantidadeAssistenciasGols;
    }

        
    @Override
    public String toString() {
        return "EstatisticaDTO [ id=" + id + ",   quantidadeJogosJogados=" + quantidadeJogosJogados + ",   quantidadeGolsMarcados=" + quantidadeGolsMarcados + ",   quantidadeAssistenciasGols=" + quantidadeAssistenciasGols + "]";
    }

    @Override
    public int hashCode(){ return Objects.hash(id, quantidadeJogosJogados, quantidadeGolsMarcados, quantidadeAssistenciasGols); }

    @Override
    public boolean equals (Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EstatisticaRequest other = (EstatisticaRequest) obj;

        return Objects.equals(this.id, other.id) && Objects.equals(this.quantidadeJogosJogados, other.quantidadeJogosJogados) && Objects.equals(this.quantidadeGolsMarcados, other.quantidadeGolsMarcados) && Objects.equals(this.quantidadeAssistenciasGols, other.quantidadeAssistenciasGols);     }

    public Set< String >isValidOnStage (Class<?> stage) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();


        Set< ConstraintViolation< EstatisticaRequest >>violacoes = validator.validate(this, stage);

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