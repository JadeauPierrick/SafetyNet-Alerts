package com.safetynet.safetynetalerts.DTO;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PersonInfoDTOTest {

    private PersonInfoDTO personInfoDTO;

    @Test
    public void testGetFirstName(){
        String firstName = "Nelson";

        personInfoDTO = new PersonInfoDTO("Nelson","","",9,"",new ArrayList<>(),new ArrayList<>());

        assertThat(firstName).isEqualTo(personInfoDTO.getFirstName());
    }

    @Test
    public void testGetLastName(){
        String lastName = "Moris";

        personInfoDTO = new PersonInfoDTO("","Moris","",19,"",new ArrayList<>(),new ArrayList<>());

        assertThat(lastName).isEqualTo(personInfoDTO.getLastName());
    }

    @Test
    public void testGetAddress(){
        String address = "51 Campbell St";

        personInfoDTO = new PersonInfoDTO("","","51 Campbell St",15,"",new ArrayList<>(),new ArrayList<>());

        assertThat(address).isEqualTo(personInfoDTO.getAddress());
    }

    @Test
    public void testGetAge(){
        int age = 17;

        personInfoDTO = new PersonInfoDTO("","","",17,"",new ArrayList<>(),new ArrayList<>());

        assertThat(age).isEqualTo(personInfoDTO.getAge());
    }

    @Test
    public void testGetEmail(){
        String email = "fd@email.com";

        personInfoDTO = new PersonInfoDTO("","","",51,"fd@email.com",new ArrayList<>(),new ArrayList<>());

        assertThat(email).isEqualTo(personInfoDTO.getEmail());
    }

    @Test
    public void testPersonInfoDTO(){
        personInfoDTO = new PersonInfoDTO();

        personInfoDTO.setFirstName("Nelson");
        personInfoDTO.setLastName("Moris");
        personInfoDTO.setAddress("51 Campbell St");
        personInfoDTO.setAge(14);
        personInfoDTO.setEmail("nm@email.com");
        List<String> medications = new ArrayList<>();
        medications.add("aznol");
        personInfoDTO.setMedications(medications);
        List<String> allergies = new ArrayList<>();
        allergies.add("venin");
        personInfoDTO.setAllergies(allergies);

        assertThat(personInfoDTO.getFirstName()).isEqualTo("Nelson");
        assertThat(personInfoDTO.getLastName()).isEqualTo("Moris");
        assertThat(personInfoDTO.getAddress()).isEqualTo("51 Campbell St");
        assertThat(personInfoDTO.getAge()).isEqualTo(14);
        assertThat(personInfoDTO.getEmail()).isEqualTo("nm@email.com");
        assertThat(personInfoDTO.getMedications()).isEqualTo(medications);
        assertThat(personInfoDTO.getAllergies()).isEqualTo(allergies);


    }
}
