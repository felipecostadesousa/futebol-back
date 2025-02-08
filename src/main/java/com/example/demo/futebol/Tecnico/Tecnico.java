package com.example.demo.futebol.Tecnico;

import java.time.LocalDate;

import com.example.demo.futebol.Pessoa.Pessoa;
import com.example.demo.futebol.Time.Time;

import jakarta.persistence.*;

@Entity
@Table(name="tecnico", schema = "public")
public class Tecnico extends Pessoa {

    @Id
    @Column(name="id", nullable = false)
    private Integer id;

    @Column(name="contrato_inicio", nullable = false)
    private LocalDate contratoInicio;

    @Column(name="contrato_fim", nullable = false)
    private LocalDate contratoFim;

    @Column(name="cidade_nascimento", nullable = false)
    private String cidadeNascimento;

    @OneToOne
    @JoinColumn(name = "id_time", referencedColumnName="id")
    private Time time;

    public Tecnico() {
    }

    public Tecnico(Integer id, String nome, String apelido, LocalDate dataNascimento, String nacionalidade, String imagem, LocalDate contratoInicio, LocalDate contratoFim, String cidadeNascimento, Time time) {
        super();
        this.id = id;
        this.contratoInicio = contratoInicio;
        this.contratoFim = contratoFim;
        this.cidadeNascimento = cidadeNascimento;
        this.time = time;
    }

    public Tecnico(String nome, String apelido, LocalDate dataNascimento, String nacionalidade, String imagem, LocalDate contratoInicio, LocalDate contratoFim, String cidadeNascimento, Time time) {
        super();
        this.contratoInicio = contratoInicio;
        this.contratoFim = contratoFim;
        this.cidadeNascimento = cidadeNascimento;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getContratoInicio() {
        return contratoInicio;
    }

    public void setContratoInicio(LocalDate contratoInicio) {
        this.contratoInicio = contratoInicio;
    }

    public LocalDate getContratoFim() {
        return contratoFim;
    }

    public void setContratoFim(LocalDate contratoFim) {
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
    

}
