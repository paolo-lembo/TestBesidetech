package com.ricercacanali.webapp.repository;

import com.ricercacanali.webapp.model.Canali;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CanaliRepo extends JpaRepository<Canali, Integer> {
}
