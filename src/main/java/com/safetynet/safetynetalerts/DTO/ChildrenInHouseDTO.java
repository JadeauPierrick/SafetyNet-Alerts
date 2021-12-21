package com.safetynet.safetynetalerts.DTO;

import lombok.Data;

@Data
public class ChildrenInHouseDTO {

    private String firstName;
    private String lastName;
    private int age;

    public ChildrenInHouseDTO(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public ChildrenInHouseDTO() {
    }
}
