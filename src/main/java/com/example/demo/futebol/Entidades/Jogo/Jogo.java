package com.example.demo.futebol.Entidades.Jogo;

import jakarta.persistence.*;
import java.util.*;

import com.example.demo.futebol.Entidades.Arbitro.Arbitro;
import com.example.demo.futebol.Entidades.Competicao.Competicao;
import com.example.demo.futebol.Entidades.Estadio.Estadio;

@Entity
@Table(name = "Jogo", schema = "public")
public class Jogo {

    @Id
    @Column(name="id", nullable = false)
    private Integer id;

    @Column(name="data_jogo", nullable = false)  
    private Date data;

    @Column(name="gols_time_casa", nullable = false)
    private Integer golsCasa;

    @Column(name="gols_time_visitante", nullable = false)
    private Integer golsVisitante;
    
    @OneToOne
    @JoinColumn(name="fk_estadio", referencedColumnName = "id_estadio")
    private Estadio estadio;

    @OneToOne
    @JoinColumn(name="fk_arbitro", referencedColumnName = "id_arbitro")
    private Arbitro arbitro;

    @ManyToOne
    @JoinColumn(name="fk_competicao", referencedColumnName = "id_competicao")
    private Competicao competicao;

    public Jogo() {
    }

    public Jogo(Integer id, Date data, Integer golsCasa, Integer golsVisitante, Estadio estadio, Arbitro arbitro, Competicao competicao){
        this.id = id;
        this.data = data;
        this.golsCasa = golsCasa;
        this.golsVisitante = golsVisitante;
        this.estadio = estadio;
        this.arbitro = arbitro;
        this.competicao = competicao;
    }

    public Jogo(Date data, Integer golsCasa, Integer golsVisitante, Estadio estadio, Arbitro arbitro, Competicao competicao){
        this.data = data;
        this.golsCasa = golsCasa;
        this.golsVisitante = golsVisitante;
        this.estadio = estadio;
        this.arbitro = arbitro;
        this.competicao = competicao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getGolsCasa() {
        return golsCasa;
    }

    public void setGolsCasa(Integer golsCasa) {
        this.golsCasa = golsCasa;
    }

    public Integer getGolsVisitante() {
        return golsVisitante;
    }

    public void setGolsVisitante(Integer golsVisitante) {
        this.golsVisitante = golsVisitante;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public Competicao getCompeticao() {
        return competicao;
    }

    public void setCompeticao(Competicao competicao) {
        this.competicao = competicao;
    }

    

}