package com.safetynet.safetynetalerts.DTO;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ChildrenInHouseDTOTest {

    private ChildrenInHouseDTO childrenInHouseDTO;

    @Test
    public void testGetFirstName(){
        String firstName = "Scott";

        childrenInHouseDTO = new ChildrenInHouseDTO("Scott","",8);

        assertThat(firstName).isEqualTo(childrenInHouseDTO.getFirstName());
    }

    @Test
    public void testGetLastName(){
        String lastName = "Davies";

        childrenInHouseDTO = new ChildrenInHouseDTO("","Davies",8);

        assertThat(lastName).isEqualTo(childrenInHouseDTO.getLastName());
    }

    @Test
    public void testGetAge(){
        int age = 8;

        childrenInHouseDTO = new ChildrenInHouseDTO("","",8);

        assertThat(age).isEqualTo(childrenInHouseDTO.getAge());
    }

    @Test
    public void testChildrenInHouse(){
        childrenInHouseDTO = new ChildrenInHouseDTO();

        childrenInHouseDTO.setFirstName("Scott");
        childrenInHouseDTO.setLastName("Davies");
        childrenInHouseDTO.setAge(8);

        assertThat(childrenInHouseDTO.getFirstName()).isEqualTo("Scott");
        assertThat(childrenInHouseDTO.getLastName()).isEqualTo("Davies");
        assertThat(childrenInHouseDTO.getAge()).isEqualTo(8);
    }
}
