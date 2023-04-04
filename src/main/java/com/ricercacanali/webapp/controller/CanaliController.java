package com.ricercacanali.webapp.controller;

import com.ricercacanali.webapp.exeptions.ArgumentNotValidException;
import com.ricercacanali.webapp.exeptions.ObjectNotFoundExeption;
import com.ricercacanali.webapp.modelDto.CanaliDto;
import com.ricercacanali.webapp.service.canali.CanaliService;
import com.ricercacanali.webapp.util.PathConstant;
import com.ricercacanali.webapp.util.ResponseConstant;
import com.ricercacanali.webapp.util.RestResponse;
import com.ricercacanali.webapp.util.RestResponseCollection;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PathConstant.ROOT+PathConstant.CANALI)
public class CanaliController {

    private static final Logger LOGGER = Logger.getLogger(CanaliController.class);

    @Autowired
    private CanaliService canaliService;

    @PostMapping(PathConstant.SAVE)
    public RestResponse<CanaliDto> saveCanale(@RequestBody CanaliDto c){
        try {
            canaliService.saveCanali(c);
            LOGGER.info(ResponseConstant.CANALI_SAVED + " con id: " + c.getId());
            return new RestResponse(200, ResponseConstant.CANALI_SAVED, null);
        } catch (ArgumentNotValidException e) {
            LOGGER.error(ResponseConstant.CANALI_NOT_SAVED + " exception: " + e.getMessage());
            return new RestResponse(e.getCode(), e.getDescription(), null);
        } catch (Exception e) {
            LOGGER.error(ResponseConstant.INTERNAL_SERVER_ERROR + " exception: " + e.getMessage());
            return new RestResponse(500, ResponseConstant.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping(PathConstant.UPDATE)
    public RestResponse<CanaliDto> updateCanale(@RequestBody CanaliDto c) {
        try {
            canaliService.updateCanali(c);
            LOGGER.info(ResponseConstant.CANALI_UPDATED + " dati aggiornati canale: " + c);
            return new RestResponse(200, ResponseConstant.CANALI_UPDATED, null);
        } catch (ObjectNotFoundExeption e) {
            LOGGER.error(ResponseConstant.CANALI_NOT_FOUND + " exception: " + e.getMessage());
            return new RestResponse(e.getCode(), e.getDescription(), null);
        } catch (Exception e) {
            LOGGER.error(ResponseConstant.INTERNAL_SERVER_ERROR + " exception: " + e.getMessage());
            return new RestResponse(500, ResponseConstant.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping(PathConstant.DELETE)
    public RestResponse deleteCanale(@PathVariable("id") int id) {
        try {
            canaliService.deleteCanali(id);
            LOGGER.info(ResponseConstant.CANALI_DELETED  + " id utente eliminato: " + id);
            return new RestResponse(200, ResponseConstant.CANALI_DELETED, null);
        }  catch (ObjectNotFoundExeption e) {
            LOGGER.error(ResponseConstant.CANALI_NOT_FOUND + " exception: " + e.getMessage());
            return new RestResponse(e.getCode(), e.getDescription(), null);
        } catch (Exception e) {
            LOGGER.error(ResponseConstant.INTERNAL_SERVER_ERROR + " exception: " + e.getMessage());
            return new RestResponse(500, ResponseConstant.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping(PathConstant.FIND)
    public RestResponse getCanale(@PathVariable("id") int id){
        try {
            CanaliDto c = canaliService.getCanali(id);
            LOGGER.info(ResponseConstant.CANALI_FOUND + " dati utente: " + c);
            return new RestResponse(200, ResponseConstant.CANALI_FOUND, c);
        }  catch (ObjectNotFoundExeption e) {
            LOGGER.error(ResponseConstant.CANALI_NOT_FOUND+ " exception: " + e.getMessage());
            return new RestResponse(e.getCode(), e.getDescription(), null);
        } catch (Exception e) {
            LOGGER.error(ResponseConstant.INTERNAL_SERVER_ERROR + " exception: " + e.getMessage());
            return new RestResponse(500, ResponseConstant.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping(PathConstant.GETALL)
    public RestResponseCollection getAll(){
        try {
            List<CanaliDto> list = canaliService.getAllCanali();
            LOGGER.info(ResponseConstant.CANALI_LIST_FOUND);
            return new RestResponseCollection(200, ResponseConstant.CANALI_LIST_FOUND, list);
        } catch (Exception e) {
            LOGGER.error(ResponseConstant.INTERNAL_SERVER_ERROR + " exception: " + e.getMessage());
            return new RestResponseCollection(500, ResponseConstant.INTERNAL_SERVER_ERROR, null);
        }
    }
}
