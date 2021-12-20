package com.safetynet.safetynetalerts.DTO;

import lombok.Data;

import java.util.List;

@Data
public class FireDTO {

    private List<PersonInfoForFireAndFloodDTO> personInfoDTOList;
    private int station;

    public FireDTO(List<PersonInfoForFireAndFloodDTO> personInfoDTOList, int station) {
        this.personInfoDTOList = personInfoDTOList;
        this.station = station;
    }

    public FireDTO() {
    }
}
