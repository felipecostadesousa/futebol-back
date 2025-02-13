package com.example.demo.futebol.view;




public class EstatisticasJogadoresView {

    private String nome_jogador;

    private String nome_time;

    private Integer quantidade_jogos_jogados;

    private Integer quantidade_gols_marcados;

    private Integer quantidade_assistencias_gols;


    public EstatisticasJogadoresView(){}

    public String getNome_jogador() {
        return nome_jogador;
    }

    public void setNome_jogador(String nome_jogador) {
        this.nome_jogador = nome_jogador;
    }

    public String getNome_time() {
        return nome_time;
    }

    public void setNome_time(String nome_time) {
        this.nome_time = nome_time;
    }

    public Integer getQuantidade_jogos_jogados() {
        return quantidade_jogos_jogados;
    }

    public void setQuantidade_jogos_jogados(Integer quantidade_jogos_jogados) {
        this.quantidade_jogos_jogados = quantidade_jogos_jogados;
    }

    public Integer getQuantidade_gols_marcados() {
        return quantidade_gols_marcados;
    }

    public void setQuantidade_gols_marcados(Integer quantidade_gols_marcados) {
        this.quantidade_gols_marcados = quantidade_gols_marcados;
    }

    public Integer getQuantidade_assistencias_gols() {
        return quantidade_assistencias_gols;
    }

    public void setQuantidade_assistencias_gols(Integer quantidade_assistencias_gols) {
        this.quantidade_assistencias_gols = quantidade_assistencias_gols;
    }
}

