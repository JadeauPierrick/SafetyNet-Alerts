package com.safetynet.safetynetalerts.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class DTOControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext web;

    @BeforeEach
    private void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(web).build();
    }

    @Test
    public void testPersonCoveredByItsFirestationNumberDTOList() throws Exception {
        mockMvc.perform(get("/firestation").contentType(APPLICATION_JSON)
                        .param("stationNumber", "2"))
                .andExpect(status().isOk());
    }

    @Test
    public void testChildAlert() throws Exception {
        mockMvc.perform(get("/childAlert").contentType(APPLICATION_JSON)
                .param("address", "1509 Culver St"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFireAlert() throws Exception {
        mockMvc.perform(get("/fire").contentType(APPLICATION_JSON)
                .param("address", "1509 Culver St"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFloodAlert() throws Exception {
        mockMvc.perform(get("/flood/stations").contentType(APPLICATION_JSON)
                .param("stations", "1,2"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPersonInfoAlert() throws Exception {
        mockMvc.perform(get("/personInfo").contentType(APPLICATION_JSON)
                .param("firstName", "John")
                .param("lastName", "Boyd"))
                .andExpect(status().isOk());
    }
}
