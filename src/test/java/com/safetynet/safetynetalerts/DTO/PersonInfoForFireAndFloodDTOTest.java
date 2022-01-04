package com.safetynet.safetynetalerts.DTO;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PersonInfoForFireAndFloodDTOTest {

    private PersonInfoForFireAndFloodDTO personInfoForFireAndFloodDTO;

    @Test
    public void testGetFirstName(){
        String firstName = "Peter";

        personInfoForFireAndFloodDTO = new PersonInfoForFireAndFloodDTO();
        personInfoForFireAndFloodDTO.setFirstName(firstName);

        assertThat(firstName).isEqualTo(personInfoForFireAndFloodDTO.getFirstName());
    }

    @Test
    public void testGetLastName(){
        String lastName = "Pan";

        personInfoForFireAndFloodDTO = new PersonInfoForFireAndFloodDTO();
        personInfoForFireAndFloodDTO.setLastName(lastName);

        assertThat(lastName).isEqualTo(personInfoForFireAndFloodDTO.getLastName());
    }

    @Test
    public void testGetPhone(){
        String phone = "000-999-888";

        personInfoForFireAndFloodDTO = new PersonInfoForFireAndFloodDTO();
        personInfoForFireAndFloodDTO.setPhone(phone);

        assertThat(phone).isEqualTo(personInfoForFireAndFloodDTO.getPhone());
    }

    @Test
    public void testGetAge(){
        int age = 16;

        personInfoForFireAndFloodDTO = new PersonInfoForFireAndFloodDTO();
        personInfoForFireAndFloodDTO.setAge(age);

        assertThat(age).isEqualTo(personInfoForFireAndFloodDTO.getAge());
    }

    @Test
    public void testPersonInfoForFireAndFloodDTO(){
        personInfoForFireAndFloodDTO = new PersonInfoForFireAndFloodDTO("Dan","Carter","999-777-888",35,new ArrayList<>(),new ArrayList<>());

        List<String> medications = new ArrayList<>();
        medications.add("aznol");
        personInfoForFireAndFloodDTO.setMedications(medications);
        List<String> allergies = new ArrayList<>();
        allergies.add("venin");
        personInfoForFireAndFloodDTO.setAllergies(allergies);

        assertThat(personInfoForFireAndFloodDTO.getFirstName()).isEqualTo("Dan");
        assertThat(personInfoForFireAndFloodDTO.getLastName()).isEqualTo("Carter");
        assertThat(personInfoForFireAndFloodDTO.getPhone()).isEqualTo("999-777-888");
        assertThat(personInfoForFireAndFloodDTO.getAge()).isEqualTo(35);
        assertThat(personInfoForFireAndFloodDTO.getMedications()).isEqualTo(medications);
        assertThat(personInfoForFireAndFloodDTO.getAllergies()).isEqualTo(allergies);
    }

    @Test
    public void equalsContract(){
        EqualsVerifier.simple().forClass(PersonInfoForFireAndFloodDTO.class).verify();
    }
}
