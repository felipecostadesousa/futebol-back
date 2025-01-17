package com.example.demo.futebol;

import jakarta.persistence.*;

@Entity
@Table(name="Localizacao", schema = "public")
public class Localizacao {

    @Id
    @Column(name="id", nullable = false)
    private Integer id;

    @Column(name="pais", nullable = false)
    private String pais;

    @Column(name="regiao", nullable = false)
    private String regiao;

    @Column(name="estado", nullable = false)
    private String estado;

    @Column(name="cidade", nullable = false)
    private String cidade;

    public Localizacao() {
    }   
    public Localizacao(Integer id, String pais, String regiao, String estado, String cidade) {
        this.id = id;
        this.pais = pais;
        this.regiao = regiao;
        this.estado = estado;
        this.cidade = cidade;
    }

    public Localizacao(String pais, String regiao, String estado, String cidade) {
        this.pais = pais;
        this.regiao = regiao;
        this.estado = estado;
        this.cidade = cidade;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public String getRegiao() {
        return regiao;
    }
    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    

}
