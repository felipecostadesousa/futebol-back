package com.example.demo.futebol.Jogador;

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

public class JogadorRequest {

    @NotNull(message = "id de Jogador está invalido.")
    @JsonProperty("id")
    private Integer id;
       
    @JsonProperty("posicao")
    private String posicao; 

    @JsonProperty("nome")
    private String nome; 

    @JsonProperty("apelido")
    private String apelido; 

    @JsonProperty("nacionalidade")
    private String nacionalidade; 

    @JsonProperty("imagem")
    private String imagem; 
    
    @JsonProperty("altura")
    private Double altura; 
    
    @JsonProperty("peDominante")
    private String peDominante; 
    
    @JsonProperty("valorMercado")
    private Double valorMercado; 
   
    @JsonProperty("cidadeNascimento")
    private String cidadeNascimento; 
   
    @JsonProperty("numeroCamisa")
    private Integer numeroCamisa; 
   
    @JsonProperty("agente")
    private String agente; 
    
    @JsonProperty("patrocinador")
    private String patrocinador; 
   
    @JsonProperty("redesSociais")
    private String redesSociais; 
   
    @JsonProperty("contratoInicio")
    @JsonFormat(pattern="dd/MM/yyyy")
    private String contratoInicio;

    @JsonProperty("dataNascimento")
    @JsonFormat(pattern="dd/MM/yyyy")
    private String dataNascimento;
    
    @JsonProperty("contratoFim")
    @JsonFormat(pattern="dd/MM/yyyy")
    private String contratoFim;

    @JsonProperty("idTime")
    private Integer idTime;


    public Jogador transform(Time time){
        Jogador jogador = new Jogador(this.id, this.nome, this.apelido, this.dataNascimento, this.nacionalidade, this.imagem, this.posicao,  this.altura,  this.peDominante,  this.valorMercado, this.cidadeNascimento,  this.numeroCamisa,  this.agente,  this.patrocinador,  this.redesSociais, this.contratoInicio,  this.contratoFim, time);
        return jogador;
    }
     
    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
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

    public void setContratoInicio( String contratoInicio ) {
        this.contratoInicio = contratoInicio ;
    }
    public String getContratoInicio() {
        return this.contratoInicio;
    }
           
    public void setContratoFim( String contratoFim ) {
        this.contratoFim = contratoFim ;
    }
    public String getContratoFim() {
        return this.contratoFim;
    }
    
    public Integer getIdTime() {
      return idTime;
    }

    public void setIdTime(Integer idTime) {
      this.idTime = idTime;
    }
    
    @Override
    public String toString() {
        return "JogadorDTO [id=" + id + ",   posicao=" + posicao + ",   altura=" + altura + ",   peDominante=" + peDominante + ",   valorMercado=" + valorMercado + ",   cidadeNascimento=" + cidadeNascimento + ",   numeroCamisa=" + numeroCamisa + ",   agente=" + agente + ",   patrocinador=" + patrocinador + ",   redesSociais=" + redesSociais + ",   contratoInicio=" + contratoInicio + ",   contratoFim=" + contratoFim + "]";
    }
                                                                                                                     
    @Override
    public int hashCode(){ return Objects.hash(id, posicao, altura, peDominante, valorMercado, cidadeNascimento, numeroCamisa, agente, patrocinador, redesSociais, contratoInicio, contratoFim); }

    @Override
    public boolean equals (Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JogadorRequest other = (JogadorRequest) obj;

        return Objects.equals(this.id, other.id) && Objects.equals(this.posicao, other.posicao) &&
                Objects.equals(this.altura, other.altura) && Objects.equals(this.peDominante, other.peDominante) && 
                Objects.equals(this.valorMercado, other.valorMercado) && 
                Objects.equals(this.cidadeNascimento, other.cidadeNascimento) && 
                Objects.equals(this.numeroCamisa, other.numeroCamisa) && 
                Objects.equals(this.agente, other.agente) && Objects.equals(this.patrocinador, other.patrocinador) &&
                Objects.equals(this.redesSociais, other.redesSociais) && 
                Objects.equals(this.contratoInicio, other.contratoInicio) && 
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
