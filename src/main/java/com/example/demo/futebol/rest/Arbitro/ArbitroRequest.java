package com.example.demo.futebol.rest.Arbitro;

import java.util.Date;

import com.example.demo.futebol.Entidades.Pessoa.Pessoa;
import com.example.demo.futebol.rest.ValidOnCreation;
import com.example.demo.futebol.rest.ValidOnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;;

public class ArbitroRequest {

    @JsonProperty("id")
    private Integer id;

    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "Data de ínicio de contrato do árbitro não pode ser nulo.")
    @JsonProperty("contrato_inicio")
    private Date contratoInicio;


    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "Referência de pessoa não pode ser nulo.")
    @JsonProperty("pessoa")
    private Pessoa pessoa;

    public ArbitroRequest() {
    }

    public ArbitroRequest(Integer id, Date contratoInicio, Pessoa pessoa) {
        this.id = id;
        this.contratoInicio = contratoInicio;
        this.pessoa = pessoa;
    }


}
