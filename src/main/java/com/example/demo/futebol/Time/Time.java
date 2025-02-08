package com.example.demo.futebol.Time;

import java.time.LocalDate;

import com.example.demo.futebol.Estadio.Estadio;
import com.example.demo.futebol.Localizacao.Localizacao;

import jakarta.persistence.*;

@Entity
@Table(name="time", schema = "public")
public class Time {

    @Id
    @Column(name="id", nullable = false)
    private Integer id;

    @Column(name="imagem_escudo", nullable = false)
    private byte[] escudo;

    @Column(name="nome", nullable = false)
    private String nome;

    @Column(name="apelido", nullable = false)
    private String apelido;

    @Column(name="data_fundacao", nullable = false)
    private LocalDate dataFundacao;

    @Column(name="site_time_url", nullable = false)
    private String site;

    @Column(name="quantidade_socios", nullable = false)
    private Integer qtdSocios;

    @Column(name="quantidade_jogadores_selecao", nullable = false)
    private Integer qtdJogadoresSelecao;

    @Column(name="valor_mercado", nullable = false)
    private Double valorMercado;

    @OneToOne
    @JoinColumn(name = "id_estadio", referencedColumnName="id")
    private Estadio estadio;

    @OneToOne
    @JoinColumn(name = "id_localizacao", referencedColumnName="id")
    private Localizacao localizacao;

    public Time(){

    }

    public Time(Integer id, byte[] escudo, String nome, String apelido, LocalDate dataFundacao, String site, Integer qtdSocios, Integer qtdJogadoresSelecao, Double valorMercado, Estadio estadio, Localizacao localizacao){
        this.id = id;
        this.escudo = escudo;
        this.nome = nome;
        this.apelido = apelido;
        this.dataFundacao = dataFundacao;
        this.site = site;
        this.qtdSocios = qtdSocios;
        this.qtdJogadoresSelecao = qtdJogadoresSelecao;
        this.valorMercado = valorMercado;
        this.estadio = estadio;
        this.localizacao = localizacao;
    }

    public Time(byte[] escudo, String nome, String apelido, LocalDate dataFundacao, String site, Integer qtdSocios, Integer qtdJogadoresSelecao, Double valorMercado, Estadio estadio, Localizacao localizacao){
        this.escudo = escudo;
        this.nome = nome;
        this.apelido = apelido;
        this.dataFundacao = dataFundacao;
        this.site = site;
        this.qtdSocios = qtdSocios;
        this.qtdJogadoresSelecao = qtdJogadoresSelecao;
        this.valorMercado = valorMercado;
        this.estadio = estadio;
        this.localizacao = localizacao;
    }

    public Time(Integer id, byte[] escudo, String nome, String apelido, LocalDate dataFundacao, String site, Integer qtdSocios, Integer qtdJogadoresSelecao, Double valorMercado, Localizacao localizacao){
        this.id = id;
        this.escudo = escudo;
        this.nome = nome;
        this.apelido = apelido;
        this.dataFundacao = dataFundacao;
        this.site = site;
        this.qtdSocios = qtdSocios;
        this.qtdJogadoresSelecao = qtdJogadoresSelecao;
        this.valorMercado = valorMercado;
        this.localizacao = localizacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getEscudo() {
        return escudo;
    }

    public void setEscudo(byte[] escudo) {
        this.escudo = escudo;
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

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Integer getQtdSocios() {
        return qtdSocios;
    }

    public void setQtdSocios(Integer qtdSocios) {
        this.qtdSocios = qtdSocios;
    }

    public Integer getQtdJogadoresSelecao() {
        return qtdJogadoresSelecao;
    }

    public void setQtdJogadoresSelecao(Integer qtdJogadoresSelecao) {
        this.qtdJogadoresSelecao = qtdJogadoresSelecao;
    }

    public Double getValorMercado() {
        return valorMercado;
    }

    public void setValorMercado(Double valorMercado) {
        this.valorMercado = valorMercado;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    

}
