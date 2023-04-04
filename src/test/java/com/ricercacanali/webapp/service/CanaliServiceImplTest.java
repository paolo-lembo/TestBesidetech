package com.ricercacanali.webapp.service;

import com.ricercacanali.webapp.exeptions.ArgumentNotValidException;
import com.ricercacanali.webapp.exeptions.ObjectNotFoundExeption;
import com.ricercacanali.webapp.model.Canali;
import com.ricercacanali.webapp.model.Ricerche;
import com.ricercacanali.webapp.modelDto.CanaliDto;
import com.ricercacanali.webapp.repository.CanaliRepo;
import com.ricercacanali.webapp.service.canali.CanaliServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CanaliServiceImplTest {

    @Mock
    CanaliRepo canaliRepo;

    @InjectMocks
    CanaliServiceImpl canaliService;




    @Test
    public void testSaveCanale() throws ArgumentNotValidException {
        CanaliDto c = new CanaliDto();
        c.setId(1);
        c.setDescrizione("a");

        when(canaliRepo.save(any(Canali.class))).thenReturn(any(Canali.class));
        canaliService.saveCanali(c);

        verify(canaliRepo).save(any());
        assertNotNull(Ricerche.class);
    }

    @Test
    public void testDeleteCanali_404(){
        assertThrows(ObjectNotFoundExeption.class, () -> {
                canaliService.deleteCanali(1);
        });
        verify(canaliRepo, never()).deleteById(1);
    }

    @Test
    public void testDeleteCanali_ok() throws ObjectNotFoundExeption {
        willDoNothing().given(canaliRepo).deleteById(1);
        given(canaliRepo.findById(1)).willReturn(Optional.of(new Canali()));
        canaliService.deleteCanali(1);
        verify(canaliRepo, times(1)).deleteById(1);
    }

    @Test
    public void testGetCanali() throws ObjectNotFoundExeption {
        Canali c = new Canali();
        c.setId(1);
        c.setDescrizione("a");

        when(canaliRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(c));
        CanaliDto canaliDto = canaliService.getCanali(1);
        CanaliDto canaliDto1 = new CanaliDto();
        canaliDto1.setId(1);
        canaliDto1.setDescrizione("a");

        assertEquals(canaliDto.getId(), canaliDto1.getId());
    }

    @Test
    public void testGetAllCanali() throws ObjectNotFoundExeption {
        List<Canali> list = new ArrayList<>();
        Canali c = new Canali();
        c.setId(1);
        c.setDescrizione("a");
        list.add(c);

        when(canaliRepo.findAll()).thenReturn(list);
        List<CanaliDto> listDto = canaliService.getAllCanali();
        assertEquals(1, listDto.get(0).getId());
    }

    @Test
    public void testUpdateCanale_than404(){
        Canali c = new Canali();
        c.setId(1);
        c.setDescrizione("a");

        assertThrows(ObjectNotFoundExeption.class, () -> {
            canaliService.updateCanali(new CanaliDto());
        });
        verify(canaliRepo, never()).save(c);
    }

    @Test
    public void testUpdateCanale_than200() throws ObjectNotFoundExeption{
        Canali c = new Canali();
        c.setId(1);
        c.setDescrizione("a");

        CanaliDto c1 = new CanaliDto();
        c1.setId(1);
        c1.setDescrizione("a");

        given(canaliRepo.save(c)).willReturn(c);
        given(canaliRepo.findById(1)).willReturn(Optional.of(c));

        canaliService.updateCanali(c1);
        verify(canaliRepo, times(1)).save(c);
    }
}
