package com.ricercacanali.webapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricercacanali.webapp.exeptions.ArgumentNotValidException;
import com.ricercacanali.webapp.exeptions.ObjectNotFoundExeption;
import com.ricercacanali.webapp.modelDto.UtenteDto;
import com.ricercacanali.webapp.service.utente.UtenteService;
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
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(UtenteController.class)
public class UtenteControllerTest{

    @MockBean
    private UtenteService utenteService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper  objectMapper;

    @Test
    public void getAll_giveFullList_thenOK() throws Exception {
        String uri = "/api/user/getAll";
        List<UtenteDto> list = new ArrayList<>();

        when(utenteService.getAllUtente()).thenReturn(list);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).
                accept(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(list))).andReturn();


        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void getAll_giveEmptyList_then500() throws Exception {
        String uri = "/api/user/getAll";

        when(utenteService.getAllUtente()).thenThrow(NullPointerException.class);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).
                accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();


        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(500, response.getStatus());
    }

    @Test
    public void saveUtente_givenValidUser_isOk() throws Exception {
        String uri = "/api/user/save";
        UtenteDto utenteDTO = new UtenteDto();

        doNothing().when(utenteService).saveUtente(any(UtenteDto.class));
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).
                contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(utenteDTO))).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void saveUtente_givenUserWithId_than400() throws Exception {
        String uri = "/api/user/save";
        UtenteDto utenteDTO = new UtenteDto();

        doThrow(new ArgumentNotValidException(400, null)).when(utenteService).saveUtente(any(UtenteDto.class));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).
                contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(utenteDTO))).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(400, response.getStatus());
    }

    @Test
    public void saveUtente_givenEmptyUser_than500() throws Exception {
        String uri = "/api/user/save";
        UtenteDto u = new UtenteDto();

        doThrow(RuntimeException.class).when(utenteService).saveUtente(any(UtenteDto.class));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).
                contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(u))).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(500, response.getStatus());
    }

    @Test
    public void deleteUtente_givenValidId_thenOk() throws Exception {
        String uri = "/api/user/delete/{id}";

        doNothing().when(utenteService).deleteUtente(anyInt());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri, 1).
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void deleteUtente_givenNotValidId_then404() throws Exception {
        String uri = "/api/user/delete/{id}";

        doThrow(new ObjectNotFoundExeption(404, null)).when(utenteService).deleteUtente(anyInt());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri, 1).
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(404, response.getStatus());
    }

    @Test
    public void deleteUtente_givenNullId_then500() throws Exception {
        String uri = "/api/user/delete/{id}";

        doThrow(RuntimeException.class).when(utenteService).deleteUtente(anyInt());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri, 1).
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(500, response.getStatus());
    }

    @Test
    public void getUtente_givenValidId_then200() throws Exception {
        String uri = "/api/user/find/{id}";

        when(utenteService.getUtente(anyInt())).thenReturn(any(UtenteDto.class));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri, 1).
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();


        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void getUtente_givenNotValidId_then404() throws Exception {
        String uri = "/api/user/find/{id}";

        doThrow(new ObjectNotFoundExeption(404, null)).when(utenteService).getUtente(anyInt());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri, 1).
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();


        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(404, response.getStatus());
    }

    @Test
    public void getUtente_givenNullId_then500() throws Exception {
        String uri = "/api/user/find/{id}";

        doThrow(RuntimeException.class).when(utenteService).getUtente(anyInt());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri, 1).
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();


        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(500, response.getStatus());
    }

    @Test
    public void updateUtente_givenValidUser_isOk() throws Exception {
        String uri = "/api/user/update/";
        UtenteDto utenteDTO = new UtenteDto();

        doNothing().when(utenteService).updateUtente(any(UtenteDto.class));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).
                contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(utenteDTO))).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void updateUtente_givenValidUser_than404() throws Exception {
        String uri = "/api/user/update/";
        UtenteDto utenteDTO = new UtenteDto();

        doThrow(new ObjectNotFoundExeption(404, null)).when(utenteService).updateUtente(utenteDTO);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).
                contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(utenteDTO))).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(404, response.getStatus());
    }

    @Test
    public void updateUtente_givenValidUser_than500() throws Exception {
        String uri = "/api/user/update/";
        UtenteDto utenteDTO = new UtenteDto();

        doThrow(RuntimeException.class).when(utenteService).updateUtente(utenteDTO);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).
                contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(utenteDTO))).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(500, response.getStatus());
    }

}
