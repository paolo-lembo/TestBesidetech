package com.ricercacanali.webapp.service.canali;

import com.ricercacanali.webapp.exeptions.ArgumentNotValidException;
import com.ricercacanali.webapp.exeptions.ObjectNotFoundExeption;
import com.ricercacanali.webapp.modelDto.CanaliDto;

import java.util.List;

public interface CanaliService {

    void saveCanali (CanaliDto canaliDto) throws ArgumentNotValidException;
    void deleteCanali(int id) throws ObjectNotFoundExeption;
    CanaliDto getCanali (int id) throws ObjectNotFoundExeption;
    List<CanaliDto> getAllCanali() throws ObjectNotFoundExeption;
    void updateCanali (CanaliDto canaliDto) throws ObjectNotFoundExeption, ArgumentNotValidException;
}
