package com.safetynet.safetynetalerts.Service;

import com.safetynet.safetynetalerts.DTO.FireDTO;
import com.safetynet.safetynetalerts.DTO.PersonCoveredByItsFirestationNumberDTO;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.DataService;
import com.safetynet.safetynetalerts.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @MockBean
    private DataService dataService;

    @BeforeEach
    private void setUp(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Jean", "Denis", "11 Silver St", "Culver", 97451, "888-999-000", "jd@email.com"));
        personList.add(new Person("Sylvie", "Denis", "11 Silver St", "Culver", 97451, "888-000-777","sd@email.com"));
        personList.add(new Person("Jacques", "Durand", "87 Maine St","Culver",97451,"665-766-999","jdurand@email.com"));
        personList.add((new Person("Monique","Durand","87 Maine St","Culver",97451,"333-222-111","mdurand@email.com")));
        when(dataService.getPersons()).thenReturn(personList);

        List<Firestation> firestationList = new ArrayList<>();
        firestationList.add(new Firestation("11 Silver St",1));
        firestationList.add(new Firestation("87 Maine St",2));
        when(dataService.getFirestations()).thenReturn(firestationList);

        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(new MedicalRecord("Jean","Denis","01/12/1988",new ArrayList<>(), new ArrayList<>()));
        medicalRecordList.add(new MedicalRecord("Sylvie","Denis","09/11/1988",new ArrayList<>(), new ArrayList<>()));
        medicalRecordList.add(new MedicalRecord("Jacques","Durand","17/09/1987",new ArrayList<>(),new ArrayList<>()));
        medicalRecordList.add(new MedicalRecord("Monique","Durand","18/08/1986",new ArrayList<>(),new ArrayList<>()));
        when(dataService.getMedicalrecords()).thenReturn(medicalRecordList);
    }

    @Test
    public void findAPersonWithFirstNameAndLastName(){
        Person person = personService.findPersonByFirstNameAndLastName("Jean", "Denis");
        assertEquals("Jean", person.getFirstName());
        assertEquals("Denis", person.getLastName());
    }

    @Test
    public void putAPersonWithFirstNameAndLastName(){
        Person person = personService.updatePerson("Jean", "Denis", new Person("Jean", "Denis","11 Silver St", "Culver",97451,"999-999-999","jeand@email.com" ));
        assertEquals("999-999-999", person.getPhone());
        assertEquals("jeand@email.com", person.getEmail());
    }

    @Test
    public void deleteAPersonWithFirstNameAndLastName(){
        boolean delete = personService.deletePerson("Sylvie","Denis");
        assertEquals(3, personService.displayPersons().size());
    }

    @Test
    public void findAllPersonsByItsFirestationNumber(){
        List<Person> psList = personService.findAllPersonsByItsFirestationNumber(2);
        assertEquals("Jacques",psList.get(0).getFirstName());
        assertEquals("Durand", psList.get(0).getLastName());
        assertEquals("665-766-999", psList.get(0).getPhone());
        assertEquals("Monique", psList.get(1).getFirstName());
    }

    @Test
    public void personCoveredByItsFirestationNumber(){
        PersonCoveredByItsFirestationNumberDTO ps = personService.personCoveredByItsFirestationNumber(1);
        assertThat(ps.getPersonByFirestationNumberDTOList().get(0).getFirstName()).isEqualTo("Jean");
        assertThat(ps.getPersonByFirestationNumberDTOList().get(0).getLastName()).isEqualTo("Denis");
        assertThat(ps.getPersonByFirestationNumberDTOList().get(0).getAddress()).isEqualTo("11 Silver St");
        assertThat(ps.getPersonByFirestationNumberDTOList().get(0).getPhone()).isEqualTo("888-999-000");

        assertEquals(2, ps.getNumberOfAdults());
        assertEquals(0, ps.getNumberOfChildren());
    }

    @Test
    public void phoneAlertService(){
        List<String> phoneList = personService.phoneAlertService(2);
        assertEquals("665-766-999", phoneList.get(0));
        assertEquals("333-222-111", phoneList.get(1));
    }

    @Test
    public void fireAlertService(){
        FireDTO fireDTO = personService.fireAlertService("11 Silver St");
        assertEquals("Jean", fireDTO.getPersonInfoDTOList().get(0).getFirstName());
        assertEquals("Denis", fireDTO.getPersonInfoDTOList().get(0).getLastName());
        assertEquals("888-999-000", fireDTO.getPersonInfoDTOList().get(0).getPhone());
        assertEquals(33, fireDTO.getPersonInfoDTOList().get(0).getAge());
        assertEquals(1, fireDTO.getStation());
    }
}
