package com.example.demo.futebol.Estadio;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.example.demo.futebol.Localizacao.Localizacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;

public class EstadioRequest {

    @NotNull(message = "id de Estadio está invalido.")
    @JsonProperty("id")
    private Integer id;
    
    @JsonProperty("dataFundacao")
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataFundacao;
    
    @JsonProperty("nome")
    private String nome; 
   
    @JsonProperty("capacidadePessoas")
    private Integer capacidadePessoas; 
             
    public Estadio transform(Localizacao localizacao){
        Estadio objEstadio = new Estadio(this.dataFundacao,  this.nome,  this.capacidadePessoas, localizacao);
        return objEstadio;
    }
     
    public void setId( int id ) {
        this.id = id ;
    }
    public int getId() {
        return this.id;
    }

           
    public void setDataFundacao( LocalDate dataFundacao ) {
        this.dataFundacao = dataFundacao ;
    }
    public LocalDate getDataFundacao() {
        return this.dataFundacao;
    }

           
    public void setNome( String nome ) {
        this.nome = nome ;
    }
    public String getNome() {
        return this.nome;
    }

           
    public void setCapacidadePessoas( Integer capacidadePessoas ) {
        this.capacidadePessoas = capacidadePessoas ;
    }
    public Integer getCapacidadePessoas() {
        return this.capacidadePessoas;
    }

        
    @Override
    public String toString() {
        return "EstadioDTO [                                                                                                                  id=" + id + ",   dataFundacao=" + dataFundacao + ",   nome=" + nome + ",   capacidadePessoas=" + capacidadePessoas + "]";
      }

                                             
    @Override
    public int hashCode(){ return Objects.hash(id, dataFundacao, nome, capacidadePessoas); }

    @Override
    public boolean equals (Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EstadioRequest other = (EstadioRequest) obj;

        return Objects.equals(this.id, other.id) && Objects.equals(this.dataFundacao, other.dataFundacao) && 
                 Objects.equals(this.nome, other.nome) && Objects.equals(this.capacidadePessoas, other.capacidadePessoas);    
        }

    public Set< String >isValidOnStage (Class<?> stage) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();


        Set< ConstraintViolation< EstadioRequest >>violacoes = validator.validate(this, stage);

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
