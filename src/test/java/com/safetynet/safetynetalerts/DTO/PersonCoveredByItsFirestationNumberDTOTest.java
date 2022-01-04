package com.safetynet.safetynetalerts.DTO;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PersonCoveredByItsFirestationNumberDTOTest {

    private PersonCoveredByItsFirestationNumberDTO personCoveredByItsFirestationNumberDTO;

    @Test
    public void testPersonCoveredByItsFirestationNumberDTO(){
        personCoveredByItsFirestationNumberDTO = new PersonCoveredByItsFirestationNumberDTO(new ArrayList<>(),2,4);

        List<PersonByFirestationNumberDTO> personByFirestationNumberDTOList = new ArrayList<>();
        PersonByFirestationNumberDTO person = new PersonByFirestationNumberDTO("Jacob","Dale","12 Oslo St","999-999-999");
        personByFirestationNumberDTOList.add(person);
        personCoveredByItsFirestationNumberDTO.setPersonByFirestationNumberDTOList(personByFirestationNumberDTOList);

        assertThat(personCoveredByItsFirestationNumberDTO.getPersonByFirestationNumberDTOList().get(0).getFirstName()).isEqualTo("Jacob");
        assertThat(personCoveredByItsFirestationNumberDTO.getPersonByFirestationNumberDTOList().get(0).getLastName()).isEqualTo("Dale");
        assertThat(personCoveredByItsFirestationNumberDTO.getPersonByFirestationNumberDTOList().get(0).getAddress()).isEqualTo("12 Oslo St");
        assertThat(personCoveredByItsFirestationNumberDTO.getPersonByFirestationNumberDTOList().get(0).getPhone()).isEqualTo("999-999-999");
        assertThat(personCoveredByItsFirestationNumberDTO.getNumberOfAdults()).isEqualTo(2);
        assertThat(personCoveredByItsFirestationNumberDTO.getNumberOfChildren()).isEqualTo(4);
    }

    @Test
    public void equalsContract(){
        EqualsVerifier.simple().forClass(PersonCoveredByItsFirestationNumberDTO.class).verify();
    }
}
