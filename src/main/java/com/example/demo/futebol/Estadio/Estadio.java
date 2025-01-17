package com.example.demo.futebol.Estadio;

import java.util.*;

import com.example.demo.futebol.Localizacao.Localizacao;

import jakarta.persistence.*;

@Entity
@Table(name="estadio", schema = "public")
public class Estadio {

    @Id
    @Column(name="id", nullable = false)
    private Integer id;

    @Column(name="data_fundacao", nullable = false)
    private Date dataFundacao;

    @Column(name="nome", nullable = false)
    private String nome;

    @Column(name="capacidade_pessoas", nullable = false)
    private Integer capacidadePessoas;

    @ManyToOne
    @JoinColumn(name = "fk_localizacao", referencedColumnName = "id_localizacao")
    private Localizacao localizacao;

    public Estadio() {
    }

    public Estadio(Integer id, Date dataFundacao, String nome, Integer capacidadePessoas, Localizacao localizacao) {
        this.id = id;
        this.dataFundacao = dataFundacao;
        this.nome = nome;
        this.capacidadePessoas = capacidadePessoas;
        this.localizacao = localizacao;
    }

    public Estadio(Date dataFundacao, String nome, Integer capacidadePessoas, Localizacao localizacao) {
        this.dataFundacao = dataFundacao;
        this.nome = nome;
        this.capacidadePessoas = capacidadePessoas;
        this.localizacao = localizacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCapacidadePessoas() {
        return capacidadePessoas;
    }

    public void setCapacidadePessoas(Integer capacidadePessoas) {
        this.capacidadePessoas = capacidadePessoas;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    


}
