package com.ricercacanali.webapp.controller;

import com.ricercacanali.webapp.exeptions.ArgumentNotValidException;
import com.ricercacanali.webapp.exeptions.ObjectNotFoundExeption;
import com.ricercacanali.webapp.modelDto.UtenteDto;
import com.ricercacanali.webapp.service.utente.UtenteService;
import com.ricercacanali.webapp.util.PathConstant;
import com.ricercacanali.webapp.util.ResponseConstant;
import com.ricercacanali.webapp.util.RestResponse;
import com.ricercacanali.webapp.util.RestResponseCollection;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PathConstant.ROOT+PathConstant.USER)
public class UtenteController {

    private static final Logger LOGGER = Logger.getLogger(UtenteController.class);

    @Autowired
    private UtenteService utenteService;

    @PostMapping(PathConstant.SAVE)
    public RestResponse<UtenteDto> saveUtente(@RequestBody UtenteDto u){
        try {
            utenteService.saveUtente(u);
            LOGGER.info(ResponseConstant.USER_SAVED + " con id: " + u.getId());
            return new RestResponse(200, ResponseConstant.USER_SAVED, null);
        } catch (ArgumentNotValidException e) {
            LOGGER.error(ResponseConstant.USER_NOT_SAVED + " exception: " + e.getMessage());
            return new RestResponse(e.getCode(), e.getDescription(), null);
        } catch (Exception e) {
            LOGGER.error(ResponseConstant.INTERNAL_SERVER_ERROR + " exception: " + e.getMessage());
            return new RestResponse(500, ResponseConstant.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping(PathConstant.UPDATE)
    public RestResponse<UtenteDto> updateUtente(@RequestBody UtenteDto u){
        try {
            utenteService.updateUtente(u);
            LOGGER.info(ResponseConstant.USER_UPDATED + " dati aggiornati utente: " + u);
            return new RestResponse(200, ResponseConstant.USER_UPDATED, null);
        }  catch (ObjectNotFoundExeption e) {
            LOGGER.error(ResponseConstant.USER_NOT_FOUND + " exception: " + e.getMessage());
            return new RestResponse(e.getCode(), e.getDescription(), null);
        }catch (Exception e) {
            LOGGER.error(ResponseConstant.INTERNAL_SERVER_ERROR + " exception: " + e.getMessage());
            return new RestResponse(500, ResponseConstant.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping(PathConstant.GETALL)
    public RestResponseCollection getAll(){
        try {
            List<UtenteDto> listUtente = utenteService.getAllUtente();
            LOGGER.info(ResponseConstant.USER_LIST_FOUND);
            return new RestResponseCollection(200, ResponseConstant.USER_LIST_FOUND, listUtente);
        } catch (Exception e) {
            LOGGER.error(ResponseConstant.INTERNAL_SERVER_ERROR + " exception: " + e.getMessage());
            return new RestResponseCollection(500, ResponseConstant.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping(PathConstant.DELETE)
    public RestResponse deleteUser(@PathVariable("id") int id){
        try {
            utenteService.deleteUtente(id);
            LOGGER.info(ResponseConstant.USER_DELETED + " id utente eliminato: " + id);
            return new RestResponse(200, ResponseConstant.USER_DELETED, null);
        }  catch (ObjectNotFoundExeption e) {
            LOGGER.error(ResponseConstant.USER_NOT_FOUND + " exception: " + e.getMessage());
            return new RestResponse(e.getCode(), e.getDescription(), null);
        } catch (Exception e) {
            LOGGER.error(ResponseConstant.INTERNAL_SERVER_ERROR + " exception: " + e.getMessage());
            return new RestResponse(500, ResponseConstant.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping(PathConstant.FIND)
    public RestResponse getUtente(@PathVariable("id") int id) {
        try {
            UtenteDto u = utenteService.getUtente(id);
            LOGGER.info(ResponseConstant.USER_FOUND + " dati utente: " + u);
            return new RestResponse(200, ResponseConstant.USER_FOUND, null);
        }  catch (ObjectNotFoundExeption e) {
            LOGGER.error(ResponseConstant.USER_NOT_FOUND + " exception: " + e.getMessage());
            return new RestResponse(e.getCode(), e.getDescription(), null);
        } catch (Exception e) {
            LOGGER.error(ResponseConstant.INTERNAL_SERVER_ERROR + " exception: " + e.getMessage());
            return new RestResponse(500, ResponseConstant.INTERNAL_SERVER_ERROR, null);
        }
    }
}
