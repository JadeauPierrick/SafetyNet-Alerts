package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordService {

    @Autowired
    private DataService dataService;


    public List<MedicalRecord> displayMedicalRecords(){
        return dataService.getMedicalrecords();
    }

    public MedicalRecord findMedicalRecordByFirstNameAndLastName(String firstName, String lastName){
        List<MedicalRecord> medicalRecordsList = dataService.getMedicalrecords();
        Optional<MedicalRecord> medicalRecord = medicalRecordsList.stream()
                .filter(x -> x.getFirstName().equals(firstName) && x.getLastName().equals(lastName))
                .findFirst();
        return medicalRecord.get();
    }

    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord){
        List<MedicalRecord> medicalRecordsList = dataService.getMedicalrecords();
        medicalRecordsList.add(medicalRecord);
        return medicalRecord;
    }
}
