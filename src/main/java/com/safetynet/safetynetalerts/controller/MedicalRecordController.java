package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping(value = "/medicalRecord")
    public List<MedicalRecord> listOfMedicalRecords(){
        return medicalRecordService.displayMedicalRecords();
    }

    @GetMapping(value = "/medicalRecord/{firstName}/{lastName}")
    public MedicalRecord findMedicalRecordByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName){
        return medicalRecordService.findMedicalRecordByFirstNameAndLastName(firstName, lastName);
    }

    @PostMapping(value = "/medicalRecord")
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        if (medicalRecord == null){
            return ResponseEntity.notFound().build();
        }else {
            MedicalRecord newMedicalRecord = medicalRecordService.saveMedicalRecord(medicalRecord);
            return ResponseEntity.ok(newMedicalRecord);
        }
    }

    @PutMapping(value = "/medicalRecord/{firstName}/{lastName}")
    public MedicalRecord updateMedicalRecord(@PathVariable String firstName, @PathVariable String lastName, @RequestBody MedicalRecord medicalRecord){
        MedicalRecord md = medicalRecordService.findMedicalRecordByFirstNameAndLastName(firstName, lastName);

        List<String> medications = medicalRecord.getMedications();
        if (medications != null){
            md.setMedications(medications);
        }
        List<String> allergies = medicalRecord.getAllergies();
        if (allergies != null){
            md.setAllergies(allergies);
        }
        medicalRecordService.saveMedicalRecord(md);
        return md;
    }

    @DeleteMapping(value = "/medicalRecord/{firstName}/{lastName}")
    public void deleteMedicalRecord(@PathVariable String firstName, @PathVariable String lastName){
        medicalRecordService.deleteMedicalRecord(firstName, lastName);
    }
}
