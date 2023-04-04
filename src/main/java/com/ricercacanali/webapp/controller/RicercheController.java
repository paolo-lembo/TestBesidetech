package com.ricercacanali.webapp.controller;

import com.ricercacanali.webapp.exeptions.ArgumentNotValidException;
import com.ricercacanali.webapp.exeptions.ObjectNotFoundExeption;
import com.ricercacanali.webapp.modelDto.RicercheDto;
import com.ricercacanali.webapp.service.ricerche.RicercheService;
import com.ricercacanali.webapp.util.PathConstant;
import com.ricercacanali.webapp.util.ResponseConstant;
import com.ricercacanali.webapp.util.RestResponse;
import com.ricercacanali.webapp.util.RestResponseCollection;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PathConstant.ROOT+PathConstant.RICERCHE)
public class RicercheController {

    private static final Logger LOGGER = Logger.getLogger(RicercheController.class);

    @Autowired
    private RicercheService ricercheService;

    @PostMapping(PathConstant.SAVE)
    public RestResponse<RicercheDto> saveRicerca(@RequestBody RicercheDto r){
        try {
            ricercheService.saveRicerca(r);
            LOGGER.info(ResponseConstant.RICERCHE_SAVED + " con id: " + r.getId());
            return new RestResponse(200, ResponseConstant.RICERCHE_SAVED, null);
        } catch (ArgumentNotValidException e) {
            LOGGER.error(ResponseConstant.RICERCHE_NOT_SAVED + " exception: " + e.getMessage());
            return new RestResponse(e.getCode(), e.getDescription(), null);
        } catch (ObjectNotFoundExeption e) {
            LOGGER.error(ResponseConstant.CANALI_NOT_FOUND + " exception: " + e.getMessage());
            return new RestResponse(e.getCode(), e.getDescription(), null);
        } catch (Exception e) {
            LOGGER.error(ResponseConstant.INTERNAL_SERVER_ERROR + " exception: " + e.getMessage());
            return new RestResponse(500, ResponseConstant.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping(PathConstant.UPDATE)
    public RestResponse<RicercheDto> updateRicerca(@RequestBody RicercheDto r){
        try {
            ricercheService.updateRicerca(r);
            LOGGER.info(ResponseConstant.RICERCHE_UPDATED + " dati aggiornati ricerca: " + r);
            return new RestResponse(200, ResponseConstant.RICERCHE_UPDATED, null);
        } catch (ObjectNotFoundExeption e) {
            LOGGER.error(ResponseConstant.RICERCHE_NOT_FOUND + " exception: " + e.getMessage());
            return new RestResponse(e.getCode(), e.getDescription(), null);
        } catch (Exception e) {
            LOGGER.error(ResponseConstant.INTERNAL_SERVER_ERROR + " exception: " + e.getMessage());
            return new RestResponse(500, ResponseConstant.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping(PathConstant.DELETE)
    public RestResponse deleteRicerca(@PathVariable("id") int id) {
        try {
            ricercheService.deleteRicerca(id);
            LOGGER.info(ResponseConstant.RICERCHE_DELETED + " id ricerca eliminato: " + id);
            return new RestResponse(200, ResponseConstant.RICERCHE_DELETED, null);
        } catch (ObjectNotFoundExeption e) {
            LOGGER.error(ResponseConstant.RICERCHE_NOT_FOUND + " exception: " + e.getMessage());
            return new RestResponse(e.getCode(), e.getDescription(), null);
        } catch (Exception e) {
            LOGGER.error(ResponseConstant.INTERNAL_SERVER_ERROR + " exception: " + e.getMessage());
            return new RestResponse(500, ResponseConstant.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping(PathConstant.FIND)
    public RestResponse getRicerca(@PathVariable("id") int id){
        try {
            RicercheDto r = ricercheService.getRicerca(id);
            LOGGER.info(ResponseConstant.RICERCHE_FOUND + " dati utente: " + r);
            return new RestResponse(200, ResponseConstant.RICERCHE_FOUND, r);
        } catch (ObjectNotFoundExeption e) {
            LOGGER.error(ResponseConstant.RICERCHE_NOT_FOUND + " exception: " + e.getMessage());
            return new RestResponse(e.getCode(), e.getDescription(), null);
        } catch (Exception e) {
            LOGGER.error(ResponseConstant.INTERNAL_SERVER_ERROR + " exception: " + e.getMessage());
            return new RestResponse(500, ResponseConstant.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping(PathConstant.GETALL)
    public RestResponseCollection getAll() {
        try {
            List<RicercheDto> list = ricercheService.getAllRicerche();
            LOGGER.info(ResponseConstant.RICERCHE_LIST_FOUND);
            return new RestResponseCollection(200, ResponseConstant.RICERCHE_LIST_FOUND, list);
        } catch (Exception e) {
            LOGGER.error(ResponseConstant.INTERNAL_SERVER_ERROR + " exception: " + e.getMessage());
            return new RestResponseCollection(500, ResponseConstant.INTERNAL_SERVER_ERROR, null);
        }
    }
}
