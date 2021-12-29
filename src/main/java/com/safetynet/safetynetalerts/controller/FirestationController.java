package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.service.FirestationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class FirestationController {

    @Autowired
    private FirestationService firestationService;

    @GetMapping(value = "/firestation")
    public ResponseEntity<List<Firestation>> listOfFirestations(){
        List<Firestation> firestationList = firestationService.displayFirestations();
        if (firestationList.isEmpty()){
            log.error("The list of fire stations is empty");
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            log.info("The list of fire stations was loaded");
            return new ResponseEntity<>(firestationList, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/firestation/{address}")
    public ResponseEntity<Firestation> findFirestationByAddress(@PathVariable String address){
        Firestation firestation = firestationService.findFirestationByAddress(address);
        if (firestation == null){
            log.error("The address does not correspond to any fire station");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            log.info("The fire station was found");
            return new ResponseEntity<>(firestation, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/firestation")
    public ResponseEntity<Firestation> createFirestation(@RequestBody Firestation firestation){
        if(firestation == null){
            log.error("Your request does not contain all the necessary fields");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            Firestation newFirestation = firestationService.saveFirestation(firestation);
            log.info("The fire station is created");
            return new ResponseEntity<>(newFirestation, HttpStatus.CREATED);
        }
    }

    @PutMapping(value = "/firestation/{address}")
    public ResponseEntity<Firestation> updateFirestation(@PathVariable String address, @RequestBody Firestation firestation) {
        if (address == null) {
            log.error("The address does not correspond to any fire station");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else if (firestation == null){
            log.error("Your request does not contain all the necessary fields");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            Firestation newFirestation = firestationService.updateFirestation(address, firestation);
            log.info("The fire station is updated");
            return new ResponseEntity<>(newFirestation, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/firestation/{address}")
    public ResponseEntity<Void> deleteFirestation(@PathVariable String address){
        boolean delete = firestationService.deleteFirestation(address);
        if (delete) {
            log.info("The fire station is deleted");
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            log.error("The fire station was not deleted because the address does not correspond to any fire station");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
