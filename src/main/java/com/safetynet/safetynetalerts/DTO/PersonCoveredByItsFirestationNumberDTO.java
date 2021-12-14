package com.safetynet.safetynetalerts.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PersonCoveredByItsFirestationNumberDTO {

    private List<PersonByFirestationNumberDTO> personByFirestationNumberDTOList;
    private int numberOfAdults;
    private int numberOfChildren;

    public PersonCoveredByItsFirestationNumberDTO(List<PersonByFirestationNumberDTO> personByFirestationNumberDTOList, int numberOfAdults, int numberOfChildren) {
        this.personByFirestationNumberDTOList = personByFirestationNumberDTOList;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
    }

    public PersonCoveredByItsFirestationNumberDTO() {
    }
}
