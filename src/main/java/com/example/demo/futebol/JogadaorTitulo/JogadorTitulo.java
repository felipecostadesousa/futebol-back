package com.example.demo.futebol.JogadaorTitulo;

import com.example.demo.futebol.Jogador.Jogador;
import com.example.demo.futebol.Titulo.Titulo;

import jakarta.persistence.*;

@Entity
@Table(name="jogador_titulo", schema = "public")
public class JogadorTitulo {

    @Id
    @Column(name="id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id_jogador", referencedColumnName = "id")
    private Jogador jogador;

    @ManyToOne
    @JoinColumn(name="id_titulo", referencedColumnName = "id")
    private Titulo titulo;

    public JogadorTitulo() {
    }

    public JogadorTitulo(Integer id, Jogador jogador, Titulo titulo){
        this.id = id;
        this.jogador = jogador;
        this.titulo = titulo;
    }

    public JogadorTitulo(Jogador jogador, Titulo titulo){
        this.jogador = jogador;
        this.titulo = titulo;
    }

    public JogadorTitulo(Titulo titulo){
        this.titulo = titulo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

}
