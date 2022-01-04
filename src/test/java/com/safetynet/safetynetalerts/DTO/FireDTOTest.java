package com.safetynet.safetynetalerts.DTO;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FireDTOTest {

    private FireDTO fireDTO;

    @Test
    public void testFireDTO(){
        fireDTO = new FireDTO(new ArrayList<>(),7);

        List<PersonInfoForFireAndFloodDTO> personInfoForFireAndFloodDTOList = new ArrayList<>();
        personInfoForFireAndFloodDTOList.add(new PersonInfoForFireAndFloodDTO("Peter","Pan","000-999-888",16,new ArrayList<>(),new ArrayList<>()));
        fireDTO.setPersonInfoDTOList(personInfoForFireAndFloodDTOList);

        assertThat(fireDTO.getPersonInfoDTOList().get(0).getFirstName()).isEqualTo("Peter");
        assertThat(fireDTO.getPersonInfoDTOList().get(0).getLastName()).isEqualTo("Pan");
        assertThat(fireDTO.getPersonInfoDTOList().get(0).getPhone()).isEqualTo("000-999-888");
        assertThat(fireDTO.getPersonInfoDTOList().get(0).getAge()).isEqualTo(16);
        assertThat(fireDTO.getStation()).isEqualTo(7);
    }

    @Test
    public void equalsContract(){
        EqualsVerifier.simple().forClass(FireDTO.class).verify();
    }
}
