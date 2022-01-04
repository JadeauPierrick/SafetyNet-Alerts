package com.safetynet.safetynetalerts.DTO;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PersonByFirestationNumberDTOTest {

    private PersonByFirestationNumberDTO personByFirestationNumberDTO;

    @Test
    public void testGetFirstName(){
        String firstName = "Jacob";

        personByFirestationNumberDTO = new PersonByFirestationNumberDTO();
        personByFirestationNumberDTO.setFirstName(firstName);

        assertThat(firstName).isEqualTo(personByFirestationNumberDTO.getFirstName());
    }

    @Test
    public void testGetLastName(){
        String lastName = "Davies";

        personByFirestationNumberDTO = new PersonByFirestationNumberDTO();
        personByFirestationNumberDTO.setLastName(lastName);

        assertThat(lastName).isEqualTo(personByFirestationNumberDTO.getLastName());
    }

    @Test
    public void testGetAddress(){
        String address = "6 Johnson St";

        personByFirestationNumberDTO = new PersonByFirestationNumberDTO();
        personByFirestationNumberDTO.setAddress(address);

        assertThat(address).isEqualTo(personByFirestationNumberDTO.getAddress());
    }

    @Test
    public void testGetPhone(){
        String phone = "888-000-999";

        personByFirestationNumberDTO = new PersonByFirestationNumberDTO();
        personByFirestationNumberDTO.setPhone(phone);

        assertThat(phone).isEqualTo(personByFirestationNumberDTO.getPhone());
    }

    @Test
    public void testPersonByFirestationNumberDTO(){
        personByFirestationNumberDTO = new PersonByFirestationNumberDTO("Jacob","Davies","6 Johnson St","888-000-999");

        assertThat("Jacob").isEqualTo(personByFirestationNumberDTO.getFirstName());
        assertThat("Davies").isEqualTo(personByFirestationNumberDTO.getLastName());
        assertThat("6 Johnson St").isEqualTo(personByFirestationNumberDTO.getAddress());
        assertThat("888-000-999").isEqualTo(personByFirestationNumberDTO.getPhone());
    }

    @Test
    public void equalsContract(){
        EqualsVerifier.simple().forClass(PersonByFirestationNumberDTO.class).verify();
    }
}
