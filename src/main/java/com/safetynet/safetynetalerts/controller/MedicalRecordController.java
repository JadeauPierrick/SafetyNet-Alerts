package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping(value = "/medicalRecord")
    public ResponseEntity<List<MedicalRecord>> listOfMedicalRecords(){
        List<MedicalRecord> medicalRecordList = medicalRecordService.displayMedicalRecords();
        if (medicalRecordList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(medicalRecordList, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/medicalRecord/{firstName}/{lastName}")
    public ResponseEntity<MedicalRecord> findMedicalRecordByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName){
        MedicalRecord medicalRecord = medicalRecordService.findMedicalRecordByFirstNameAndLastName(firstName, lastName);
        if (medicalRecord == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(medicalRecord, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/medicalRecord")
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        if (medicalRecord == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            MedicalRecord newMedicalRecord = medicalRecordService.saveMedicalRecord(medicalRecord);
            return new ResponseEntity<>(newMedicalRecord, HttpStatus.CREATED);
        }
    }

    @PutMapping(value = "/medicalRecord/{firstName}/{lastName}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable String firstName, @PathVariable String lastName, @RequestBody MedicalRecord medicalRecord){
        if (firstName == null || lastName == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            MedicalRecord medicalRecord1 = medicalRecordService.updateMedicalRecord(firstName, lastName, medicalRecord);
            return new ResponseEntity<>(medicalRecord1, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/medicalRecord/{firstName}/{lastName}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable String firstName, @PathVariable String lastName){
        boolean delete = medicalRecordService.deleteMedicalRecord(firstName, lastName);
        if (delete){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
