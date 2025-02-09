package com.example.demo.futebol.Jogador;


import com.example.demo.futebol.Time.Time;
import jakarta.persistence.*;

@Entity
@Table(name = "jogador", schema = "public")
public class Jogador {

    @Id
    @Column(name = "id", nullable = false)
    private Integer idJogador;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "apelido", nullable = false)
    private String apelido;

    @Column(name = "data_nascimento", nullable = false)
    private String dataNascimento;

    @Column(name = "nacionalidade", nullable = false)
    private String nacionalidade;

    @Column(name = "imagem", nullable = false)
    private String imagemUrl;

    @Column(name = "posicao", nullable = false)
    private String posicao;

    @Column(name = "altura", nullable = false)
    private double altura;

    @Column(name = "pe_dominante", nullable = false)
    private String peDominante;

    @Column(name = "valor_mercado", nullable = false)
    private double valorMercado;

    @Column(name = "cidade_nascimento", nullable = false)
    private String cidadeNascimento;

    @Column(name = "numero_camisa", nullable = false)
    private Integer numeroCamisa;

    @Column(name = "agente", nullable = false)
    private String agente;

    @Column(name = "patrocinador", nullable = false)
    private String patrocinador;

    @Column(name = "redes_sociais", nullable = false)
    private String redeSocial;

    @Column(name = "data_inicio_contrato", nullable = false)
    private String dataInicioContrato;

    @Column(name = "data_fim_contrato", nullable = false)
    private String dataFimContrato;

    @OneToOne
    @JoinColumn(name = "id_time", referencedColumnName = "id")
    private Time time;

    public Jogador() {
    }

    public Jogador(Integer idJogador, String nome, String apelido, String dataNascimento, String nacionalidade, 
                   String imagemUrl, String posicao, double altura, String peDominante, double valorMercado, 
                   String cidadeNascimento, Integer numeroCamisa, String agente, String patrocinador, 
                   String redeSocial, String dataInicioContrato, String dataFimContrato, Time time) {
        this.idJogador = idJogador;
        this.nome = nome;
        this.apelido = apelido;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.imagemUrl = imagemUrl;
        this.posicao = posicao;
        this.altura = altura;
        this.peDominante = peDominante;
        this.valorMercado = valorMercado;
        this.cidadeNascimento = cidadeNascimento;
        this.numeroCamisa = numeroCamisa;
        this.agente = agente;
        this.patrocinador = patrocinador;
        this.redeSocial = redeSocial;
        this.dataInicioContrato = dataInicioContrato;
        this.dataFimContrato = dataFimContrato;
        this.time = time;
    }

    // Getters and setters

    public Integer getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Integer idJogador) {
        this.idJogador = idJogador;
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

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getPeDominante() {
        return peDominante;
    }

    public void setPeDominante(String peDominante) {
        this.peDominante = peDominante;
    }

    public double getValorMercado() {
        return valorMercado;
    }

    public void setValorMercado(double valorMercado) {
        this.valorMercado = valorMercado;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public Integer getNumeroCamisa() {
        return numeroCamisa;
    }

    public void setNumeroCamisa(Integer numeroCamisa) {
        this.numeroCamisa = numeroCamisa;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public String getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }

    public String getRedeSocial() {
        return redeSocial;
    }

    public void setRedeSocial(String redeSocial) {
        this.redeSocial = redeSocial;
    }

    public String getDataInicioContrato() {
        return dataInicioContrato;
    }

    public void setDataInicioContrato(String dataInicioContrato) {
        this.dataInicioContrato = dataInicioContrato;
    }

    public String getDataFimContrato() {
        return dataFimContrato;
    }

    public void setDataFimContrato(String dataFimContrato) {
        this.dataFimContrato = dataFimContrato;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
