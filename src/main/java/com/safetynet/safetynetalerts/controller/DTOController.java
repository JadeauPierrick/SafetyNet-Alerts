package com.safetynet.safetynetalerts.controller;


import com.safetynet.safetynetalerts.DTO.*;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DTOController {

    @Autowired
    private FirestationService firestationService;

    @Autowired
    private PersonService personService;

    @Autowired
    private MedicalRecordService medicalRecordService;


    @RequestMapping(value = "/firestation", params = { "stationNumber" })
    public PersonCoveredByItsFirestationNumberDTO personCoveredByItsFirestationNumberDTOList(@RequestParam("stationNumber") int stationNumber){
        return personService.personCoveredByItsFirestationNumber(stationNumber);
    }

    @RequestMapping(value = "/childAlert", params = { "address" })
    public List<ChildAlertDTO> childAlert(@RequestParam("address") String address){
        return medicalRecordService.childAlertService(address);
    }

    @RequestMapping(value = "/phoneAlert", params = { "firestation" })
    public List<String> phoneAlert(@RequestParam("firestation") int firestationNumber){
        return personService.phoneAlertService(firestationNumber);
    }

    @RequestMapping(value = "/fire", params = { "address" })
    public FireDTO fireAlert(@RequestParam("address") String address){
        return personService.fireAlertService(address);
    }

    @RequestMapping(value = "/flood/stations", params = { "stations" })
    public List<FloodDTO> floodAlert(@RequestParam("stations") List<Integer> listOfStationNumbers){
        return personService.floodByStationNumber(listOfStationNumbers);
    }

    @RequestMapping(value = "/personInfo", params = { "firstName", "lastName" })
    public List<PersonInfoDTO> personInfoAlert(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
        return personService.personInfoService(firstName, lastName);
    }

    @RequestMapping(value = "/communityEmail", params = { "city" })
    public List<String> emailAlert(@RequestParam("city") String city){
        return personService.emailService(city);
    }
}
