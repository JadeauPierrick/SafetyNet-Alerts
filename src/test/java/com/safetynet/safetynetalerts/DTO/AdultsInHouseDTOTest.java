package com.safetynet.safetynetalerts.DTO;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AdultsInHouseDTOTest {

    private AdultsInHouseDTO adultsInHouseDTO;

    @Test
    public void testGetFirstName(){
        String firstName = "Jacob";

        adultsInHouseDTO = new AdultsInHouseDTO();
        adultsInHouseDTO.setFirstName(firstName);

        assertThat(adultsInHouseDTO.getFirstName()).isEqualTo(firstName);
    }

    @Test
    public void testGetLastName(){
        String lastName = "Dale";

        adultsInHouseDTO = new AdultsInHouseDTO();
        adultsInHouseDTO.setLastName(lastName);

        assertThat(adultsInHouseDTO.getLastName()).isEqualTo(lastName);
    }

    @Test
    public void testGetAge(){
        int age = 9;

        adultsInHouseDTO = new AdultsInHouseDTO();
        adultsInHouseDTO.setAge(age);

        assertThat(adultsInHouseDTO.getAge()).isEqualTo(age);
    }

    @Test
    public void testAdultsInHouseDTO(){
        adultsInHouseDTO = new AdultsInHouseDTO("Jacob","Dale",9);

        assertThat(adultsInHouseDTO.getFirstName()).isEqualTo("Jacob");
        assertThat(adultsInHouseDTO.getLastName()).isEqualTo("Dale");
        assertThat(adultsInHouseDTO.getAge()).isEqualTo(9);
    }

    @Test
    public void equalsContract(){
        EqualsVerifier.simple().forClass(AdultsInHouseDTO.class).verify();
    }
}
