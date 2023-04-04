package com.ricercacanali.webapp.service.ricerche;

import com.ricercacanali.webapp.exeptions.ArgumentNotValidException;
import com.ricercacanali.webapp.exeptions.ObjectNotFoundExeption;
import com.ricercacanali.webapp.model.Canali;
import com.ricercacanali.webapp.model.Ricerche;
import com.ricercacanali.webapp.modelDto.RicercheDto;
import com.ricercacanali.webapp.repository.CanaliRepo;
import com.ricercacanali.webapp.repository.RicercheRepo;
import com.ricercacanali.webapp.repository.UtenteRepo;
import com.ricercacanali.webapp.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RicercheServiceImpl implements RicercheService{

    @Autowired
    RicercheRepo ricercheRepo;

    @Autowired
    UtenteRepo utenteRepo;

    @Autowired
    CanaliRepo canaliRepo;

    @Override
    public void saveRicerca(RicercheDto ricercheDto) throws ObjectNotFoundExeption, ArgumentNotValidException {
        if (!ricercheRepo.findById(ricercheDto.getId()).isPresent())
            throw new ArgumentNotValidException(400, "impossibile inserire id ricerca");
        Ricerche ricerche = new Ricerche();
        Converter.convertRicercheDtoToRicerche(ricercheDto, ricerche);
        for (Integer i : ricercheDto.getIdCanali()) {
            Canali c = canaliRepo.findById(i).get();
            if (c.getDescrizione().isEmpty())
                throw new ObjectNotFoundExeption( 404, "Il canale non esiste");
            ricerche.getCanali().add(c);
        }
        if (!utenteRepo.findById(ricercheDto.getUtente()).isPresent())
            throw new ObjectNotFoundExeption( 404, "utente non esistente");
        ricercheRepo.save(ricerche);
    }

    @Override
    public void deleteRicerca(int idRicerca) throws ObjectNotFoundExeption {
        if (!ricercheRepo.findById(idRicerca).isPresent())
            throw new ObjectNotFoundExeption(404, "ricerca da eliminare non trovata");
        ricercheRepo.deleteById(idRicerca);
    }

    @Override
    public RicercheDto getRicerca(int idRicerca) throws ObjectNotFoundExeption {
        if(!ricercheRepo.findById(idRicerca).isPresent())
            throw new ObjectNotFoundExeption(404, "ricerca non trovata");
        RicercheDto ricercheDto = new RicercheDto();
        Converter.convertRicercheToRicercheDto(ricercheRepo.findById(idRicerca).get(), ricercheDto);
        ricercheDto.setUtente(ricercheRepo.findById(idRicerca).get().getUtente().getId());
        return ricercheDto;
    }

    @Override
    public List<RicercheDto> getAllRicerche(){
        List<RicercheDto> list = new ArrayList<>();
        Converter.convertRicercheListToDto(ricercheRepo.findAll(), list);
        return list;
    }

    @Override
    public void updateRicerca(RicercheDto ricercheDto) throws ObjectNotFoundExeption{
        if (!ricercheRepo.findById(ricercheDto.getId()).isPresent())
            throw new ObjectNotFoundExeption(404, "nessuna riceraca da aggiornare");
        Ricerche ricerche = new Ricerche();
        Converter.convertRicercheDtoToRicerche(ricercheDto, ricerche);
        for (Integer i : ricercheDto.getIdCanali()) {
            Canali c = canaliRepo.findById(i).get();
            if (c.getDescrizione().isEmpty())
                throw new ObjectNotFoundExeption( 404, "Il canale non esiste");
            ricerche.getCanali().add(c);
        }
        if (!utenteRepo.findById(ricercheDto.getUtente()).isPresent())
            throw new ObjectNotFoundExeption( 404, "utente non esistente");
        ricercheRepo.save(ricerche);
    }
}
