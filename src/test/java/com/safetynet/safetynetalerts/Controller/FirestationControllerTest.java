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
public class FirestationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext web;

    @BeforeEach
    private void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(web).build();
    }


    @Test
    public void testListOfFirestations() throws Exception {
        mockMvc.perform(get("/firestation"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindByAddress() throws Exception {
        mockMvc.perform(get("/firestation/29 15th St"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindByAddressWithUnknownAddress() throws Exception {
        mockMvc.perform(get("/firestation/11 Oslo St"))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testCreateFirestation() throws Exception {
        mockMvc.perform(post("/firestation").contentType(APPLICATION_JSON)
                        .content("{ \"address\":\"10 Maine St\", \"station\":\"3\" }"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateFirestationWithError() throws Exception {
        mockMvc.perform(post("/firestation").contentType(APPLICATION_JSON)
                .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateFirestation() throws Exception {
        mockMvc.perform(put("/firestation/10 Maine St").contentType(APPLICATION_JSON)
                .content("{ \"address\":\"10 Maine St\", \"station\":\"8\" }")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateFirestationWithError() throws Exception {
        mockMvc.perform(put("/firestation/11 Oslo St").contentType(APPLICATION_JSON)
                .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteFirestation() throws Exception {
        mockMvc.perform(delete("/firestation/1509 Culver St"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteFirestationWithUnknownAddress() throws Exception {
        mockMvc.perform(delete("/firestation/11 Oslo St"))
                .andExpect(status().isBadRequest());
    }
}