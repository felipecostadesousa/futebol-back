package com.example.demo.futebol.Pessoa;

import java.util.*;

import jakarta.persistence.*;

public abstract class Pessoa {

    @Id
    @Column(name="id", nullable = false)
    private Integer id;

    @Column(name="nome", nullable = false)
    private String nome;

    @Column(name="apelido", nullable = false)
    private String apelido;

    @Column(name="data_nascimento", nullable = false)
    private Date dataNascimento;

    @Column(name="nacionalidade", nullable = false)
    private String nacionalidade;

    @Column(name="imagem", nullable = false)
    private String imagem;

    public Pessoa() {
    }

    public Pessoa(Integer id, String nome, String apelido, Date dataNascimento, String nacionalidade, String imagem) {
        this.id = id;
        this.nome = nome;
        this.apelido = apelido;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.imagem = imagem;
    }

    public Pessoa(String nome, String apelido, Date dataNascimento, String nacionalidade, String imagem) {
        this.nome = nome;
        this.apelido = apelido;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.imagem = imagem;

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

    

}
