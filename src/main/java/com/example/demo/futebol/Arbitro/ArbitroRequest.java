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

    @JsonProperty("nome")
    private String nome; 

    @JsonProperty("apelido")
    private String apelido; 

    @JsonProperty("dataNascimento")
    private LocalDate dataNascimento; 

    @JsonProperty("nacionalidade")
    private String nacionalidade; 

    @JsonProperty("imagem")
    private String imagem; 

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

    public void setContratoInicio( LocalDate contratoInicio ) {
        this.contratoInicio = contratoInicio;
    }
    public LocalDate getContratoInicio() {
        return this.contratoInicio;
    }
      
    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getApelido() {
        return apelido;
    }


    public void setApelido(String apelido) {
        this.apelido = apelido;
    }


    public LocalDate getDataNascimento() {
        return dataNascimento;
    }


    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    public String getNacionalidade() {
        return nacionalidade;
    }


    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }


    public String getImagem() {
        return imagem;
    }


    public void setImagem(String imagem) {
        this.imagem = imagem;
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
