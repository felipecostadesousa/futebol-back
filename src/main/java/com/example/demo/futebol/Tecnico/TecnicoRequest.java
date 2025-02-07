package com.example.demo.futebol.Tecnico;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.example.demo.futebol.Time.Time;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;

public class TecnicoRequest {
     
    
    @NotNull(message = "id de Tecnico está invalido.")
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("apelido")
    private String apelido;

    @JsonProperty("nacionalidade")
    private String nacionalidade;

    @JsonProperty("imagem")
    private String imagem;

    @JsonProperty("dataNascimento")
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataNascimento;
   
    @JsonProperty("contratoInicio")
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate contratoInicio;
    
    @JsonProperty("contratoFim")
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate contratoFim;

    @JsonProperty("cidadeNascimento")
    private String cidadeNascimento; 
     
    public Tecnico transform(Time time){
        Tecnico objTecnico = new Tecnico(this.id, this.nome, this.apelido, this.dataNascimento, this.nacionalidade, this.imagem,  this.contratoInicio,  this.contratoFim,  this.cidadeNascimento,    time);
        return objTecnico;
    }

    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
        return this.id;
    }
           
    public void setContratoInicio( LocalDate contratoInicio ) {
        this.contratoInicio = contratoInicio ;
    }
    public LocalDate getContratoInicio() {
        return this.contratoInicio;
    }
           
    public void setContratoFim( LocalDate contratoFim ) {
        this.contratoFim = contratoFim ;
    }
    public LocalDate getContratoFim() {
        return this.contratoFim;
    }

    public void setCidadeNascimento( String cidadeNascimento ) {
        this.cidadeNascimento = cidadeNascimento ;
    }
    public String getCidadeNascimento() {
        return this.cidadeNascimento;
    }

    @Override
    public String toString() {
        return "TecnicoDTO [id=" + id + ",   contratoInicio=" + contratoInicio + ",   contratoFim=" + contratoFim + ",   cidadeNascimento=" + cidadeNascimento + "]";
    }
                                             
    @Override
    public int hashCode(){ return Objects.hash(id, contratoInicio, contratoFim, cidadeNascimento); }

    @Override
    public boolean equals (Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TecnicoRequest other = (TecnicoRequest) obj;

        return Objects.equals(this.id, other.id) && Objects.equals(this.contratoInicio, other.contratoInicio) &&          
                Objects.equals(this.contratoFim, other.contratoFim) && Objects.equals(this.cidadeNascimento, other.cidadeNascimento);     
    }

    public Set< String >isValidOnStage (Class<?> stage) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set< ConstraintViolation< TecnicoRequest >>violacoes = validator.validate(this, stage);

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
