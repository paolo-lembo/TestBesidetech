package com.ricercacanali.webapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricercacanali.webapp.exeptions.ArgumentNotValidException;
import com.ricercacanali.webapp.exeptions.ObjectNotFoundExeption;
import com.ricercacanali.webapp.modelDto.CanaliDto;
import com.ricercacanali.webapp.modelDto.UtenteDto;
import com.ricercacanali.webapp.service.canali.CanaliService;
import com.ricercacanali.webapp.util.RestResponse;
import org.checkerframework.checker.units.qual.C;
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

@WebMvcTest(CanaliController.class)
public class CanaliControllerTest {
    @MockBean
    private CanaliService canaliService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAll_thenOK() throws Exception {
        String uri = "/api/canali/getAll";
        List<CanaliDto> list = new ArrayList<>();

        when(canaliService.getAllCanali()).thenReturn(list);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).
                accept(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(list))).andReturn();


        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void getAll_then500() throws Exception {
        String uri = "/api/canali/getAll";

        when(canaliService.getAllCanali()).thenThrow(NullPointerException.class);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).
                accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(500, response.getStatus());
    }

    @Test
    public void saveCanali_isOk() throws Exception {
        String uri = "/api/canali/save";
        CanaliDto canaliDto = new CanaliDto();

        doNothing().when(canaliService).saveCanali(any(CanaliDto.class));
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).
                contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(canaliDto))).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void saveCanali_then400() throws Exception {
        String uri = "/api/canali/save";
        CanaliDto canaliDto = new CanaliDto();

        doThrow(new ArgumentNotValidException(400, null)).when(canaliService).saveCanali(any(CanaliDto.class));
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).
                contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(canaliDto))).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(400, response.getStatus());
    }

    @Test
    public void saveCanali_then500() throws Exception {
        String uri = "/api/canali/save";
        CanaliDto canaliDto = new CanaliDto();

        doThrow(RuntimeException.class).when(canaliService).saveCanali(any(CanaliDto.class));
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).
                contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(canaliDto))).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(500, response.getStatus());
    }

    @Test
    public void deleteCanale_thenOk() throws Exception {
        String uri = "/api/canali/delete/{id}";

        doNothing().when(canaliService).deleteCanali(anyInt());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri, 1).
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void deleteCanale_then404() throws Exception {
        String uri = "/api/canali/delete/{id}";

        doThrow(new ObjectNotFoundExeption(404, null)).when(canaliService).deleteCanali(anyInt());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri, 1).
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(404, response.getStatus());
    }

    @Test
    public void deleteCanale_then500() throws Exception {
        String uri = "/api/canali/delete/{id}";

        doThrow(RuntimeException.class).when(canaliService).deleteCanali(anyInt());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri, 1).
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(500, response.getStatus());
    }

    @Test
    public void getCanale_then200() throws Exception {
        String uri = "/api/canali/find/{id}";

        when(canaliService.getCanali(anyInt())).thenReturn(any(CanaliDto.class));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri, 1).
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();


        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void getCanale_then404() throws Exception {
        String uri = "/api/canali/find/{id}";

        doThrow(new ObjectNotFoundExeption(404, null)).when(canaliService).getCanali(anyInt());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri, 1).
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(404, response.getStatus());
    }

    @Test
    public void getCanale_then500() throws Exception {
        String uri = "/api/canali/find/{id}";

        doThrow(RuntimeException.class).when(canaliService).getCanali(anyInt());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri, 1).
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(500, response.getStatus());
    }

    @Test
    public void updateCanale_isOk() throws Exception {
        String uri = "/api/canali/update/";
        CanaliDto canaliDto = new CanaliDto();

        doNothing().when(canaliService).updateCanali(any(CanaliDto.class));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).
                contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(canaliDto))).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void updateCanale_then500() throws Exception {
        String uri = "/api/canali/update/";
        CanaliDto canaliDto = new CanaliDto();

        doThrow(RuntimeException.class).when(canaliService).updateCanali(any(CanaliDto.class));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).
                contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(canaliDto))).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(500, response.getStatus());
    }

    @Test
    public void updateCanale_404() throws Exception {
        String uri = "/api/canali/update/";
        CanaliDto canaliDto = new CanaliDto();

        doThrow(new ObjectNotFoundExeption(404, null)).when(canaliService).updateCanali(canaliDto);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).
                contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(canaliDto))).andReturn();

        RestResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);
        assertEquals(404, response.getStatus());
    }

}
