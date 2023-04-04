package com.ricercacanali.webapp.service.utente;

import com.ricercacanali.webapp.exeptions.ArgumentNotValidException;
import com.ricercacanali.webapp.exeptions.ObjectNotFoundExeption;
import com.ricercacanali.webapp.model.Utente;
import com.ricercacanali.webapp.modelDto.UtenteDto;
import com.ricercacanali.webapp.repository.UtenteRepo;
import com.ricercacanali.webapp.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    private UtenteRepo utenteRepo;

    @Override
    public void saveUtente(UtenteDto utenteDto) throws ArgumentNotValidException {
        if (utenteRepo.findById(utenteDto.getId()).isPresent())
            throw new ArgumentNotValidException(400, "impossibile inserire id utente");
        Utente utente = new Utente();
        Converter.convertUtenteDtoToUtente(utenteDto, utente);
        utenteRepo.save(utente);
    }

    @Override
    public List<UtenteDto> getAllUtente(){
        List<UtenteDto> listUtente = new ArrayList<>();
        Converter.convertUtenteListToDto(utenteRepo.findAll(), listUtente);
        return listUtente;
    }

    @Override
    public void deleteUtente(int idUtente) throws ObjectNotFoundExeption {
        if (!utenteRepo.findById(idUtente).isPresent())
            throw new ObjectNotFoundExeption(404, "utente da eliminare non trovato");
        utenteRepo.deleteById(idUtente);
    }

    @Override
    public UtenteDto getUtente(int idUtente) throws ObjectNotFoundExeption {
        if(!utenteRepo.findById(idUtente).isPresent())
            throw new ObjectNotFoundExeption(404, "utente non trovato");
        UtenteDto utenteDto = new UtenteDto();
        Converter.convertUtenteToUtenteDto(utenteRepo.findById(idUtente).get(), utenteDto);
        return utenteDto;
    }

    @Override
    public void updateUtente(UtenteDto utenteDto) throws ObjectNotFoundExeption{
        if (!utenteRepo.findById(utenteDto.getId()).isPresent())
            throw new ObjectNotFoundExeption(404, "utente da aggiornare non trovato");
        Utente utente = new Utente();
        Converter.convertUtenteDtoToUtente(utenteDto, utente);
        utenteRepo.save(utente);
    }
}
