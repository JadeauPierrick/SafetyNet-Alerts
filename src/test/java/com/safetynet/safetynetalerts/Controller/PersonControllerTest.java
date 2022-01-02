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
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext web;

    @BeforeEach
    private void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(web).build();
    }


    @Test
    public void testListOfPersons() throws Exception {
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindPersonByFirstNameAndLastName() throws Exception {
        mockMvc.perform(get("/person/John/Boyd"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindPersonWithUnknownFirstNameAndLastName() throws Exception {
        mockMvc.perform(get("/person/Jacob/Dale"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreatePerson() throws Exception {
        mockMvc.perform(post("/person").contentType(APPLICATION_JSON)
                    .content("{ \"firstName\":\"Cyril\", \"lastName\":\"Cobb\", \"address\":\"87 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"891-874-7812\", \"email\":\"cyco@email.com\" }"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreatePersonWithError() throws Exception {
        mockMvc.perform(post("/person").contentType(APPLICATION_JSON)
                .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdatePerson() throws Exception {
        mockMvc.perform(put("/person/Cyril/Cobb").contentType(APPLICATION_JSON)
                    .content("{ \"firstName\":\"Cyril\", \"lastName\":\"Cobb\", \"address\":\"15 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"cc@email.com\" }")
                    .accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdatePersonWithError() throws Exception {
        mockMvc.perform(put("/person/Jacob/Dale").contentType(APPLICATION_JSON)
                .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeletePerson() throws Exception {
        mockMvc.perform(delete("/person/Cyril/Cobb"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePersonWithError() throws Exception {
        mockMvc.perform(delete("/person/Jacob/Dale"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testPhoneAlert() throws Exception {
        mockMvc.perform(get("/phoneAlert").contentType(APPLICATION_JSON)
                        .param("firestation", "2"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPhoneAlertWithUnknownFirestationNumber() throws Exception {
        mockMvc.perform(get("/phoneAlert").contentType(APPLICATION_JSON)
                .param("firestation", "8"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testEmailAlert() throws Exception {
        mockMvc.perform(get("/communityEmail").contentType(APPLICATION_JSON)
                .param("city", "Culver"))
                .andExpect(status().isOk());
    }

    @Test
    public void testEmailAlertWithUnknownCity() throws Exception {
        mockMvc.perform(get("/communityEmail").contentType(APPLICATION_JSON)
                .param("city", "Denver"))
                .andExpect(status().isBadRequest());
    }
}
