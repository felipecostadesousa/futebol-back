package com.example.demo.futebol.Arbitro;

import java.util.*;

import com.example.demo.futebol.Pessoa.Pessoa;

import jakarta.persistence.*;


@Entity
@Table(name="arbitro", schema = "public")
public class Arbitro extends Pessoa{

    @Id
    @Column(name="id", nullable = false)
    private Integer id;

    @Column(name="contrato_inicio", nullable = false)
    private Date contratoInicio;

    public Arbitro() {
    }

    public Arbitro(Integer id, Date contratoInicio, String nome, String apelido, Date dataNascimento, String nacionalidade, String imagem){
        super();
        this.id = id;
        this.contratoInicio = contratoInicio;
    }

    public Arbitro(Date contratoInicio, String nome, String apelido, Date dataNascimento, String nacionalidade, String imagem){
        super();
        this.contratoInicio = contratoInicio;
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

}
