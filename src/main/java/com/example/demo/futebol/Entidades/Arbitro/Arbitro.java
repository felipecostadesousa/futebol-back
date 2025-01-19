package com.example.demo.futebol.Entidades.Arbitro;

import java.util.*;

import com.example.demo.futebol.Entidades.Pessoa.Pessoa;

import jakarta.persistence.*;


@Entity
@Table(name="arbitro", schema = "public")
public class Arbitro {

    @Id
    @Column(name="id", nullable = false)
    private Integer id;

    @Column(name="contrato_inicio", nullable = false)
    private Date contratoInicio;

    @OneToOne
    @JoinColumn(name = "fk_pessoa", referencedColumnName="id_pessoa")
    private Pessoa pessoa;

    public Arbitro() {
    }

    public Arbitro(Integer id, Date contratoInicio, Pessoa pessoa){
        this.id = id;
        this.contratoInicio = contratoInicio;
        this.pessoa = pessoa;
    }

    public Arbitro(Date contratoInicio, Pessoa pessoa){
        this.contratoInicio = contratoInicio;
        this.pessoa = pessoa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getContratoInicio() {
        return contratoInicio;
    }

    public void setContratoInicio(Date contratoInicio) {
        this.contratoInicio = contratoInicio;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    

}
