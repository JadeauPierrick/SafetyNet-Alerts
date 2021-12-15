package com.safetynet.safetynetalerts.mapper;

import com.safetynet.safetynetalerts.DTO.ChildAlertDTO;
import com.safetynet.safetynetalerts.DTO.PersonByFirestationNumberDTO;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.CalculateService;

public class Mapper {

    private CalculateService calculateService;

    public static PersonByFirestationNumberDTO toPersonByFirestationNumberDTO(Person person){
        String firstName = person.getFirstName();
        String lastName = person.getLastName();
        String address = person.getAddress();
        String phone = person.getPhone();

        return new PersonByFirestationNumberDTO(firstName, lastName, address, phone);
    }
}

