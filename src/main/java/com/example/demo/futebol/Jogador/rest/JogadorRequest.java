
package com.example.demo.futebol.Jogador.rest;

import java.time.LocalDate;
import javax.persistence.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


import com.fasterxml.jackson.annotation.JsonFormat;

   
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.demo.Jogador;

                         
import org.demo.Time;


public class JogadorRequest {

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "id de Jogador está invalido.")
    @JsonProperty("id")
    private int id;

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "posicao de Jogador está invalido.")
    @JsonProperty("posicao")
    private String posicao;

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "altura de Jogador está invalido.")
    @JsonProperty("altura")
    private Double altura;

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "peDominante de Jogador está invalido.")
    @JsonProperty("peDominante")
    private String peDominante;

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "valorMercado de Jogador está invalido.")
    @JsonProperty("valorMercado")
    private Double valorMercado;

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "cidadeNascimento de Jogador está invalido.")
    @JsonProperty("cidadeNascimento")
    private String cidadeNascimento;

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "numeroCamisa de Jogador está invalido.")
    @JsonProperty("numeroCamisa")
    private Integer numeroCamisa;

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "agente de Jogador está invalido.")
    @JsonProperty("agente")
    private String agente;

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "patrocinador de Jogador está invalido.")
    @JsonProperty("patrocinador")
    private String patrocinador;

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "redesSociais de Jogador está invalido.")
    @JsonProperty("redesSociais")
    private String redesSociais;

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "contratoInicio de Jogador está invalido.")
    @JsonProperty("contratoInicio")
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate contratoInicio;

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "contratoFim de Jogador está invalido.")
    @JsonProperty("contratoFim")
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate contratoFim;
             
    public Jogador transform(Time time){
        Jogador objJogador = new Jogador(  this.id,  this.posicao,  this.altura,  this.peDominante,  this.valorMercado,
                this.cidadeNascimento,  this.numeroCamisa,  this.agente,  this.patrocinador,  this.redesSociais,
                this.contratoInicio,  this.contratoFim, time);
        return objJogador;
    }

    public void setId( int id ) {
        this.id = id ;
    }
    public int getId() {
        return this.id;
    }

           
    public void setPosicao( String posicao ) {
        this.posicao = posicao ;
    }
    public String getPosicao() {
        return this.posicao;
    }

           
    public void setAltura( Double altura ) {
        this.altura = altura ;
    }
    public Double getAltura() {
        return this.altura;
    }

           
    public void setPeDominante( String peDominante ) {
        this.peDominante = peDominante ;
    }
    public String getPeDominante() {
        return this.peDominante;
    }

           
    public void setValorMercado( Double valorMercado ) {
        this.valorMercado = valorMercado ;
    }
    public Double getValorMercado() {
        return this.valorMercado;
    }

           
    public void setCidadeNascimento( String cidadeNascimento ) {
        this.cidadeNascimento = cidadeNascimento ;
    }
    public String getCidadeNascimento() {
        return this.cidadeNascimento;
    }

           
    public void setNumeroCamisa( Integer numeroCamisa ) {
        this.numeroCamisa = numeroCamisa ;
    }
    public Integer getNumeroCamisa() {
        return this.numeroCamisa;
    }

           
    public void setAgente( String agente ) {
        this.agente = agente ;
    }
    public String getAgente() {
        return this.agente;
    }

           
    public void setPatrocinador( String patrocinador ) {
        this.patrocinador = patrocinador ;
    }
    public String getPatrocinador() {
        return this.patrocinador;
    }

           
    public void setRedesSociais( String redesSociais ) {
        this.redesSociais = redesSociais ;
    }
    public String getRedesSociais() {
        return this.redesSociais;
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

        
    @Override
    public String toString() {
        return "JogadorDTO [ id=" + id + ",   posicao=" + posicao + ",   altura=" + altura + ",   peDominante=" +
                peDominante + ",   valorMercado=" + valorMercado + ",   cidadeNascimento=" + cidadeNascimento + ",   numeroCamisa=" +
                numeroCamisa + ",   agente=" + agente + ",   patrocinador=" + patrocinador + ",   redesSociais=" + redesSociais + ",   contratoInicio=" +
                contratoInicio + ",   contratoFim=" + contratoFim + "]";
    }

                                                                                                                     
    @Override
    public int hashCode(){ return Objects.hash(id, posicao, altura, peDominante, valorMercado, cidadeNascimento,
            numeroCamisa, agente, patrocinador, redesSociais, contratoInicio, contratoFim); }

    @Override
    public boolean equals (Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JogadorRequest other = (JogadorRequest) obj;

        return Objects.equals(this.id, other.id) && Objects.equals(this.posicao, other.posicao) && Objects.equals(this.altura, other.altura) &&
                Objects.equals(this.peDominante, other.peDominante) && Objects.equals(this.valorMercado, other.valorMercado) &&
                Objects.equals(this.cidadeNascimento, other.cidadeNascimento) && Objects.equals(this.numeroCamisa, other.numeroCamisa) &&
                Objects.equals(this.agente, other.agente) && Objects.equals(this.patrocinador, other.patrocinador) &&
                Objects.equals(this.redesSociais, other.redesSociais) && Objects.equals(this.contratoInicio, other.contratoInicio) &&
                Objects.equals(this.contratoFim, other.contratoFim);
    }

    public Set< String >isValidOnStage (Class<?> stage) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();


        Set< ConstraintViolation< JogadorRequest >>violacoes = validator.validate(this, stage);

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