package com.safetynet.safetynetalerts.Model;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MedicalRecordTest {

    private MedicalRecord medicalRecord;

    @Test
    public void testGetFirstName(){
        String firstName = "Jacob";

        medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName(firstName);

        assertThat(medicalRecord.getFirstName()).isEqualTo(firstName);
    }

    @Test
    public void testGetLastName(){
        String lastName = "Dale";

        medicalRecord = new MedicalRecord();
        medicalRecord.setLastName(lastName);

        assertThat(medicalRecord.getLastName()).isEqualTo(lastName);
    }

    @Test
    public void testGetBirthdate(){
        String birthdate = "01/01/1980";

        medicalRecord = new MedicalRecord();
        medicalRecord.setBirthdate(birthdate);

        assertThat(medicalRecord.getBirthdate()).isEqualTo(birthdate);
    }

    @Test
    public void testGetMedications(){
        List<String> medications = new ArrayList<>();
        medications.add("aznol");

        medicalRecord = new MedicalRecord();
        medicalRecord.setMedications(medications);

        assertThat(medicalRecord.getMedications()).isEqualTo(medications);
    }

    @Test
    public void testGetAllergies(){
        List<String> allergies = new ArrayList<>();
        allergies.add("venin");

        medicalRecord = new MedicalRecord();
        medicalRecord.setAllergies(allergies);

        assertThat(medicalRecord.getAllergies()).isEqualTo(allergies);
    }

    @Test
    public void equalsContract(){
        EqualsVerifier.simple().forClass(MedicalRecord.class).verify();
    }
}
