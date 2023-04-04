package com.ricercacanali.webapp.modelDto;


import com.ricercacanali.webapp.model.Ricerche;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CanaliDto {
    private int id;
    private String descrizione;
    private Set<Integer> idRicerche = new HashSet<>();

    @Override
    public String toString() {
        return "CanaliDto{" +
                "id=" + id +
                ", descrizione='" + descrizione + '\'' +
                ", idRicerche=" + idRicerche +
                '}';
    }
}
