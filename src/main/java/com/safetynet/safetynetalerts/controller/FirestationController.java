package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FirestationController {

    @Autowired
    private DataService dataService;

    @GetMapping(value = "firestation")
    public List<Firestation> listOfFirestations(){
        return dataService.getFirestations();
    }
}
