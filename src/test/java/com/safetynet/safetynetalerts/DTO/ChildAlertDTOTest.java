package com.safetynet.safetynetalerts.DTO;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ChildAlertDTOTest {

    private ChildAlertDTO childAlertDTO;

    @Test
    public void testChildAlertDTO(){
        childAlertDTO = new ChildAlertDTO(new ArrayList<>(),new ArrayList<>());

        List<ChildrenInHouseDTO> childrenInHouseDTOList = new ArrayList<>();
        childrenInHouseDTOList.add(new ChildrenInHouseDTO("Jacob","Heldens",8));
        childrenInHouseDTOList.add(new ChildrenInHouseDTO("Oliver","Heldens",16));
        childAlertDTO.setChildren(childrenInHouseDTOList);

        List<AdultsInHouseDTO> adultsInHouseDTOList = new ArrayList<>();
        adultsInHouseDTOList.add(new AdultsInHouseDTO("Robert","Heldens",45));
        adultsInHouseDTOList.add(new AdultsInHouseDTO("Bianca","Heldens",44));
        childAlertDTO.setAdults(adultsInHouseDTOList);

        assertThat(childAlertDTO.getChildren().get(0).getFirstName()).isEqualTo("Jacob");
        assertThat(childAlertDTO.getChildren().get(0).getLastName()).isEqualTo("Heldens");
        assertThat(childAlertDTO.getChildren().get(0).getAge()).isEqualTo(8);
        assertThat(childAlertDTO.getAdults().get(0).getFirstName()).isEqualTo("Robert");
        assertThat(childAlertDTO.getAdults().get(0).getLastName()).isEqualTo("Heldens");
        assertThat(childAlertDTO.getAdults().get(0).getAge()).isEqualTo(45);
    }

    @Test
    public void equalsContract(){
        EqualsVerifier.simple().forClass(ChildAlertDTO.class).verify();
    }
}
