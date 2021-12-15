package com.safetynet.safetynetalerts.DTO;

import lombok.Data;

@Data
public class ChildAlertDTO {

    private String firstName;
    private String lastName;
    private int age;

    public ChildAlertDTO(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public ChildAlertDTO() {
    }
}
