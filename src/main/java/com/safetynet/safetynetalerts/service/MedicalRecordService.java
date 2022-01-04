package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.DTO.AdultsInHouseDTO;
import com.safetynet.safetynetalerts.DTO.ChildAlertDTO;
import com.safetynet.safetynetalerts.DTO.ChildrenInHouseDTO;
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


    public List<MedicalRecord> displayMedicalRecords() {
        return dataService.getMedicalrecords();
    }

    public MedicalRecord findMedicalRecordByFirstNameAndLastName(String firstName, String lastName) {
        List<MedicalRecord> medicalRecordsList = dataService.getMedicalrecords();
        Optional<MedicalRecord> medicalRecord = medicalRecordsList.stream()
                .filter(x -> x.getFirstName().equals(firstName) && x.getLastName().equals(lastName))
                .findFirst();

        return medicalRecord.orElse(null);
    }

    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
        if (medicalRecord != null) {
            List<MedicalRecord> medicalRecordsList = dataService.getMedicalrecords();
            medicalRecordsList.add(medicalRecord);
            return medicalRecord;
        } else {
            return null;
        }
    }

    public MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord) {
        MedicalRecord md = findMedicalRecordByFirstNameAndLastName(firstName, lastName);
        if (md != null) {
            String birthdate = medicalRecord.getBirthdate();
            if (birthdate != null){
                md.setBirthdate(birthdate);
            }
            List<String> medications = medicalRecord.getMedications();
            if (medications != null) {
                md.setMedications(medications);
            }
            List<String> allergies = medicalRecord.getAllergies();
            if (allergies != null) {
                md.setAllergies(allergies);
            }
            return md;
        } else {
            return null;
        }
    }

    public boolean deleteMedicalRecord(String firstName, String lastName) {
        List<MedicalRecord> medicalRecordsList = dataService.getMedicalrecords();
        MedicalRecord md = findMedicalRecordByFirstNameAndLastName(firstName, lastName);
        if (md != null) {
            medicalRecordsList.remove(md);
            return true;
        }
        return false;
    }

    public ChildAlertDTO childAlertService(String address) {
        List<Person> personList = dataService.getPersons().stream()
                    .filter(ad -> ad.getAddress().equals(address))
                    .collect(Collectors.toList());

        if (personList.isEmpty()) {
            return null;
        }else {
            ChildAlertDTO childAlertDTO = new ChildAlertDTO();
            List<AdultsInHouseDTO> adultsInHouseDTOList = new ArrayList<>();
            List<ChildrenInHouseDTO> childrenInHouseDTOList = new ArrayList<>();

            personList.forEach(person -> {
                MedicalRecord medicalRecord = dataService.getMedicalrecords().stream().filter(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())).findFirst().get();
                AdultsInHouseDTO adults = new AdultsInHouseDTO();
                ChildrenInHouseDTO children = new ChildrenInHouseDTO();
                int age = calculateService.calculateAge(medicalRecord.getBirthdate());
                if (age <= 18) {
                    children.setFirstName(person.getFirstName());
                    children.setLastName(person.getLastName());
                    children.setAge(age);
                    childrenInHouseDTOList.add(children);
                } else {
                    adults.setFirstName(person.getFirstName());
                    adults.setLastName(person.getLastName());
                    adults.setAge(age);
                    adultsInHouseDTOList.add(adults);
                }

            });

            if (childrenInHouseDTOList.isEmpty()) {
                return childAlertDTO;
            } else {
                childAlertDTO.setChildren(childrenInHouseDTOList);
                childAlertDTO.setAdults(adultsInHouseDTOList);
                return childAlertDTO;
            }
        }
    }
}