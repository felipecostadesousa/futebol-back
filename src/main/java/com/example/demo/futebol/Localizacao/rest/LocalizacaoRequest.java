package com.example.demo.futebol.Localizacao.rest;

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
import org.demo.Localizacao;


public class LocalizacaoRequest {

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "id de Localizacao está invalido.")
    @NotBlank(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "id de Localizacao está em branco")
    @JsonProperty("id")
    private int id;

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "pais de Localizacao está invalido.")
    @NotBlank(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "pais de Localizacao está em branco")
    @JsonProperty("pais")
    private String pais;

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "regiao de Localizacao está invalido.")
    @NotBlank(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "regiao de Localizacao está em branco")
    @JsonProperty("regiao")
    private String regiao;

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "estado de Localizacao está invalido.")
    @NotBlank(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "estado de Localizacao está em branco")
    @JsonProperty("estado")
    private String estado;

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "cidade de Localizacao está invalido.")
    @NotBlank(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "cidade de Localizacao está em branco")
    @JsonProperty("cidade")
    private String cidade;

    public Localizacao transform( String pais,   String regiao,   String estado,    String cidade  ){
        Localizacao object = new Localizacao(     pais,       regiao,       estado,    cidade  );
        return object;
    }

    public void setId( int id ) {
        this.id = id ;
    }
    public int getId() {
        return this.id;
    }
           
    public void setPais( String pais ) {
        this.pais = pais ;
    }
    public String getPais() {
        return this.pais;
    }
           
    public void setRegiao( String regiao ) {
        this.regiao = regiao ;
    }
    public String getRegiao() {
        return this.regiao;
    }

    public void setEstado( String estado ) {
        this.estado = estado ;
    }
    public String getEstado() {
        return this.estado;
    }

    public void setCidade( String cidade ) {
        this.cidade = cidade ;
    }
    public String getCidade() {
        return this.cidade;
    }

    @Override
    public String toString() {
        return "LocalizacaoDTO [id=" + id + ",   pais=" + pais + ",   regiao=" + regiao + ",   estado=" + estado + ",   cidade=" + cidade + "]";
    }

    @Override
    public int hashCode(){ return Objects.hash(id, pais, regiao, estado, cidade); }

    @Override
    public boolean equals (Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LocalizacaoRequest other = (LocalizacaoRequest) obj;

        return Objects.equals(this.id, other.id) && Objects.equals(this.pais, other.pais) && Objects.equals(this.regiao, other.regiao) &&
                Objects.equals(this.estado, other.estado) && Objects.equals(this.cidade, other.cidade);
    }

    public Set< String >isValidOnStage (Class<?> stage) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();


        Set< ConstraintViolation< LocalizacaoRequest >>violacoes = validator.validate(this, stage);

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