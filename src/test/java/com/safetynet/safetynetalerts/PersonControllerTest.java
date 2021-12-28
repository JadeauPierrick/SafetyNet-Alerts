package com.safetynet.safetynetalerts;

import com.safetynet.safetynetalerts.controller.PersonController;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.PersonService;
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

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

    private Person person;
    private List<Person> personList;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @BeforeEach
    private void setUp(){
        personList = new ArrayList<>();
        person = new Person("John", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6512", "jaboyd@email.com");
        personList.add(person);
        when(personService.displayPersons()).thenReturn(personList);
    }

    @Test
    public void testListOfPersons() throws Exception {
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreatePerson() throws Exception {
        mockMvc.perform(post("/person").contentType(APPLICATION_JSON)
                    .content("{ \"firstName\":\"Damien\", \"lastName\":\"Chouly\", \"address\":\"Allée du vestiaire\", \"city\":\"Perpignan\", \"zip\":\"66000\", \"phone\":\"841-874-6512\", \"email\":\"ilesttempsdeprendremaretraite@email.com\" }"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdatePerson() throws Exception {
        mockMvc.perform(put("/person/John/Boyd").contentType(APPLICATION_JSON)
                    .content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"15 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jd@email.com\" }")
                    .accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePerson() throws Exception {
        mockMvc.perform(delete("/person/John/Boyd"))
                .andExpect(status().isOk());
    }
}
