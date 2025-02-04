
package com.example.demo.futebol.Arbitro.rest;

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
import org.demo.Arbitro;


public class ArbitroRequest {

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "id de Arbitro está invalido.")
    //@NotBlank(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "id de Arbitro está em branco")
    @JsonProperty("id")
    private int id;

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "contratoInicio de Arbitro está invalido.")
    @NotBlank(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "contratoInicio de Arbitro está em branco")
    @JsonProperty("contratoInicio")
    private Date contratoInicio;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("apelido")
    private String apelido;

    @JsonProperty("dataNascimento")
    private Date dataNascimento;

    @JsonProperty("imagem")
    private String imagem;

    @JsonProperty("nacionalidade")
    private String nacionalidade;

    public Arbitro transform( Date contratoInicio, String nome, String apelido, Date dataNascimento, String nacionalidade, String imagem ){
        Arbitro arbitro = new Arbitro(  contratoInicio, nome, apelido, dateNascimento, imagem);
        return arbitro;
    }

    public void setId( int id ) {
        this.id = id ;
    }

    public int getId() {
        return this.id;
    }

    public Date getContratoInicio() {
        return contratoInicio;
    }

    public void setContratoInicio(Date contratoInicio) {
        this.contratoInicio = contratoInicio;
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    @Override
    public String toString() {
        return "ArbitroDTO [ id=" + id + ",   contratoInicio=" + contratoInicio + "]";
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