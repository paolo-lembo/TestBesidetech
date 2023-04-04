package com.ricercacanali.webapp.service;

import com.ricercacanali.webapp.exeptions.ArgumentNotValidException;
import com.ricercacanali.webapp.exeptions.ObjectNotFoundExeption;
import com.ricercacanali.webapp.model.Canali;
import com.ricercacanali.webapp.model.Ricerche;
import com.ricercacanali.webapp.model.Utente;
import com.ricercacanali.webapp.modelDto.CanaliDto;
import com.ricercacanali.webapp.modelDto.RicercheDto;
import com.ricercacanali.webapp.repository.CanaliRepo;
import com.ricercacanali.webapp.repository.RicercheRepo;
import com.ricercacanali.webapp.repository.UtenteRepo;
import com.ricercacanali.webapp.service.ricerche.RicercheServiceImpl;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RicercheServiceImplTest {

    @Mock
    RicercheRepo ricercheRepo;

    @Mock
    UtenteRepo utenteRepo;

    @Mock
    CanaliRepo canaliRepo;

    @InjectMocks
    RicercheServiceImpl ricercheService;



    @Test
    public void testSaveRicerca() throws ArgumentNotValidException, ObjectNotFoundExeption {
        Utente utente = new Utente();
        utente.setId(1);

        Ricerche r = new Ricerche();
        r.setId(1);
        r.setDataRicerca(new Date());
        r.setUtente(utente);

        Set<Integer> idCanali = new HashSet<>();
        idCanali.add(1);

        RicercheDto ricercheDto = new RicercheDto();
        ricercheDto.setId(1);
        ricercheDto.setUtente(1);
        ricercheDto.setIdCanali(idCanali);

        Canali c = new Canali();
        c.setId(1);
        c.setDescrizione("a");

        given(ricercheRepo.findById(1)).willReturn(Optional.of(r));
        given(canaliRepo.findById(1)).willReturn(Optional.of(c));
        given(utenteRepo.findById(1)).willReturn(Optional.of(utente));
        given(ricercheRepo.save(r)).willReturn(r);

        ricercheService.saveRicerca(ricercheDto);
        verify(ricercheRepo, times(1)).save(r);
    }

    @Test
    public void testDeleteRicerca() throws ObjectNotFoundExeption {
        willDoNothing().given(ricercheRepo).deleteById(1);
        given(ricercheRepo.findById(1)).willReturn(Optional.of(new Ricerche()));
        ricercheService.deleteRicerca(1);
        verify(ricercheRepo, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteRicerca_404(){
        assertThrows(ObjectNotFoundExeption.class, () -> {
            ricercheService.deleteRicerca(1);
        });
        verify(ricercheRepo, never()).deleteById(1);
    }

    @Test
    public void testGetRicerca() throws ObjectNotFoundExeption {
        Ricerche r = new Ricerche();
        Utente utente = new Utente();
        r.setId(1);
        r.setDataRicerca(new Date());
        r.setUtente(utente);

        when(ricercheRepo.findById(anyInt())).thenReturn(Optional.of(r));
        RicercheDto ricercheDto1 = ricercheService.getRicerca(1);
        RicercheDto ricercheDto = new RicercheDto();
        ricercheDto.setId(1);
        ricercheDto.setDataRicerca(new Date());
        ricercheDto.setUtente(utente.getId());

        assertEquals(ricercheDto1.getId(), ricercheDto.getId());
    }

    @Test
    public void testGetAllRicerche() throws ObjectNotFoundExeption {
        List<Ricerche> list = new ArrayList<>();
        Ricerche r = new Ricerche();
        Utente utente = new Utente();
        r.setId(1);
        r.setDataRicerca(new Date());
        r.setUtente(utente);
        list.add(r);

        when(ricercheRepo.findAll()).thenReturn(list);
        List<RicercheDto> listDto = ricercheService.getAllRicerche();
        assertEquals(1, listDto.get(0).getId());
    }

    @Test
    public void testUpdateRicerca_than404() {
        Ricerche r = new Ricerche();
        Utente utente = new Utente();
        r.setId(1);
        r.setDataRicerca(new Date());
        r.setUtente(utente);

        assertThrows(ObjectNotFoundExeption.class, () -> {
            ricercheService.updateRicerca(new RicercheDto());
        });
        verify(ricercheRepo, never()).save(r);
    }

    @Test
    public void testUpdateCanale_than200() throws ObjectNotFoundExeption {
        Ricerche r = new Ricerche();
        Utente utente = new Utente();
        r.setId(1);
        r.setDataRicerca(new Date());
        utente.setId(1);
        r.setUtente(utente);

        RicercheDto ricercheDto = new RicercheDto();
        ricercheDto.setId(1);
        ricercheDto.setDataRicerca(new Date());
        ricercheDto.setUtente(1);
        Set<Integer> idCanali = new HashSet<>();
        idCanali.add(1);
        ricercheDto.setIdCanali(idCanali);

        Canali c = new Canali();
        c.setId(1);
        c.setDescrizione("a");

        given(canaliRepo.findById(1)).willReturn(Optional.of(c));
        given(ricercheRepo.save(r)).willReturn(r);
        given(ricercheRepo.findById(1)).willReturn(Optional.of(r));
        given(utenteRepo.findById(1)).willReturn(Optional.of(utente));

        ricercheService.updateRicerca(ricercheDto);
        verify(ricercheRepo, times(1)).save(r);
    }


}
