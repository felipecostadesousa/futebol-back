package com.example.demo.futebol.Arbitro;


import jakarta.persistence.*;

@Entity
@Table(name="arbitro", schema = "public")
public class Arbitro{

    @Id
    @Column(name="id", nullable = false)
    private Integer id;

    @Column(name="contrato_inicio", nullable = false)
    private String contratoInicio;

    public Arbitro() {
    }

    public Arbitro(Integer id, String contratoInicio, String nome, String apelido, String dataNascimento, String nacionalidade, String imagem){
        super();
        this.id = id;
        this.contratoInicio = contratoInicio;
    }

    public Arbitro(String contratoInicio, String nome, String apelido, String dataNascimento, String nacionalidade, String imagem){
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

    public String getContratoInicio() {
        return contratoInicio;
    }

    public void setContratoInicio(String contratoInicio) {
        this.contratoInicio = contratoInicio;
    }

   

}
