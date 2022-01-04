package com.safetynet.safetynetalerts.IT;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FirestationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testListOfFirestations() throws Exception {
        mockMvc.perform(get("/firestation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].address", is("1509 Culver St")));
    }

    @Test
    public void testFindFirestationByAddress() throws Exception {
        mockMvc.perform(get("/firestation/1509 Culver St"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("address", is("1509 Culver St")))
                .andExpect(jsonPath("station", is(3)));
    }
}
