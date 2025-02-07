package com.example.demo.futebol.Titulo;

import jakarta.persistence.*;

@Entity
@Table(name="titulo", schema = "public")
public class Titulo {

    @Id
    @Column(name="id", nullable = false)
    private Integer id;

    @Column(name="nome", nullable = false)
    private String nome;

    public Titulo() {
    }

    public Titulo(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Titulo transform(TituloRequest request) {
        Titulo titulo = new Titulo (request.getId(), request.getNome());
        return titulo;
    }

    public Titulo(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
