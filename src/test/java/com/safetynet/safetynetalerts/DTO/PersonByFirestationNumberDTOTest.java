package com.safetynet.safetynetalerts.DTO;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PersonByFirestationNumberDTOTest {

    private PersonByFirestationNumberDTO personByFirestationNumberDTO;

    @Test
    public void testGetFirstName(){
        String firstName = "Jacob";

        personByFirestationNumberDTO = new PersonByFirestationNumberDTO("Jacob","","","");

        assertThat(firstName).isEqualTo(personByFirestationNumberDTO.getFirstName());
    }

    @Test
    public void testGetLastName(){
        String lastName = "Davies";

        personByFirestationNumberDTO = new PersonByFirestationNumberDTO("","Davies","","");

        assertThat(lastName).isEqualTo(personByFirestationNumberDTO.getLastName());
    }

    @Test
    public void testGetAddress(){
        String address = "6 Johnson St";

        personByFirestationNumberDTO = new PersonByFirestationNumberDTO("","","6 Johnson St","");

        assertThat(address).isEqualTo(personByFirestationNumberDTO.getAddress());
    }

    @Test
    public void testGetPhone(){
        String phone = "888-000-999";

        personByFirestationNumberDTO = new PersonByFirestationNumberDTO("","","","888-000-999");

        assertThat(phone).isEqualTo(personByFirestationNumberDTO.getPhone());
    }

    @Test
    public void testPersonByFirestationNumberDTO(){
        personByFirestationNumberDTO = new PersonByFirestationNumberDTO();

        personByFirestationNumberDTO.setFirstName("Jacob");
        personByFirestationNumberDTO.setLastName("Davies");
        personByFirestationNumberDTO.setAddress("6 Johnson St");
        personByFirestationNumberDTO.setPhone("888-000-999");

        assertThat(personByFirestationNumberDTO.getFirstName()).isEqualTo("Jacob");
        assertThat(personByFirestationNumberDTO.getLastName()).isEqualTo("Davies");
        assertThat(personByFirestationNumberDTO.getAddress()).isEqualTo("6 Johnson St");
        assertThat(personByFirestationNumberDTO.getPhone()).isEqualTo("888-000-999");
    }
}
