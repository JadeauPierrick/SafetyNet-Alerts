package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.DTO.PersonByFirestationNumberDTO;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountService {

    @Autowired
    private DataService dataService;

    @Autowired
    private CalculateService calculateService;

    public int numberOfChildren(List<PersonByFirestationNumberDTO> dataList){
        List<MedicalRecord> medicalRecordList = dataService.getMedicalrecords().stream()
                .filter(x -> dataList.stream().map(PersonByFirestationNumberDTO::getFirstName).anyMatch(firstName -> firstName.equals(x.getFirstName())))
                .filter(x -> dataList.stream().map(PersonByFirestationNumberDTO::getLastName).anyMatch(lastName -> lastName.equals(x.getLastName())))
                .collect(Collectors.toList());

        int total = 0;

        for (MedicalRecord medicalRecord : medicalRecordList) {
            if (calculateService.calculateAge(medicalRecord.getBirthdate()) <=18){
                total = +1;
            }
        }
        return total;
    }
}
