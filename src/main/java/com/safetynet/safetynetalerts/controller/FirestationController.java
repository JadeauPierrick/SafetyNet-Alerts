package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FirestationController {

    @Autowired
    private FirestationService firestationService;

    @GetMapping(value = "/firestation")
    public ResponseEntity<List<Firestation>> listOfFirestations(){
        List<Firestation> firestationList = firestationService.displayFirestations();
        if (firestationList.isEmpty()){
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(firestationList, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/firestation/{address}")
    public ResponseEntity<Firestation> findFirestationByAddress(@PathVariable String address){
        Firestation firestation = firestationService.findFirestationByAddress(address);
        if (firestation == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(firestation, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/firestation")
    public ResponseEntity<Firestation> createFirestation(@RequestBody Firestation firestation){
        if(firestation == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            Firestation newFirestation = firestationService.saveFirestation(firestation);
            return new ResponseEntity<>(newFirestation, HttpStatus.CREATED);
        }
    }

    @PutMapping(value = "/firestation/{address}")
    public ResponseEntity<Firestation> updateFirestation(@PathVariable String address, @RequestBody Firestation firestation) {
        if (address == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            Firestation newFirestation = firestationService.updateFirestation(address, firestation);
            return new ResponseEntity<>(newFirestation, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/firestation/{address}")
    public ResponseEntity<Void> deleteFirestation(@PathVariable String address){
        boolean delete = firestationService.deleteFirestation(address);
        if (delete) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
