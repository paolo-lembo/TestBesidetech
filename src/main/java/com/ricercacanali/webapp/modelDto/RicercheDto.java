package com.ricercacanali.webapp.modelDto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class RicercheDto {

    private int id;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dataRicerca;

    private int utente;

    private Set<Integer> idCanali = new HashSet<>();

    public RicercheDto() {
        this.dataRicerca = new Date();
    }

}
