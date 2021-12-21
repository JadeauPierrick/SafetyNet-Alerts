package com.safetynet.safetynetalerts.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ChildAlertDTO {

    private List<ChildrenInHouseDTO> children;
    private List<AdultsInHouseDTO> adults;

    public ChildAlertDTO(List<ChildrenInHouseDTO> children, List<AdultsInHouseDTO> adults) {
        this.children = children;
        this.adults = adults;
    }

    public ChildAlertDTO() {
    }
}
