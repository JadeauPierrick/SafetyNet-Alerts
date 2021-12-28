package com.safetynet.safetynetalerts;

import com.safetynet.safetynetalerts.controller.MedicalRecordController;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
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

@WebMvcTest(controllers = MedicalRecordController.class)
public class MedicalRecordControllerTest {

    private MedicalRecord medicalRecord;
    private List<MedicalRecord> medicalRecordList;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordService medicalRecordService;

    @BeforeEach
    private void setUp(){
        medicalRecordList = new ArrayList<>();
        medicalRecord = new MedicalRecord("John", "Boyd", "03/06/1984",new ArrayList<>(), new ArrayList<>());
        medicalRecordList.add(medicalRecord);
        when(medicalRecordService.displayMedicalRecords()).thenReturn(medicalRecordList);
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
                        .content("{ \"firstName\":\"Damien\", \"lastName\":\"Chouly\", \"birthdate\":\"27/11/1985\", \"medications\":[\"mentalus:350mg\"], \"allergies\":[\"victoire\"] }"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateMedicalRecord() throws Exception {
        mockMvc.perform(put("/medicalRecord/John/Boyd").contentType(APPLICATION_JSON)
                    .content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:790g\", \"hydrapermazol:550mg\"], \"allergies\":[\"ninja\"] }")
                    .accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteMedicalRecord() throws Exception {
        mockMvc.perform(delete("/medicalRecord/John/Boyd"))
                .andExpect(status().isOk());
    }
}
