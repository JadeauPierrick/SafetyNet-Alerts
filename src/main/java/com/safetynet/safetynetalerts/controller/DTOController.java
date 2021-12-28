package com.safetynet.safetynetalerts.controller;


import com.safetynet.safetynetalerts.DTO.*;
import com.safetynet.safetynetalerts.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DTOController {

    @Autowired
    private PersonService personService;

    @Autowired
    private MedicalRecordService medicalRecordService;


    @RequestMapping(value = "/firestation", params = { "stationNumber" })
    public ResponseEntity<PersonCoveredByItsFirestationNumberDTO> personCoveredByItsFirestationNumberDTOList(@RequestParam("stationNumber") int stationNumber){
        PersonCoveredByItsFirestationNumberDTO person = personService.personCoveredByItsFirestationNumber(stationNumber);
        if (person == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/childAlert", params = { "address" })
    public ResponseEntity<List<ChildAlertDTO>> childAlert(@RequestParam("address") String address){
        List<ChildAlertDTO> childAlertDTOList = medicalRecordService.childAlertService(address);
        if (address == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else if (childAlertDTOList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(childAlertDTOList, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/phoneAlert", params = { "firestation" })
    public ResponseEntity<List<String>> phoneAlert(@RequestParam("firestation") int firestationNumber){
        List<String> phoneList = personService.phoneAlertService(firestationNumber);
        if (phoneList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(phoneList, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/fire", params = { "address" })
    public ResponseEntity<FireDTO> fireAlert(@RequestParam("address") String address){
        FireDTO fireDTO = personService.fireAlertService(address);
        if (address == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else if (fireDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(fireDTO, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/flood/stations", params = { "stations" })
    public ResponseEntity<List<FloodDTO>> floodAlert(@RequestParam("stations") List<Integer> listOfStationNumbers){
        List<FloodDTO> floodDTOList = personService.floodByStationNumber(listOfStationNumbers);
        if (listOfStationNumbers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else if (floodDTOList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(floodDTOList, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/personInfo", params = { "firstName", "lastName" })
    public ResponseEntity<List<PersonInfoDTO>> personInfoAlert(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
        List<PersonInfoDTO> personInfoDTOList = personService.personInfoService(firstName, lastName);
        if (firstName == null || lastName == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else if (personInfoDTOList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(personInfoDTOList, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/communityEmail", params = { "city" })
    public ResponseEntity<List<String>> emailAlert(@RequestParam("city") String city){
        List<String> emailList = personService.emailService(city);
        if (city == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else if (emailList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(emailList, HttpStatus.OK);
        }
    }
}
