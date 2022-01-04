package com.safetynet.safetynetalerts.DTO;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ChildrenInHouseDTOTest {

    private ChildrenInHouseDTO childrenInHouseDTO;

    @Test
    public void testGetFirstName(){
        String firstName = "Scott";

        childrenInHouseDTO = new ChildrenInHouseDTO();
        childrenInHouseDTO.setFirstName(firstName);

        assertThat(firstName).isEqualTo(childrenInHouseDTO.getFirstName());
    }

    @Test
    public void testGetLastName(){
        String lastName = "Davies";

        childrenInHouseDTO = new ChildrenInHouseDTO();
        childrenInHouseDTO.setLastName(lastName);

        assertThat(lastName).isEqualTo(childrenInHouseDTO.getLastName());
    }

    @Test
    public void testGetAge(){
        int age = 8;

        childrenInHouseDTO = new ChildrenInHouseDTO();
        childrenInHouseDTO.setAge(age);

        assertThat(age).isEqualTo(childrenInHouseDTO.getAge());
    }

    @Test
    public void testChildrenInHouse(){
        childrenInHouseDTO = new ChildrenInHouseDTO("Scott","Davies",8);

        assertThat("Scott").isEqualTo(childrenInHouseDTO.getFirstName());
        assertThat("Davies").isEqualTo(childrenInHouseDTO.getLastName());
        assertThat(8).isEqualTo(childrenInHouseDTO.getAge());
    }

    @Test
    public void equalsContract(){
        EqualsVerifier.simple().forClass(ChildrenInHouseDTO.class).verify();
    }
}
