package com.ricercacanali.webapp.service.canali;

import com.ricercacanali.webapp.exeptions.ArgumentNotValidException;
import com.ricercacanali.webapp.exeptions.ObjectNotFoundExeption;
import com.ricercacanali.webapp.model.Canali;
import com.ricercacanali.webapp.modelDto.CanaliDto;
import com.ricercacanali.webapp.repository.CanaliRepo;
import com.ricercacanali.webapp.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CanaliServiceImpl implements CanaliService{

    @Autowired
    CanaliRepo canaliRepo;


    @Override
    public void saveCanali(CanaliDto canaliDto) throws ArgumentNotValidException {
        if (canaliRepo.findById(canaliDto.getId()).isPresent())
            throw new ArgumentNotValidException(400, "impossibile inserire id canale");
        Canali canali = new Canali();
        Converter.convertCanaliDtoToCanali(canaliDto, canali);
        canaliRepo.save(canali);
    }

    @Override
    public void deleteCanali(int id) throws ObjectNotFoundExeption {
        if (!canaliRepo.findById(id).isPresent())
            throw new ObjectNotFoundExeption(404, "canale da eliminare non trovato");
        canaliRepo.deleteById(id);
    }

    @Override
    public CanaliDto getCanali(int id) throws ObjectNotFoundExeption {
        if(!canaliRepo.findById(id).isPresent())
            throw new ObjectNotFoundExeption(404, "canale non trovato");
        CanaliDto canaliDto = new CanaliDto();
        Converter.convertCanaliToCanaliDto(canaliRepo.findById(id).get(), canaliDto);
        return canaliDto;
    }

    @Override
    public List<CanaliDto> getAllCanali(){
        List<CanaliDto> list= new ArrayList<>();
        Converter.convertCanaliListToDto(canaliRepo.findAll(), list);
        return list;
    }

    @Override
    public void updateCanali(CanaliDto canaliDto) throws ObjectNotFoundExeption{
        if (!canaliRepo.findById(canaliDto.getId()).isPresent())
            throw new ObjectNotFoundExeption(404, "canale da aggiornare non trovato");
        Canali canali = new Canali();
        Converter.convertCanaliDtoToCanali(canaliDto, canali);
        canaliRepo.save(canali);
    }
}
