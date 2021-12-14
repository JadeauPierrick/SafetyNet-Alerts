package com.safetynet.safetynetalerts.controller;


import com.safetynet.safetynetalerts.DTO.PersonByFirestationNumberDTO;
import com.safetynet.safetynetalerts.DTO.PersonCoveredByItsFirestationNumberDTO;
import com.safetynet.safetynetalerts.mapper.Mapper;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.CountService;
import com.safetynet.safetynetalerts.service.DTOService;
import com.safetynet.safetynetalerts.service.FirestationService;
import com.safetynet.safetynetalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DTOController {

    @Autowired
    private FirestationService firestationService;

    @Autowired
    private PersonService personService;

    @Autowired
    private CountService countService;


    @RequestMapping(value = "/firestation", params = { "stationNumber" })
    public PersonCoveredByItsFirestationNumberDTO personCoveredByItsFirestationNumberDTOList(@RequestParam("stationNumber") int stationNumber){
        System.out.println(stationNumber);
        List<Firestation> firestationList = firestationService.findFirestationByStation(stationNumber);
        List<PersonByFirestationNumberDTO> personList = personService.displayPersons().stream()
                .filter(x -> firestationList.stream().map(Firestation::getAddress).anyMatch(address -> address.equals(x.getAddress())))
                .map(Mapper::toPersonByFirestationNumberDTO)
                .collect(Collectors.toList());

        int numberOfChildren = countService.numberOfChildren(personList);

        int numberOfAdults = personList.toArray().length - numberOfChildren;

        return new PersonCoveredByItsFirestationNumberDTO(personList, numberOfAdults, numberOfChildren);
    }
}
