package com.example.demo.futebol.Entidades.Tecnico;

import java.util.*;

import com.example.demo.futebol.Entidades.Pessoa.Pessoa;
import com.example.demo.futebol.Entidades.Time.Time;

import jakarta.persistence.*;

@Entity
@Table(name="tecnico", schema = "public")
public class Tecnico {

    @Id
    @Column(name="id", nullable = false)
    private Integer id;

    @Column(name="contrato_inicio", nullable = false)
    private Date contratoInicio;

    @Column(name="contrato_fim", nullable = false)
    private Date contratoFim;

    @Column(name="cidade_nascimento", nullable = false)
    private String cidadeNascimento;

    @OneToOne
    @JoinColumn(name = "fk_time", referencedColumnName="id_time")
    private Time time;

    @OneToOne
    @JoinColumn(name = "fk_pessoa", referencedColumnName="id_pessoa")
    private Pessoa pessoa;

    public Tecnico() {
    }

    public Tecnico(Integer id, Date contratoInicio, Date contratoFim, String cidadeNascimento, Time time, Pessoa pessoa) {
        this.id = id;
        this.contratoInicio = contratoInicio;
        this.contratoFim = contratoFim;
        this.cidadeNascimento = cidadeNascimento;
        this.time = time;
        this.pessoa = pessoa;
    }

    public Tecnico(Date contratoInicio, Date contratoFim, String cidadeNascimento, Time time, Pessoa pessoa) {
        this.contratoInicio = contratoInicio;
        this.contratoFim = contratoFim;
        this.cidadeNascimento = cidadeNascimento;
        this.time = time;
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

    public Date getContratoFim() {
        return contratoFim;
    }

    public void setContratoFim(Date contratoFim) {
        this.contratoFim = contratoFim;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
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
