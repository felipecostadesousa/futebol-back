package com.example.demo.futebol.Arbitro;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name="arbitro", schema = "public")
public class Arbitro{

    @Id
    @Column(name="id", nullable = false)
    private Integer id;

    @Column(name="contrato_inicio", nullable = false)
    private LocalDate contratoInicio;

    public Arbitro() {
    }

    public Arbitro(Integer id, LocalDate contratoInicio, String nome, String apelido, LocalDate dataNascimento, String nacionalidade, String imagem){
        super();
        this.id = id;
        this.contratoInicio = contratoInicio;
    }

    public Arbitro(LocalDate contratoInicio, String nome, String apelido, LocalDate dataNascimento, String nacionalidade, String imagem){
        super();
        this.contratoInicio = contratoInicio;
    }

    public void transform(ArbitroRequest request) {
        Arbitro arbitro = new Arbitro(request.getContratoInicio(), request.getNome(), request.getApelido(), request.getDataNascimento(), request.getNacionalidade(), request.getImagem());
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

   

}
