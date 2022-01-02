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
public class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext web;

    @BeforeEach
    private void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(web).build();
    }

    @Test
    public void testListOfMedicalRecords() throws Exception {
        mockMvc.perform(get("/medicalRecord"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindMedicalRecordByFirstNameAndLastName() throws Exception {
        mockMvc.perform(get("/medicalRecord/John/Boyd"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateMedicalRecord() throws Exception {
        mockMvc.perform(post("/medicalRecord").contentType(APPLICATION_JSON)
                        .content("{ \"firstName\":\"Cyril\", \"lastName\":\"Cobb\", \"birthdate\":\"27/11/1985\", \"medications\":[\"mentalus:350mg\"], \"allergies\":[\"victis\"] }"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateMedicalRecord() throws Exception {
        mockMvc.perform(put("/medicalRecord/Cyril/Cobb").contentType(APPLICATION_JSON)
                        .content("{ \"firstName\":\"Cyril\", \"lastName\":\"Cobb\", \"birthdate\":\"27/11/1985\", \"medications\":[\"aznol:790g\", \"hydrapermazol:550mg\"], \"allergies\":[\"ninja\"] }")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteMedicalRecord() throws Exception {
        mockMvc.perform(delete("/medicalRecord/Cyril/Cobb"))
                .andExpect(status().isOk());
    }
}
