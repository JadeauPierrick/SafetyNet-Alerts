package com.safetynet.safetynetalerts;

import com.safetynet.safetynetalerts.controller.FirestationController;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.service.FirestationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FirestationController.class)
public class FirestationControllerTest {

    private Firestation firestation;
    private List<Firestation> firestationList;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FirestationService firestationService;

    @BeforeEach
    private void setUp(){
        firestationList = new ArrayList<>();
        firestation = new Firestation("14 rue des bois", 8);
        firestationList.add(firestation);
        when(firestationService.displayFirestations()).thenReturn(firestationList);
    }

    @Test
    public void testListOfFirestations() throws Exception {
        mockMvc.perform(get("/firestation"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateFirestation() throws Exception {
        mockMvc.perform(post("/firestation").contentType(APPLICATION_JSON)
                    .content("{ \"address\":\"1509 Culver St\", \"station\":\"3\" }"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateFirestation() throws Exception {
        mockMvc.perform(put("/firestation/14 rue des bois").contentType(APPLICATION_JSON)
                    .content("{ \"address\":\"14 rue des bois\", \"station\":\"7\" }")
                    .accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteFirestation() throws Exception {
        mockMvc.perform(delete("/firestation/14 rue des bois"))
                .andExpect(status().isOk());
    }
}
