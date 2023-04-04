package com.ricercacanali.webapp.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="ricerche")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Ricerche{

    @Id
    @Column(name = "id_ricerche")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "data_ricerca")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dataRicerca;

    @ManyToMany(cascade = { CascadeType.MERGE})
    @EqualsAndHashCode.Exclude
    @JoinTable(name = "ricerche_canali",
            joinColumns = {@JoinColumn(name = "id_ricerche")},
            inverseJoinColumns = {@JoinColumn(name = "id_canali")})
    private Set<Canali> canali = new HashSet<>();

    @JoinColumn(name="id_user")
    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private Utente utente;

}
