package com.ricercacanali.webapp.service.ricerche;

import com.ricercacanali.webapp.exeptions.ArgumentNotValidException;
import com.ricercacanali.webapp.exeptions.ObjectNotFoundExeption;
import com.ricercacanali.webapp.modelDto.RicercheDto;
import com.ricercacanali.webapp.modelDto.UtenteDto;

import java.util.List;

public interface RicercheService{

    void saveRicerca (RicercheDto ricercheDto) throws ObjectNotFoundExeption, ArgumentNotValidException;
    void deleteRicerca(int idRicerca) throws ObjectNotFoundExeption;
    RicercheDto getRicerca(int idRicerca) throws ObjectNotFoundExeption;
    List<RicercheDto> getAllRicerche() throws ObjectNotFoundExeption;
    void updateRicerca (RicercheDto ricercheDto) throws ObjectNotFoundExeption, ArgumentNotValidException;
}
