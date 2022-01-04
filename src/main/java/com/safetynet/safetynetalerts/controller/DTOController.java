package com.safetynet.safetynetalerts.controller;


import com.safetynet.safetynetalerts.DTO.*;
import com.safetynet.safetynetalerts.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DTOController {

    @Autowired
    private PersonService personService;

    @Autowired
    private MedicalRecordService medicalRecordService;


    @RequestMapping(value = "/firestation", params = { "stationNumber" })
    public ResponseEntity<PersonCoveredByItsFirestationNumberDTO> personCoveredByItsFirestationNumberDTOList(@RequestParam("stationNumber") Integer stationNumber){
        PersonCoveredByItsFirestationNumberDTO person = personService.personCoveredByItsFirestationNumber(stationNumber);
        if (person == null){
            log.error("The station number is not correct");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            log.info("Your request has been made");
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/childAlert", params = { "address" })
    public ResponseEntity<ChildAlertDTO> childAlert(@RequestParam("address") String address){
        ChildAlertDTO childAlertDTO = medicalRecordService.childAlertService(address);
        if (childAlertDTO == null){
            log.error("The address is not correct");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            log.info("Your request has been made");
            return new ResponseEntity<>(childAlertDTO, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/fire", params = { "address" })
    public ResponseEntity<FireDTO> fireAlert(@RequestParam("address") String address){
        FireDTO fireDTO = personService.fireAlertService(address);
        if (fireDTO == null){
            log.error("The address is not correct");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            log.info("Your request has been made");
            return new ResponseEntity<>(fireDTO, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/flood/stations", params = { "stations" })
    public ResponseEntity<List<FloodDTO>> floodAlert(@RequestParam("stations") List<Integer> listOfStationNumbers){
        List<FloodDTO> floodDTOList = personService.floodByStationNumber(listOfStationNumbers);
        if (floodDTOList == null){
            log.error("Your list must contain valid station numbers");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            log.info("Your request has been made");
            return new ResponseEntity<>(floodDTOList, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/personInfo", params = { "firstName", "lastName" })
    public ResponseEntity<List<PersonInfoDTO>> personInfoAlert(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
        List<PersonInfoDTO> personInfoDTOList = personService.personInfoService(firstName, lastName);
        if (personInfoDTOList == null){
            log.error("The first or the last name is not correct");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            log.info("Your request has been made");
            return new ResponseEntity<>(personInfoDTOList, HttpStatus.OK);
        }
    }
}
