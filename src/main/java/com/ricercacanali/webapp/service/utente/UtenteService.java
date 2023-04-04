package com.ricercacanali.webapp.service.utente;

import com.ricercacanali.webapp.exeptions.ArgumentNotValidException;
import com.ricercacanali.webapp.exeptions.ObjectNotFoundExeption;
import com.ricercacanali.webapp.modelDto.UtenteDto;

import java.util.List;

public interface UtenteService {
    void saveUtente (UtenteDto utenteDto) throws ArgumentNotValidException;
    List<UtenteDto> getAllUtente();
    void deleteUtente(int idUtente) throws ObjectNotFoundExeption;
    UtenteDto getUtente(int idUtente) throws ObjectNotFoundExeption;
    void updateUtente (UtenteDto utenteDto) throws ObjectNotFoundExeption, ArgumentNotValidException;
}
