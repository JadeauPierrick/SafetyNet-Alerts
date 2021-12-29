package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping(value = "/medicalRecord")
    public ResponseEntity<List<MedicalRecord>> listOfMedicalRecords(){
        List<MedicalRecord> medicalRecordList = medicalRecordService.displayMedicalRecords();
        if (medicalRecordList.isEmpty()){
            log.error("The list of medical records is empty");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            log.info("The list of medical records has been loaded");
            return new ResponseEntity<>(medicalRecordList, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/medicalRecord/{firstName}/{lastName}")
    public ResponseEntity<MedicalRecord> findMedicalRecordByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName){
        MedicalRecord medicalRecord = medicalRecordService.findMedicalRecordByFirstNameAndLastName(firstName, lastName);
        if (medicalRecord == null){
            log.error("The first or the last name does not correspond to any medical record");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            log.info("The medical record was found");
            return new ResponseEntity<>(medicalRecord, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/medicalRecord")
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        if (medicalRecord == null){
            log.error("Your request does not contain all the necessary fields");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            MedicalRecord newMedicalRecord = medicalRecordService.saveMedicalRecord(medicalRecord);
            log.info("The medical record is created");
            return new ResponseEntity<>(newMedicalRecord, HttpStatus.CREATED);
        }
    }

    @PutMapping(value = "/medicalRecord/{firstName}/{lastName}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable String firstName, @PathVariable String lastName, @RequestBody MedicalRecord medicalRecord){
        if (firstName == null || lastName == null) {
            log.error("The first or the last name does not correspond to any medical record");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else if (medicalRecord == null){
            log.error("Your request does not contain all the necessary fields");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            MedicalRecord medicalRecord1 = medicalRecordService.updateMedicalRecord(firstName, lastName, medicalRecord);
            log.info("The medical record is updated");
            return new ResponseEntity<>(medicalRecord1, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/medicalRecord/{firstName}/{lastName}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable String firstName, @PathVariable String lastName){
        boolean delete = medicalRecordService.deleteMedicalRecord(firstName, lastName);
        if (delete){
            log.info("The medical record is deleted");
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            log.error("The medical record was not deleted because the first or the last name does not correspond to any medical record");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
