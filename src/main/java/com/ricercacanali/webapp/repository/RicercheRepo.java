package com.ricercacanali.webapp.repository;

import com.ricercacanali.webapp.model.Ricerche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RicercheRepo extends JpaRepository<Ricerche, Integer> {
}
