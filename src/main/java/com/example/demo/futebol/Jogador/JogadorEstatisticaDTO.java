package com.example.demo.futebol.Jogador;

public class JogadorEstatisticaDTO {
  private String jogador;
  private String time;
  private Integer quantidadeJogosJogados;
  private Integer quantidadeGolsMarcados;
  private Integer quantidadeAssistenciasGols;

  public JogadorEstatisticaDTO(String jogador, String time, Integer quantidadeJogosJogados, Integer quantidadeGolsMarcados, Integer quantidadeAssistenciasGols) {
      this.jogador = jogador;
      this.time = time;
      this.quantidadeJogosJogados = quantidadeJogosJogados;
      this.quantidadeGolsMarcados = quantidadeGolsMarcados;
      this.quantidadeAssistenciasGols = quantidadeAssistenciasGols;
  }

  // Getters e Setters
  public String getJogador() {
      return jogador;
  }

  public void setJogador(String jogador) {
      this.jogador = jogador;
  }

  public String getTime() {
      return time;
  }

  public void setTime(String time) {
      this.time = time;
  }

  public Integer getQuantidadeJogosJogados() {
      return quantidadeJogosJogados;
  }

  public void setQuantidadeJogosJogados(Integer quantidadeJogosJogados) {
      this.quantidadeJogosJogados = quantidadeJogosJogados;
  }

  public Integer getQuantidadeGolsMarcados() {
      return quantidadeGolsMarcados;
  }

  public void setQuantidadeGolsMarcados(Integer quantidadeGolsMarcados) {
      this.quantidadeGolsMarcados = quantidadeGolsMarcados;
  }

  public Integer getQuantidadeAssistenciasGols() {
      return quantidadeAssistenciasGols;
  }
}
