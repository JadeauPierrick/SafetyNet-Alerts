package com.safetynet.safetynetalerts.Service;

import com.safetynet.safetynetalerts.DTO.ChildAlertDTO;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.DataService;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordServiceTest {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @MockBean
    private DataService dataService;

    @BeforeEach
    private void setUp(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Jean", "Denis", "11 Silver St", "Culver", 97451, "888-999-000", "jd@email.com"));
        personList.add(new Person("Sylvie", "Denis", "11 Silver St", "Culver", 97451, "888-000-777","sd@email.com"));
        personList.add(new Person("Jono","Denis","11 Silver St","Culver",97451,"888-000-777","jono@email.com"));
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
        medicalRecordList.add(new MedicalRecord("Jono","Denis","12/12/2020",new ArrayList<>(),new ArrayList<>()));
        medicalRecordList.add(new MedicalRecord("Jacques","Durand","09/17/1987",new ArrayList<>(),new ArrayList<>()));
        medicalRecordList.add(new MedicalRecord("Monique","Durand","08/18/1986",new ArrayList<>(),new ArrayList<>()));
        when(dataService.getMedicalrecords()).thenReturn(medicalRecordList);
    }

    @Test
    public void findMedicalRecordByFirstNameAndLastName(){
        MedicalRecord medicalRecord = medicalRecordService.findMedicalRecordByFirstNameAndLastName("Jean","Denis");

        assertThat(medicalRecord.getFirstName()).isEqualTo("Jean");
        assertThat(medicalRecord.getLastName()).isEqualTo("Denis");
        assertThat(medicalRecord.getBirthdate()).isEqualTo("01/12/1988");
    }

    @Test
    public void saveMedicalRecord(){
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Jacob");
        medicalRecord.setLastName("Dale");
        medicalRecord.setBirthdate("03/01/1984");
        List<String> medications = new ArrayList<>();
        medications.add("aznol");
        medicalRecord.setMedications(medications);
        List<String> allergies = new ArrayList<>();
        allergies.add("venin");
        medicalRecord.setAllergies(allergies);

        MedicalRecord newMedicalRecord = medicalRecordService.saveMedicalRecord(medicalRecord);

        assertThat(medicalRecordService.displayMedicalRecords().size()).isEqualTo(6);
        assertThat(medicalRecordService.displayMedicalRecords().get(5).getFirstName()).isEqualTo(newMedicalRecord.getFirstName());
        assertThat(medicalRecordService.displayMedicalRecords().get(5).getLastName()).isEqualTo(newMedicalRecord.getLastName());
        assertThat(medicalRecordService.displayMedicalRecords().get(5).getBirthdate()).isEqualTo(newMedicalRecord.getBirthdate());
        assertThat(medicalRecordService.displayMedicalRecords().get(5).getMedications()).isEqualTo(newMedicalRecord.getMedications());
        assertThat(medicalRecordService.displayMedicalRecords().get(5).getAllergies()).isEqualTo(newMedicalRecord.getAllergies());
    }

    @Test
    public void updateMedicalRecord(){
        MedicalRecord medicalRecord = medicalRecordService.updateMedicalRecord("Jacques","Durand",new MedicalRecord("Jacques","Durand","14/07/1987",new ArrayList<>(), new ArrayList<>()));

        assertThat(medicalRecordService.displayMedicalRecords().size()).isEqualTo(5);
        assertThat(medicalRecordService.displayMedicalRecords().get(3).getFirstName()).isEqualTo(medicalRecord.getFirstName());
        assertThat(medicalRecordService.displayMedicalRecords().get(3).getLastName()).isEqualTo(medicalRecord.getLastName());
        assertThat(medicalRecordService.displayMedicalRecords().get(3).getBirthdate()).isEqualTo(medicalRecord.getBirthdate());
    }

    @Test
    public void deleteMedicalRecord(){
        boolean delete = medicalRecordService.deleteMedicalRecord("Monique","Durand");

        assertThat(medicalRecordService.displayMedicalRecords().size()).isEqualTo(4);
        assertThat(delete).isEqualTo(true);
    }

    @Test
    public void childAlertService(){
        ChildAlertDTO childAlertDTO = medicalRecordService.childAlertService("11 Silver St");

        assertThat(childAlertDTO.getChildren().get(0).getFirstName()).isEqualTo("Jono");
        assertThat(childAlertDTO.getChildren().get(0).getLastName()).isEqualTo("Denis");
        assertThat(childAlertDTO.getChildren().get(0).getAge()).isEqualTo(1);
        assertThat(childAlertDTO.getAdults().get(0).getFirstName()).isEqualTo("Jean");
        assertThat(childAlertDTO.getAdults().get(0).getLastName()).isEqualTo("Denis");
        assertThat(childAlertDTO.getAdults().get(0).getAge()).isEqualTo(33);
    }

}
