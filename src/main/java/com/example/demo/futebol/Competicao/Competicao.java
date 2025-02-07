package com.example.demo.futebol.Competicao;

import jakarta.persistence.*;

@Entity
@Table(name="competicao", schema = "public")
public class Competicao {

    @Id
    @Column (name="id", nullable = false)
    private Integer id;

    @Column(name="nome", nullable = false)
    private String nome;

    @Column(name="ano", nullable = false)
    private Integer ano;

    @Column(name="quantidade_times", nullable = false)
    private String confederacao;

    public Competicao() {
    }

    public Competicao(Integer id, String nome, Integer ano, String confederacao) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.confederacao = confederacao;
    }

    public Competicao(String nome, Integer ano, String confederacao) {
        this.nome = nome;
        this.ano = ano;
        this.confederacao = confederacao;
    }

    public void transform(CompeticaoRequest request) {
       Competicao competicao = new Competicao(request.getNome(), request.getAno(), request.getConfederacao());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getConfederacao() {
        return confederacao;
    }

    public void setConfederacao(String confederacao) {
        this.confederacao = confederacao;
    }

   

}
