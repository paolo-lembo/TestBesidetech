package com.ricercacanali.webapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricercacanali.webapp.exeptions.ArgumentNotValidException;
import com.ricercacanali.webapp.exeptions.ObjectNotFoundExeption;
import com.ricercacanali.webapp.modelDto.RicercheDto;
import com.ricercacanali.webapp.modelDto.UtenteDto;
import com.ricercacanali.webapp.service.ricerche.RicercheService;
import com.ricercacanali.webapp.util.RestResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(RicercheController.class)
public class RicercheControllerTest {

    @MockBean
    private RicercheService ricercheService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAll_giveFullList_thenOK() throws Exception {
        String uri = "/api/ricerche/getAll";
        List<RicercheDto> list = new ArrayList<>();
        RicercheDto ricercheDto = new RicercheDto();
        ricercheDto.setId(1);
        ricercheDto.setDataRicerca(new Date());
        ricercheDto.setUtente(1);
        list.add(ricercheDto);

        when(ricercheService.getAllRicerche()).thenReturn(list);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).
                accept(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(list))).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void getAll_giveEmptyList_then500() throws Exception {
        String uri = "/api/ricerche/getAll";

        when(ricercheService.getAllRicerche()).thenThrow(NullPointerException.class);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).
                accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(500, response.getStatus());
    }

    @Test
    public void saveRicerche_givenValidRicerca_isOk() throws Exception {
        String uri = "/api/ricerche/save";
        RicercheDto ricercheDto = new RicercheDto();

        doNothing().when(ricercheService).saveRicerca(any(RicercheDto.class));
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).
                contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(ricercheDto))).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void saveRicerche_givenNotValidRicerca_that400() throws Exception {
        String uri = "/api/ricerche/save";
        RicercheDto ricercheDto = new RicercheDto();

        doThrow(new ArgumentNotValidException(400, null)).when(ricercheService).saveRicerca(any(RicercheDto.class));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).
                contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(ricercheDto))).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(400, response.getStatus());
    }

    @Test
    public void saveRicerche_givenNotValidRicerca_that404() throws Exception {
        String uri = "/api/ricerche/save";
        RicercheDto ricercheDto = new RicercheDto();

        doThrow(new ObjectNotFoundExeption(404, null)).when(ricercheService).saveRicerca(any(RicercheDto.class));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).
                contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(ricercheDto))).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(404, response.getStatus());
    }

    @Test
    public void saveRicerche_givenNotValidRicerca_that500() throws Exception {
        String uri = "/api/ricerche/save";
        RicercheDto ricercheDto = new RicercheDto();

        doThrow(RuntimeException.class).when(ricercheService).saveRicerca(any(RicercheDto.class));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).
                contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(ricercheDto))).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(500, response.getStatus());
    }

    @Test
    public void deleteRicerche_givenValidId_thenOk() throws Exception {
        String uri = "/api/ricerche/delete/{id}";

        doNothing().when(ricercheService).deleteRicerca(anyInt());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri, 1).
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void deleteRicerche_givenNotValidId_then404() throws Exception {
        String uri = "/api/ricerche/delete/{id}";

        doThrow(new ObjectNotFoundExeption(404, null)).when(ricercheService).deleteRicerca(anyInt());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri, 1).
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(404, response.getStatus());
    }

    @Test
    public void deleteRicerche_givenNotValidId_then500() throws Exception {
        String uri = "/api/ricerche/delete/{id}";

        doThrow(RuntimeException.class).when(ricercheService).deleteRicerca(anyInt());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri, 1).
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(500, response.getStatus());
    }

    @Test
    public void getRicerche_givenValidId_then200() throws Exception {
        String uri = "/api/ricerche/find/{id}";

        when(ricercheService.getRicerca(anyInt())).thenReturn(any(RicercheDto.class));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri, 1).
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();


        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void getRicerche_givenNotValidId_then404() throws Exception {
        String uri = "/api/ricerche/find/{id}";

        doThrow(new ObjectNotFoundExeption(404, null)).when(ricercheService).getRicerca(anyInt());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri, 1).
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(404, response.getStatus());
    }

    @Test
    public void getRicerche_givenNotValidId_then500() throws Exception {
        String uri = "/api/ricerche/find/{id}";

        doThrow(RuntimeException.class).when(ricercheService).getRicerca(anyInt());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri, 1).
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(500, response.getStatus());
    }

    @Test
    public void updateRicerche_givenValidRicerche_isOk() throws Exception {
        String uri = "/api/ricerche/update/";
        RicercheDto ricercheDto = new RicercheDto();

        doNothing().when(ricercheService).updateRicerca(any(RicercheDto.class));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).
                contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(ricercheDto))).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void updateRicerche_givenValidRicerche_than404() throws Exception {
        String uri = "/api/ricerche/update/";
        RicercheDto ricercheDto = new RicercheDto();

        doThrow(new ObjectNotFoundExeption(404, null)).when(ricercheService).updateRicerca(ricercheDto);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).
                contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(ricercheDto))).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(404, response.getStatus());
    }

    @Test
    public void updateRicerche_givenValidRicerche_than500() throws Exception {
        String uri = "/api/ricerche/update/";
        RicercheDto ricercheDto = new RicercheDto();

        doThrow(RuntimeException.class).when(ricercheService).updateRicerca(ricercheDto);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).
                contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(ricercheDto))).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(500, response.getStatus());
    }

}
