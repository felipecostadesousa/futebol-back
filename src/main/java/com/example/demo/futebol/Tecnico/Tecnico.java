package com.example.demo.futebol.Tecnico;

import com.example.demo.futebol.Time.Time;

import jakarta.persistence.*;

@Entity
@Table(name="tecnico", schema = "public")
public class Tecnico {

    @Id
    @Column(name="id", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "apelido", nullable = false)
    private String apelido;

    @Column(name = "nacionalidade", nullable = false)
    private String nacionalidade;

    @Column(name = "data_nascimento", nullable = false)
    private String dataNascimento;

    @Column(name = "imagem", nullable = false)
    private String imagemUrl;

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
        this.id = id;
        this.nome = nome;
        this.apelido = apelido;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.imagemUrl = imagem;
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

    public String getNome() {
      return nome;
    }

    public void setNome(String nome) {
      this.nome = nome;
    }

    public String getApelido() {
      return apelido;
    }

    public void setApelido(String apelido) {
      this.apelido = apelido;
    }

    public String getDataNascimento() {
      return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
      this.dataNascimento = dataNascimento;
    }

    public String getNacionalidade() {
      return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
      this.nacionalidade = nacionalidade;
    }

    public String getImagemUrl() {
      return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
      this.imagemUrl = imagemUrl;
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
