package com.safetynet.safetynetalerts.DTO;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PersonInfoDTOTest {

    private PersonInfoDTO personInfoDTO;

    @Test
    public void testGetFirstName(){
        String firstName = "Nelson";

        personInfoDTO = new PersonInfoDTO();
        personInfoDTO.setFirstName(firstName);

        assertThat(firstName).isEqualTo(personInfoDTO.getFirstName());
    }

    @Test
    public void testGetLastName(){
        String lastName = "Moris";

        personInfoDTO = new PersonInfoDTO();
        personInfoDTO.setLastName(lastName);

        assertThat(lastName).isEqualTo(personInfoDTO.getLastName());
    }

    @Test
    public void testGetAddress(){
        String address = "51 Campbell St";

        personInfoDTO = new PersonInfoDTO();
        personInfoDTO.setAddress(address);

        assertThat(address).isEqualTo(personInfoDTO.getAddress());
    }

    @Test
    public void testGetAge(){
        int age = 17;

        personInfoDTO = new PersonInfoDTO();
        personInfoDTO.setAge(age);

        assertThat(age).isEqualTo(personInfoDTO.getAge());
    }

    @Test
    public void testGetEmail(){
        String email = "fd@email.com";

        personInfoDTO = new PersonInfoDTO();
        personInfoDTO.setEmail(email);

        assertThat(email).isEqualTo(personInfoDTO.getEmail());
    }

    @Test
    public void testPersonInfoDTO(){
        personInfoDTO = new PersonInfoDTO("Nelson","Moris","51 Campbell St",14,"nm@email.com",new ArrayList<>(),new ArrayList<>());

        List<String> medications = new ArrayList<>();
        medications.add("aznol");
        personInfoDTO.setMedications(medications);
        List<String> allergies = new ArrayList<>();
        allergies.add("venin");
        personInfoDTO.setAllergies(allergies);

        assertThat("Nelson").isEqualTo(personInfoDTO.getFirstName());
        assertThat("Moris").isEqualTo(personInfoDTO.getLastName());
        assertThat("51 Campbell St").isEqualTo(personInfoDTO.getAddress());
        assertThat(14).isEqualTo(personInfoDTO.getAge());
        assertThat("nm@email.com").isEqualTo(personInfoDTO.getEmail());
        assertThat(medications).isEqualTo(personInfoDTO.getMedications());
        assertThat(allergies).isEqualTo(personInfoDTO.getAllergies());
    }

    @Test
    public void equalsContract(){
        EqualsVerifier.simple().forClass(PersonInfoDTO.class).verify();
    }
}
