package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MedicalRecordController {

    @Autowired
    private DataService dataService;

    @GetMapping(value = "medicalRecord")
    public List<MedicalRecord> listOfMedicalRecords(){
        return dataService.getMedicalrecords();
    }
}
