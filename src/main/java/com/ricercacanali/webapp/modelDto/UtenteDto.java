package com.ricercacanali.webapp.modelDto;

import lombok.Data;

@Data
public class UtenteDto {

    private int id;

    private String nome;

    private String cognome;

    private String email;

    public UtenteDto(int id, String nome, String cognome, String email) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }

    public UtenteDto() {
    }
}
