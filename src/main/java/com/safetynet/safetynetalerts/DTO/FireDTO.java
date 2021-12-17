package com.safetynet.safetynetalerts.DTO;

import lombok.Data;

import java.util.List;

@Data
public class FireDTO {

    private List<PersonInfoForFireDTO> personInfoForFireDTOList;
    private int station;

    public FireDTO(List<PersonInfoForFireDTO> personInfoForFireDTOList, int station) {
        this.personInfoForFireDTOList = personInfoForFireDTOList;
        this.station = station;
    }

    public FireDTO() {
    }
}
