package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.DTO.ChildAlertDTO;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicalRecordService {

    @Autowired
    private DataService dataService;

    @Autowired
    private CalculateService calculateService;


    public List<MedicalRecord> displayMedicalRecords(){
        return dataService.getMedicalrecords();
    }

    public MedicalRecord findMedicalRecordByFirstNameAndLastName(String firstName, String lastName) {
        try {
            List<MedicalRecord> medicalRecordsList = dataService.getMedicalrecords();
            Optional<MedicalRecord> medicalRecord = medicalRecordsList.stream()
                    .filter(x -> x.getFirstName().equals(firstName) && x.getLastName().equals(lastName))
                    .findFirst();
            return medicalRecord.get();
        } catch (Exception exception) {
            return null;
        }
    }

    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord){
        try {
            List<MedicalRecord> medicalRecordsList = dataService.getMedicalrecords();
            medicalRecordsList.add(medicalRecord);
            return medicalRecord;
        }catch (Exception exception){
            return null;
        }
    }

    public MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord){
        try {
            MedicalRecord md = findMedicalRecordByFirstNameAndLastName(firstName, lastName);

            List<String> medications = medicalRecord.getMedications();
            if (medications != null){
                md.setMedications(medications);
            }
            List<String> allergies = medicalRecord.getAllergies();
            if (allergies != null){
                md.setAllergies(allergies);
            }
            saveMedicalRecord(md);
            return md;
        }catch (Exception exception){
            return null;
        }
    }

    public boolean deleteMedicalRecord(String firstName, String lastName){
        try {
            List<MedicalRecord> medicalRecordsList = dataService.getMedicalrecords();
            MedicalRecord medicalRecord = medicalRecordsList.stream()
                    .filter(x -> x.getFirstName().equals(firstName) && x.getLastName().equals(lastName))
                    .findFirst()
                    .get();

            medicalRecordsList.remove(medicalRecord);
            return true;
        }catch (Exception exception){
            return false;
        }
    }

    public List<ChildAlertDTO> childAlertService(String address){
        List<ChildAlertDTO> childAlertDTOList = new ArrayList<>();

        List<Person> personList = dataService.getPersons().stream()
                .filter(ad -> ad.getAddress().equals(address))
                .collect(Collectors.toList());


        List<MedicalRecord> mdList = dataService.getMedicalrecords().stream()
                .filter(x -> personList.stream().map(Person::getFirstName).anyMatch(firstName -> firstName.equals(x.getFirstName())))
                .filter(x -> personList.stream().map(Person::getLastName).anyMatch(lastName -> lastName.equals(x.getLastName())))
                .collect(Collectors.toList());

        for (MedicalRecord medicalRecord : mdList) {
            childAlertDTOList.add(new ChildAlertDTO(medicalRecord.getFirstName(), medicalRecord.getLastName(), calculateService.calculateAge(medicalRecord.getBirthdate())));
        }

        return childAlertDTOList;

    }
}
