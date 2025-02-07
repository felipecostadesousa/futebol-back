package com.example.demo.futebol.Pessoa;

import java.time.LocalDate;
import java.util.*;

import jakarta.persistence.*;

public abstract class Pessoa {


    @Column(name="nome", nullable = false)
    private String nome;

    @Column(name="apelido", nullable = false)
    private String apelido;

    @Column(name="data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name="nacionalidade", nullable = false)
    private String nacionalidade;

    @Column(name="imagem", nullable = false)
    private String imagem;

    public Pessoa() {
    }

    public Pessoa(String nome, String apelido, LocalDate dataNascimento, String nacionalidade, String imagem) {
        this.nome = nome;
        this.apelido = apelido;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.imagem = imagem;

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

    

}
