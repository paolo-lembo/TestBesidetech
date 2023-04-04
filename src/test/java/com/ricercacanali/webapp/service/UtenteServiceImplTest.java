
package com.ricercacanali.webapp.service;

import com.ricercacanali.webapp.exeptions.ArgumentNotValidException;
import com.ricercacanali.webapp.exeptions.ObjectNotFoundExeption;
import com.ricercacanali.webapp.model.Canali;
import com.ricercacanali.webapp.model.Utente;
import com.ricercacanali.webapp.modelDto.CanaliDto;
import com.ricercacanali.webapp.modelDto.UtenteDto;
import com.ricercacanali.webapp.repository.UtenteRepo;
import com.ricercacanali.webapp.service.utente.UtenteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UtenteServiceImplTest {

    @Mock
    UtenteRepo  utenteRepo;

    @InjectMocks
    UtenteServiceImpl utenteService;

    @Test
    public void testGetAllUtente()  {
        List<Utente> list = new ArrayList<>();
        Utente u = new Utente();
        u.setId(1);
        u.setNome("paolo");
        u.setCognome("lembo");
        u.setEmail("paolo@paolo");
        list.add(u);

        when(utenteRepo.findAll()).thenReturn(list);
        List<UtenteDto> listDto = utenteService.getAllUtente();
        assertEquals(1, listDto.get(0).getId());
    }

    @Test
    public void testDeleteUser() throws ObjectNotFoundExeption {
        willDoNothing().given(utenteRepo).deleteById(1);
        given(utenteRepo.findById(1)).willReturn(Optional.of(new Utente()));
        utenteService.deleteUtente(1);
        verify(utenteRepo, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteUser_404() {
        assertThrows(ObjectNotFoundExeption.class, () -> {
            utenteService.deleteUtente(1);
        });
        verify(utenteRepo, never()).deleteById(1);
    }

    @Test
    public void testGetUtente() throws ObjectNotFoundExeption {
        Utente u = new Utente();
        u.setId(1);
        u.setNome("paolo");
        u.setCognome("lembo");
        u.setEmail("paolo@");

        when(utenteRepo.findById(anyInt())).thenReturn(Optional.of(u));
        UtenteDto utenteDto1 =utenteService.getUtente(1);
        UtenteDto utenteDto = new UtenteDto();
        utenteDto.setId(1);
        utenteDto.setNome("paolo");
        utenteDto.setCognome("lembo");
        utenteDto.setEmail("paolo@");

        assertEquals(utenteDto.getId(), utenteDto1.getId());
    }

    @Test
    public void testSaveUtente() throws ArgumentNotValidException {
        UtenteDto utenteDto = new UtenteDto();
        utenteDto.setId(1);
        utenteDto.setNome("paolo");
        utenteDto.setCognome("lembo");
        utenteDto.setEmail("paolo@");

        when(utenteRepo.save(any(Utente.class))).thenReturn(any(Utente.class));
        utenteService.saveUtente(utenteDto);

        assertNotNull(Utente.class);
    }

    @Test
    public void updateUtente_404(){
        Utente utente = new Utente();
        utente.setId(1);
        utente.setNome("alph");
        utente.setCognome("davies");
        utente.setEmail("alph@nso");

        assertThrows(ObjectNotFoundExeption.class, () -> {
            utenteService.updateUtente(new UtenteDto());
        });
        verify(utenteRepo, never()).save(utente);
    }

    @Test
    public void testUpdateUtente_than200() throws ObjectNotFoundExeption {
        Utente utente = new Utente();
        utente.setId(1);
        utente.setNome("alph");
        utente.setCognome("davies");
        utente.setEmail("alph@nso");

        UtenteDto utenteDto = new UtenteDto();
        utenteDto.setId(1);
        utenteDto.setNome("alph");
        utenteDto.setCognome("davies");
        utenteDto.setEmail("alph@nso");

        given(utenteRepo.save(utente)).willReturn(utente);
        given(utenteRepo.findById(1)).willReturn(Optional.of(utente));

        utenteService.updateUtente(utenteDto);
        verify(utenteRepo, times(1)).save(utente);
    }

}