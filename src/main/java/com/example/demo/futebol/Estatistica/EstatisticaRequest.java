package com.example.demo.futebol.Estatistica;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.example.demo.futebol.Jogador.Jogador;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;

public class EstatisticaRequest {
    
    @NotNull( message = "id de Estatistica está invalido.")
    @JsonProperty("id")
    private Integer id;
    
    @JsonProperty("quantidadeJogosJogados")
    private Integer quantidadeJogosJogados; 
   
    @JsonProperty("quantidadeGolsMarcados")
    private Integer quantidadeGolsMarcados; 
   
    @JsonProperty("quantidadeAssistenciasGols")
    private Integer quantidadeAssistenciasGols; 
     
    public Estatistica transform(Jogador jogador){
        Estatistica objEstatistica = new Estatistica(this.id, this.quantidadeJogosJogados, this.quantidadeGolsMarcados,
         this.quantidadeAssistenciasGols, jogador);
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
        return "EstatisticaDTO [                                                                                                                  id=" + id + ",   quantidadeJogosJogados=" + quantidadeJogosJogados + ",   quantidadeGolsMarcados=" + quantidadeGolsMarcados + ",   quantidadeAssistenciasGols=" + quantidadeAssistenciasGols + "]";
    }
                                             
    @Override
    public int hashCode(){ 
        return Objects.hash(id, quantidadeJogosJogados, quantidadeGolsMarcados, quantidadeAssistenciasGols); 
    }

    @Override
    public boolean equals (Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EstatisticaRequest other = (EstatisticaRequest) obj;

        return Objects.equals(this.id, other.id) && Objects.equals(this.quantidadeJogosJogados, other.quantidadeJogosJogados) &&  
                Objects.equals(this.quantidadeGolsMarcados, other.quantidadeGolsMarcados) && 
                Objects.equals(this.quantidadeAssistenciasGols, other.quantidadeAssistenciasGols);     
    }

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
