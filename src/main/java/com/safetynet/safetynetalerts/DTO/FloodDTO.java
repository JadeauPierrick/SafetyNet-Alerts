package com.safetynet.safetynetalerts.DTO;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class FloodDTO {

    private Map<String, List<PersonInfoForFireAndFloodDTO>> personCoveredByAddress;
    private int station;

    public FloodDTO(Map<String, List<PersonInfoForFireAndFloodDTO>> personCoveredByAddress, int station) {
        this.personCoveredByAddress = personCoveredByAddress;
        this.station = station;
    }

    public FloodDTO() {
    }
}
