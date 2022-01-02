package com.safetynet.safetynetalerts.DTO;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AdultsInHouseDTOTest {

    private AdultsInHouseDTO adultsInHouseDTO;

    @Test
    public void testGetFirstName(){
        String firstName = "Jacob";

        adultsInHouseDTO = new AdultsInHouseDTO("Jacob", "", 9);

        assertThat(firstName).isEqualTo(adultsInHouseDTO.getFirstName());
    }

    @Test
    public void testGetLastName(){
        String lastName = "Dale";

        adultsInHouseDTO = new AdultsInHouseDTO("", "Dale", 4);

        assertThat(lastName).isEqualTo(adultsInHouseDTO.getLastName());
    }

    @Test
    public void testGetAge(){
        int age = 9;

        adultsInHouseDTO = new AdultsInHouseDTO("","",9);

        assertThat(age).isEqualTo(adultsInHouseDTO.getAge());
    }

    @Test
    public void testAdultsInHouseDTO(){
        adultsInHouseDTO = new AdultsInHouseDTO();

        adultsInHouseDTO.setFirstName("Jacob");
        adultsInHouseDTO.setLastName("Dale");
        adultsInHouseDTO.setAge(9);

        assertThat(adultsInHouseDTO.getFirstName()).isEqualTo("Jacob");
        assertThat(adultsInHouseDTO.getLastName()).isEqualTo("Dale");
        assertThat(adultsInHouseDTO.getAge()).isEqualTo(9);
    }
}
