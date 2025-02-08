package com.example.demo.futebol.Estatistica;

import com.example.demo.futebol.Jogador.Jogador;

import jakarta.persistence.*;

@Entity
@Table(name="estatistica", schema = "public")
public class Estatistica {

    @Id
    @Column(name="id", nullable = false)
    private Integer id;

    @Column(name="quantidade_jogos_jogados", nullable = false)
    private Integer jogos;

    @Column(name="quantidade_gols_marcados", nullable = false)
    private Integer gols;

    @Column(name="quantidade_assistencias_gols", nullable = false)
    private Integer assistencias;

    @OneToOne
    @JoinColumn(name="id_jogador", referencedColumnName = "id")
    private Jogador jogador;

    public Estatistica(){

    }

    public Estatistica(Integer id, Integer jogos, Integer gols, Integer assistencias, Jogador jogador){
        this.id = id;
        this.jogos = jogos;
        this.gols = gols;
        this.assistencias = assistencias;
        this.jogador = jogador;
    }

    public Estatistica(Integer jogos, Integer gols, Integer assistencias, Jogador jogador){
        this.jogos = jogos;
        this.gols = gols;
        this.assistencias = assistencias;
        this.jogador = jogador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJogos() {
        return jogos;
    }

    public void setJogos(Integer jogos) {
        this.jogos = jogos;
    }

    public Integer getGols() {
        return gols;
    }

    public void setGols(Integer gols) {
        this.gols = gols;
    }

    public Integer getAssistencias() {
        return assistencias;
    }

    public void setAssistencias(Integer assistencias) {
        this.assistencias = assistencias;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    

}
