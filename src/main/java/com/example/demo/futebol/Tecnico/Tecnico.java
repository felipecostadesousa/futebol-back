package com.example.demo.futebol.Tecnico;

import com.example.demo.futebol.Time.Time;

import jakarta.persistence.*;

@Entity
@Table(name="tecnico", schema = "public")
public class Tecnico {

    @Id
    @Column(name="id", nullable = false)
    private Integer id;

    @Column(name="contrato_inicio", nullable = false)
    private String contratoInicio;

    @Column(name="contrato_fim", nullable = false)
    private String contratoFim;

    @Column(name="cidade_nascimento", nullable = false)
    private String cidadeNascimento;

    @OneToOne
    @JoinColumn(name = "id_time", referencedColumnName="id")
    private Time time;

    public Tecnico() {
    }

    public Tecnico(Integer id, String nome, String apelido, String dataNascimento, String nacionalidade, String imagem, String contratoInicio, String contratoFim, String cidadeNascimento, Time time) {
        super();
        this.id = id;
        this.contratoInicio = contratoInicio;
        this.contratoFim = contratoFim;
        this.cidadeNascimento = cidadeNascimento;
        this.time = time;
    }

    public Tecnico(String nome, String apelido, String dataNascimento, String nacionalidade, String imagem, String contratoInicio, String contratoFim, String cidadeNascimento, Time time) {
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

    public String getContratoInicio() {
        return contratoInicio;
    }

    public void setContratoInicio(String contratoInicio) {
        this.contratoInicio = contratoInicio;
    }

    public String getContratoFim() {
        return contratoFim;
    }

    public void setContratoFim(String contratoFim) {
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
