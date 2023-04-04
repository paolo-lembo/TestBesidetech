package com.ricercacanali.webapp.util;

import com.ricercacanali.webapp.controller.RicercheController;
import com.ricercacanali.webapp.model.Canali;
import com.ricercacanali.webapp.model.Ricerche;
import com.ricercacanali.webapp.model.Utente;
import com.ricercacanali.webapp.modelDto.CanaliDto;
import com.ricercacanali.webapp.modelDto.RicercheDto;
import com.ricercacanali.webapp.modelDto.UtenteDto;
import org.apache.log4j.Logger;

import java.util.List;

public class Converter {

    private static final Logger LOGGER = Logger.getLogger(RicercheController.class);

    //Converter User

    public static void convertUtenteToUtenteDto (Utente origine, UtenteDto destinazione) {
        LOGGER.info("converto utente in utenteDto, dati utente: " + origine);
        destinazione.setNome(origine.getNome());
        destinazione.setCognome(origine.getCognome());
        destinazione.setEmail(origine.getEmail());
        destinazione.setId(origine.getId());
        LOGGER.debug("la conversione ha prodotto questo utente: " + destinazione);
    }

    public static void convertUtenteDtoToUtente (UtenteDto origine, Utente destinazione) {
        LOGGER.info("converto utenteDto in utente, dati utenteDto:" + origine);
        destinazione.setNome(origine.getNome());
        destinazione.setCognome(origine.getCognome());
        destinazione.setEmail(origine.getEmail());
        destinazione.setId(origine.getId());
        LOGGER.info("la conversione ha prodotto questo utenteDto: " + destinazione);
    }

    public static void convertUtenteListToDto (List<Utente> origine, List<UtenteDto> destinazione) {
        for (Utente u : origine) {
            UtenteDto utenteDto = new UtenteDto();
            convertUtenteToUtenteDto(u, utenteDto);
            destinazione.add(utenteDto);
        }
    }

    //Converter Ricerche

    public static void convertRicercheToRicercheDto (Ricerche origine, RicercheDto destinazione ) {
        LOGGER.info("converto Ricerca in RicercaDto, dati Ricerca: " + origine);
        destinazione.setId(origine.getId());
        destinazione.setDataRicerca(origine.getDataRicerca());
        for (Canali canali : origine.getCanali())
            destinazione.getIdCanali().add(canali.getId());
        destinazione.setUtente(origine.getUtente().getId());
        LOGGER.info("la conversione ha prodotto questa RicercaDto: " + destinazione);
    }

    public static void convertRicercheDtoToRicerche (RicercheDto origine, Ricerche destinazione ) {
        LOGGER.info("converto RicercaDto in Ricerca, dati Ricerca: " + origine);
        destinazione.setId(origine.getId());
        destinazione.setDataRicerca(origine.getDataRicerca());
        Utente utente = new Utente();
        utente.setId(origine.getUtente());
        destinazione.setUtente(utente);
        LOGGER.info("la conversione ha prodotto questa Ricerca: " + destinazione);
    }

    public static void convertRicercheListToDto (List<Ricerche> origine, List<RicercheDto> destinazione) {
        for (Ricerche r : origine) {
            RicercheDto ricercheDto = new RicercheDto();
            convertRicercheToRicercheDto(r, ricercheDto);
            destinazione.add(ricercheDto);
        }
    }

    //Converter Canali

    public static void convertCanaliToCanaliDto (Canali origine, CanaliDto destinazione ) {
        LOGGER.info("converto Canale in CanaleDto, dati Canale: " + origine);
        destinazione.setId(origine.getId());
        destinazione.setDescrizione(origine.getDescrizione());
        for (Ricerche ricerche : origine.getRicerche())
            destinazione.getIdRicerche().add(ricerche.getId());
        LOGGER.info("la conversione ha prodotto questo CanaleDto: " + destinazione);
    }

    public static void convertCanaliDtoToCanali (CanaliDto origine, Canali destinazione ) {
        LOGGER.info("converto CanaleDto in Canale, dati Canale: " + origine);
        destinazione.setId(origine.getId());
        destinazione.setDescrizione(origine.getDescrizione());
        LOGGER.info("la conversione ha prodotto questo Canale: " + destinazione);
    }

    public static void convertCanaliListToDto (List<Canali> origine, List<CanaliDto> destinazione) {
        for (Canali c : origine) {
            CanaliDto canaliDto = new CanaliDto();
            convertCanaliToCanaliDto(c, canaliDto);
            destinazione.add(canaliDto);
        }
    }
}
