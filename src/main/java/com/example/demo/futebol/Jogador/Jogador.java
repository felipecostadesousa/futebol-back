package com.example.demo.futebol.Jogador;

import java.util.*;

import com.example.demo.futebol.Pessoa.Pessoa;
import com.example.demo.futebol.Time.Time;

import jakarta.persistence.*;

@Entity
@Table(name="jogador", schema="public" )
public class Jogador extends Pessoa{

    @Id
    @Column(name = "id", nullable = false)
    private Integer idJogador;

    @Column(name = "posicao", nullable = false)
    private String posicao;
    
    @Column(name = "altura", nullable = false)
    private double altura;

    @Column(name = "pe_dominante", nullable = false)
    private String peDominante;

    @Column(name = "valor_mercado", nullable = false)
    private double valorMercado;

    @Column(name = "cidade_nascimento", nullable = false)
    private String cidadeNascimento;

    @Column(name = "numero_camisa", nullable = false)
    private Integer numeroCamisa;

    @Column(name = "agente", nullable = false)
    private String agente;

    @Column(name = "patrocinador", nullable = false)
    private String patrocinador;

    @Column(name = "rede_social", nullable = false)
    private String redeSocial;

    @Column(name = "data_inicio_contrato", nullable = false)
    private Date dataInicioContrato;

    @Column(name = "data_fim_contrato", nullable = false)
    private Date dataFimContrato;

    @OneToOne
    @JoinColumn(name = "fk_time", referencedColumnName="id_time")
    private Time time;

    public Jogador(){
    }

    public Jogador(Integer id, String nome, String apelido, Date dataNascimento, String nacionalidade, String imagem, String posicao, double altura, String peDominante, double valorMercado, String cidadeNascimento, Integer numeroCamisa, String agente, String patrocinador, String redeSocial, Date dataInicioContrato, Date dataFimContrato, Time time) {
        super();
        this.idJogador = id;
        this.posicao = posicao;
        this.altura = altura;
        this.peDominante = peDominante;
        this.valorMercado = valorMercado;
        this.cidadeNascimento = cidadeNascimento;
        this.numeroCamisa = numeroCamisa;
        this.agente = agente;
        this.patrocinador = patrocinador;
        this.redeSocial = redeSocial;
        this.dataInicioContrato = dataInicioContrato;
        this.dataFimContrato = dataFimContrato;
        this.time = time;
    }

    public Jogador(String nome, String apelido, Date dataNascimento, String nacionalidade, String imagem, String posicao, double altura, String peDominante, double valorMercado, String cidadeNascimento, Integer numeroCamisa, String agente, String patrocinador, String redeSocial, Date dataInicioContrato, Date dataFimContrato, Time time) {
        super();
        this.posicao = posicao;
        this.altura = altura;
        this.peDominante = peDominante;
        this.valorMercado = valorMercado;
        this.cidadeNascimento = cidadeNascimento;
        this.numeroCamisa = numeroCamisa;
        this.agente = agente;
        this.patrocinador = patrocinador;
        this.redeSocial = redeSocial;
        this.dataInicioContrato = dataInicioContrato;
        this.dataFimContrato = dataFimContrato;
        this.time = time;
    }

    public Integer getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Integer idJogador) {
        this.idJogador = idJogador;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getPeDominante() {
        return peDominante;
    }

    public void setPeDominante(String peDominante) {
        this.peDominante = peDominante;
    }

    public double getValorMercado() {
        return valorMercado;
    }

    public void setValorMercado(double valorMercado) {
        this.valorMercado = valorMercado;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public Integer getNumeroCamisa() {
        return numeroCamisa;
    }

    public void setNumeroCamisa(Integer numeroCamisa) {
        this.numeroCamisa = numeroCamisa;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public String getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }

    public String getRedeSocial() {
        return redeSocial;
    }

    public void setRedeSocial(String redeSocial) {
        this.redeSocial = redeSocial;
    }

    public Date getDataInicioContrato() {
        return dataInicioContrato;
    }

    public void setDataInicioContrato(Date dataInicioContrato) {
        this.dataInicioContrato = dataInicioContrato;
    }

    public Date getDataFimContrato() {
        return dataFimContrato;
    }

    public void setDataFimContrato(Date dataFimContrato) {
        this.dataFimContrato = dataFimContrato;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}
