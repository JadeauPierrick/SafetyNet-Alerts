package com.safetynet.safetynetalerts.DTO;

import lombok.Data;

@Data
public class AdultsInHouseDTO {

    private String firstName;
    private String lastName;
    private int age;

    public AdultsInHouseDTO(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public AdultsInHouseDTO() {
    }
}
