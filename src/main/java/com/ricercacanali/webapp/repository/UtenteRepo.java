package com.ricercacanali.webapp.repository;

import com.ricercacanali.webapp.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepo extends JpaRepository<Utente, Integer> {
}
