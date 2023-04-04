package com.ricercacanali.webapp.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="canali")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Canali implements Serializable {

    @Id
    @Column(name = "id_canali")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "descrizione")
    private String descrizione;

    @ManyToMany(mappedBy = "canali", cascade = { CascadeType.ALL})
    @EqualsAndHashCode.Exclude
    private Set<Ricerche> ricerche = new HashSet<>();

    public Canali(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Canali{" +
                "id=" + id +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }
}
