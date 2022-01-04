package com.safetynet.safetynetalerts.Service;

import com.safetynet.safetynetalerts.DTO.FireDTO;
import com.safetynet.safetynetalerts.DTO.FloodDTO;
import com.safetynet.safetynetalerts.DTO.PersonCoveredByItsFirestationNumberDTO;
import com.safetynet.safetynetalerts.DTO.PersonInfoDTO;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        medicalRecordList.add(new MedicalRecord("Jacques","Durand","09/17/1987",new ArrayList<>(),new ArrayList<>()));
        medicalRecordList.add(new MedicalRecord("Monique","Durand","08/18/1986",new ArrayList<>(),new ArrayList<>()));
        when(dataService.getMedicalrecords()).thenReturn(medicalRecordList);
    }

    @Test
    public void findAPersonWithFirstNameAndLastName(){
        Person person = personService.findPersonByFirstNameAndLastName("Jean", "Denis");
        assertEquals("Jean", person.getFirstName());
        assertEquals("Denis", person.getLastName());
        assertEquals("11 Silver St", person.getAddress());
        assertEquals("Culver", person.getCity());
        assertEquals(97451, person.getZip());
        assertEquals("888-999-000", person.getPhone());
        assertEquals("jd@email.com", person.getEmail());

    }

    @Test
    public void savePerson(){
        Person person = new Person();
        person.setFirstName("Peter");
        person.setLastName("Pan");
        person.setAddress("33 Belfort St");
        person.setCity("Culver");
        person.setZip(97451);
        person.setPhone("444-444-444");
        person.setEmail("pp@email.com");

        Person newPerson = personService.savePerson(person);

        assertThat(personService.displayPersons().size()).isEqualTo(5);
        assertThat(personService.displayPersons().get(4).getFirstName()).isEqualTo(newPerson.getFirstName());
        assertThat(personService.displayPersons().get(4).getLastName()).isEqualTo(newPerson.getLastName());
        assertThat(personService.displayPersons().get(4).getAddress()).isEqualTo(newPerson.getAddress());
        assertThat(personService.displayPersons().get(4).getCity()).isEqualTo(newPerson.getCity());
        assertThat(personService.displayPersons().get(4).getZip()).isEqualTo(newPerson.getZip());
        assertThat(personService.displayPersons().get(4).getPhone()).isEqualTo(newPerson.getPhone());
        assertThat(personService.displayPersons().get(4).getEmail()).isEqualTo(newPerson.getEmail());
    }

    @Test
    public void putAPersonWithFirstNameAndLastName(){
        Person person = personService.updatePerson("Jean", "Denis", new Person("Jean", "Denis","11 Silver St", "Culver",97451,"999-999-999","jeand@email.com" ));

        assertThat(personService.displayPersons().get(0).getFirstName()).isEqualTo(person.getFirstName());
        assertThat(personService.displayPersons().get(0).getLastName()).isEqualTo(person.getLastName());
        assertThat(personService.displayPersons().get(0).getAddress()).isEqualTo(person.getAddress());
        assertThat(personService.displayPersons().get(0).getCity()).isEqualTo(person.getCity());
        assertThat(personService.displayPersons().get(0).getZip()).isEqualTo(person.getZip());
        assertThat(personService.displayPersons().get(0).getPhone()).isEqualTo(person.getPhone());
        assertThat(personService.displayPersons().get(0).getEmail()).isEqualTo(person.getEmail());
        assertThat(personService.displayPersons().size()).isEqualTo(4);
    }

    @Test
    public void deleteAPersonWithFirstNameAndLastName(){
        boolean delete = personService.deletePerson("Sylvie","Denis");

        assertEquals(3, personService.displayPersons().size());
        assertTrue(delete);
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
        assertThat(ps.getPersonByFirestationNumberDTOList().get(1).getFirstName()).isEqualTo("Sylvie");
        assertThat(ps.getPersonByFirestationNumberDTOList().get(1).getLastName()).isEqualTo("Denis");
        assertThat(ps.getPersonByFirestationNumberDTOList().get(1).getAddress()).isEqualTo("11 Silver St");
        assertThat(ps.getPersonByFirestationNumberDTOList().get(1).getPhone()).isEqualTo("888-000-777");

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

        List<String> medications = new ArrayList<>();
        medications.add("Dafalgan");
        fireDTO.getPersonInfoDTOList().get(0).setMedications(medications);
        List<String> allergies = new ArrayList<>();
        allergies.add("Venin");
        fireDTO.getPersonInfoDTOList().get(0).setAllergies(allergies);

        assertEquals("Jean", fireDTO.getPersonInfoDTOList().get(0).getFirstName());
        assertEquals("Denis", fireDTO.getPersonInfoDTOList().get(0).getLastName());
        assertEquals("888-999-000", fireDTO.getPersonInfoDTOList().get(0).getPhone());
        assertEquals(33, fireDTO.getPersonInfoDTOList().get(0).getAge());
        assertEquals(medications, fireDTO.getPersonInfoDTOList().get(0).getMedications());
        assertEquals(allergies, fireDTO.getPersonInfoDTOList().get(0).getAllergies());
        assertEquals("Sylvie", fireDTO.getPersonInfoDTOList().get(1).getFirstName());
        assertEquals("Denis", fireDTO.getPersonInfoDTOList().get(1).getLastName());
        assertEquals("888-000-777", fireDTO.getPersonInfoDTOList().get(1).getPhone());
        assertEquals(33, fireDTO.getPersonInfoDTOList().get(1).getAge());
        assertEquals(1, fireDTO.getStation());
    }

    @Test
    public void floodByStationNumber(){
        List<Integer> listOfStationNumbers = new ArrayList<>();
        listOfStationNumbers.add(1);
        listOfStationNumbers.add(2);
        List<FloodDTO> floodDTOList = personService.floodByStationNumber(listOfStationNumbers);

        assertThat(floodDTOList.get(0).getStation()).isEqualTo(1);
        assertThat(floodDTOList.get(1).getStation()).isEqualTo(2);
    }

    @Test
    public void personInfoService(){
        List<PersonInfoDTO> personInfoDTOList = personService.personInfoService("Sylvie", "Denis");

        List<String> medications = new ArrayList<>();
        medications.add("Dafalgan");
        personInfoDTOList.get(0).setMedications(medications);

        List<String> allergies = new ArrayList<>();
        allergies.add("Venin");
        personInfoDTOList.get(0).setAllergies(allergies);

        assertThat(personInfoDTOList.get(0).getFirstName()).isEqualTo("Sylvie");
        assertThat(personInfoDTOList.get(0).getLastName()).isEqualTo("Denis");
        assertThat(personInfoDTOList.get(0).getAddress()).isEqualTo("11 Silver St");
        assertThat(personInfoDTOList.get(0).getAge()).isEqualTo(33);
        assertThat(personInfoDTOList.get(0).getEmail()).isEqualTo("sd@email.com");
        assertThat(personInfoDTOList.get(0).getMedications()).isEqualTo(medications);
        assertThat(personInfoDTOList.get(0).getAllergies()).isEqualTo(allergies);
    }

    @Test
    public void emailService(){
        List<String> emailList = personService.emailService("Culver");

        assertThat(emailList.get(0)).isEqualTo("jd@email.com");
        assertThat(emailList.get(1)).isEqualTo("sd@email.com");
        assertThat(emailList.get(2)).isEqualTo("jdurand@email.com");
        assertThat(emailList.get(3)).isEqualTo("mdurand@email.com");
    }
}
