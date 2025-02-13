package com.example.demo.futebol.Time;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.example.demo.futebol.Estadio.Estadio;
import com.example.demo.futebol.Localizacao.Localizacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;

public class TimeRequest {

    @NotNull( message = "id de Time está invalido.")
    @JsonProperty("id")
    private Integer id;
    
    @JsonProperty("imagemEscudo")
    private byte[] imagemEscudo; 

    @NotNull(message = "nome de Time está invalido.")
    @JsonProperty("nome")
    private String nome; 
 
    @JsonProperty("apelido")
    private String apelido; 
   
    @JsonProperty("dataFundacao")
    @JsonFormat(pattern="dd/MM/yyyy")
    private String dataFundacao;

    @JsonProperty("siteTimeUrl")
    private String siteTimeUrl; 
   
    @JsonProperty("quantidadeSocios")
    private Integer quantidadeSocios; 
   
    @JsonProperty("quantidadeJogadoresSelecao")
    private Integer quantidadeJogadoresSelecao; 
    
    @JsonProperty("valorMercado")
    private Double valorMercado; 

    @JsonProperty("idEstadio")
    private Integer idEstadio;
        
    public Time transform(Estadio estadio, Localizacao localizacao){
        Time time = new Time(  this.id,  this.imagemEscudo,  this.nome,  this.apelido,  this.dataFundacao,  this.siteTimeUrl,  this.quantidadeSocios,  this.quantidadeJogadoresSelecao,  this.valorMercado, estadio, localizacao);
        return time;
    }
     
    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
        return this.id;
    }
           
    public void setImagemEscudo( byte[] imagemEscudo ) {
        this.imagemEscudo = imagemEscudo ;
    }
    public byte[] getImagemEscudo() {
        return this.imagemEscudo;
    }

    public void setNome( String nome ) {
        this.nome = nome ;
    }
    public String getNome() {
        return this.nome;
    }

    public void setApelido( String apelido ) {
        this.apelido = apelido ;
    }
    public String getApelido() {
        return this.apelido;
    }
           
    public void setDataFundacao( String dataFundacao ) {
        this.dataFundacao = dataFundacao ;
    }
    public String getDataFundacao() {
        return this.dataFundacao;
    }
    
    public void setSiteTimeUrl( String siteTimeUrl ) {
        this.siteTimeUrl = siteTimeUrl ;
    }
    public String getSiteTimeUrl() {
        return this.siteTimeUrl;
    }
           
    public void setQuantidadeSocios( Integer quantidadeSocios ) {
        this.quantidadeSocios = quantidadeSocios ;
    }
    public Integer getQuantidadeSocios() {
        return this.quantidadeSocios;
    }
  
    public void setQuantidadeJogadoresSelecao( Integer quantidadeJogadoresSelecao ) {
        this.quantidadeJogadoresSelecao = quantidadeJogadoresSelecao ;
    }
    public Integer getQuantidadeJogadoresSelecao() {
        return this.quantidadeJogadoresSelecao;
    }
    
    public void setValorMercado( Double valorMercado ) {
        this.valorMercado = valorMercado ;
    }
    public Double getValorMercado() {
        return this.valorMercado;
    }
          
    @Override
    public String toString() {
        return "TimeDTO [id=" + id + ",   imagemEscudo=" + imagemEscudo + ",   nome=" + nome + ",   apelido=" + apelido + ",   dataFundacao=" + dataFundacao + ",   siteTimeUrl=" + siteTimeUrl + ",   quantidadeSocios=" + quantidadeSocios + ",   quantidadeJogadoresSelecao=" + quantidadeJogadoresSelecao + ",   valorMercado=" + valorMercado + "]";
    }

                                                                                               
    @Override
    public int hashCode(){ return Objects.hash(id, imagemEscudo, nome, apelido, dataFundacao, siteTimeUrl, quantidadeSocios, quantidadeJogadoresSelecao, valorMercado); }

    @Override
    public boolean equals (Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TimeRequest other = (TimeRequest) obj;

        return Objects.equals(this.id, other.id) && Objects.equals(this.imagemEscudo, other.imagemEscudo) &&          
                Objects.equals(this.nome, other.nome) && Objects.equals(this.apelido, other.apelido) && 
                Objects.equals(this.dataFundacao, other.dataFundacao) && Objects.equals(this.siteTimeUrl, other.siteTimeUrl) && 
                Objects.equals(this.quantidadeSocios, other.quantidadeSocios) && Objects.equals(this.quantidadeJogadoresSelecao, other.quantidadeJogadoresSelecao) &&          
                Objects.equals(this.valorMercado, other.valorMercado);     
    }

    public Set< String >isValidOnStage (Class<?> stage) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();


        Set< ConstraintViolation< TimeRequest >>violacoes = validator.validate(this, stage);

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

    public Integer getIdEstadio() {
        return idEstadio;
    }

    public void setIdEstadio(Integer idEstadio) {
        this.idEstadio = idEstadio;
    }

}
