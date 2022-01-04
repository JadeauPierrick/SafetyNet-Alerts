package com.safetynet.safetynetalerts.DTO;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class FloodDTOTest {

    private FloodDTO floodDTO;

    @Test
    public void testFloodDTO(){
        floodDTO = new FloodDTO(new HashMap<>(),3);

        Map<String, List<PersonInfoForFireAndFloodDTO>> personInfo = new HashMap<>();
        String address = "11 Silver St";
        List<PersonInfoForFireAndFloodDTO> personInfoForFireAndFloodDTOList = new ArrayList<>();
        PersonInfoForFireAndFloodDTO personInfoForFireAndFloodDTO = new PersonInfoForFireAndFloodDTO("Jacob","Dale","000-999-888",15,new ArrayList<>(),new ArrayList<>());
        List<String> medications = new ArrayList<>();
        medications.add("aznol");
        personInfoForFireAndFloodDTO.setMedications(medications);
        List<String> allergies = new ArrayList<>();
        allergies.add("venin");
        personInfoForFireAndFloodDTO.setAllergies(allergies);
        personInfoForFireAndFloodDTOList.add(personInfoForFireAndFloodDTO);
        personInfo.put(address,personInfoForFireAndFloodDTOList);
        floodDTO.setPersonCoveredByAddress(personInfo);

        assertThat(floodDTO.getStation()).isEqualTo(3);
    }

    @Test
    public void equalsContract(){
        EqualsVerifier.simple().forClass(FloodDTO.class).verify();
    }
}
