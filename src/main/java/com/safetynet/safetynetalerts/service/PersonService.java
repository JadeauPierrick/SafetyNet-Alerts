package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.DTO.FireDTO;
import com.safetynet.safetynetalerts.DTO.PersonByFirestationNumberDTO;
import com.safetynet.safetynetalerts.DTO.PersonCoveredByItsFirestationNumberDTO;
import com.safetynet.safetynetalerts.DTO.PersonInfoForFireDTO;
import com.safetynet.safetynetalerts.mapper.Mapper;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private DataService dataService;

    @Autowired
    private CalculateService calculateService;


    public List<Person> displayPersons() {
        return dataService.getPersons();
    }

    public Person findPersonByFirstNameAndLastName(String firstName, String lastName) {
        try {
            List<Person> personList = dataService.getPersons();
            Optional<Person> person = personList.stream()
                    .filter(x -> x.getFirstName().equals(firstName) && x.getLastName().equals(lastName))
                    .findFirst();

            return person.get();
        }catch (Exception exception){
            return null;
        }
    }

    public Person savePerson(Person person) {
        try {
            List<Person> personList = dataService.getPersons();
            personList.add(person);
            return person;
        }catch (Exception exception){
            return null;
        }
    }

    public Person updatePerson(String firstName, String lastName, Person person){
        try {
            Person ps = findPersonByFirstNameAndLastName(firstName, lastName);

            String address = person.getAddress();
            if (address != null){
                ps.setAddress(address);
            }
            String city = person.getCity();
            if (city != null){
                ps.setCity(city);
            }
            int zip = person.getZip();
            if (zip != 0){
                ps.setZip(zip);
            }
            String phone = person.getPhone();
            if (phone != null){
                ps.setPhone(phone);
            }
            String email = person.getEmail();
            if (email != null){
                ps.setEmail(email);
            }
            savePerson(ps);
            return ps;
        }catch (Exception exception){
            return null;
        }
    }

    public boolean deletePerson(String firstName, String lastName) {
        try {
            List<Person> personList = dataService.getPersons();
            Person person = personList.stream()
                    .filter(x -> x.getFirstName().equals(firstName) && x.getLastName().equals(lastName))
                    .findFirst()
                    .get();

            personList.remove(person);
            return true;
        }catch (Exception exception){
            return false;
        }
    }

    public PersonCoveredByItsFirestationNumberDTO personCoveredByItsFirestationNumber(int station){
        List<Firestation> firestationList = dataService.getFirestations().stream()
                .filter(x -> x.getStation() == station)
                .collect(Collectors.toList());

        List<PersonByFirestationNumberDTO> personList = dataService.getPersons().stream()
                .filter(add -> firestationList.stream().map(Firestation::getAddress).anyMatch(address -> address.equals(add.getAddress())))
                .map(Mapper::toPersonByFirestationNumberDTO)
                .collect(Collectors.toList());

        List<MedicalRecord> medicalRecordList = dataService.getMedicalrecords().stream()
                .filter(x -> personList.stream().map(PersonByFirestationNumberDTO::getFirstName).anyMatch(firstName -> firstName.equals(x.getFirstName())))
                .filter(x -> personList.stream().map(PersonByFirestationNumberDTO::getLastName).anyMatch(lastName -> lastName.equals(x.getLastName())))
                .collect(Collectors.toList());

        int numberOfChildren = 0;

        for (MedicalRecord medicalRecord : medicalRecordList) {
            if (calculateService.calculateAge(medicalRecord.getBirthdate()) <=18){
                numberOfChildren++;
            }
        }

        int numberOfAdults = medicalRecordList.size() - numberOfChildren;

        return new PersonCoveredByItsFirestationNumberDTO(personList, numberOfAdults, numberOfChildren);
    }

    public List<String> phoneAlertService(int firestationNumber){
        List<Firestation> firestationList = dataService.getFirestations().stream()
                .filter(x -> x.getStation() == firestationNumber)
                .collect(Collectors.toList());

        List<String> phoneList = dataService.getPersons().stream()
                .filter(add -> firestationList.stream().map(Firestation::getAddress).anyMatch(address -> address.equals(add.getAddress())))
                .map(Person::getPhone)
                .distinct()
                .collect(Collectors.toList());

        return phoneList;
    }

    public FireDTO fireAlertService(String address){
        FireDTO fireDTO = new FireDTO();
        List<PersonInfoForFireDTO> personInfoForFireDTOList = new ArrayList<>();

        List<Person> personList = dataService.getPersons().stream()
                .filter(x -> x.getAddress().equals(address))
                .collect(Collectors.toList());

        Firestation firestation = dataService.getFirestations().stream()
                .filter(add -> personList.stream().map(Person::getAddress).anyMatch(a -> a.equals(add.getAddress())))
                .findFirst().get();

        List<MedicalRecord> medicalRecordList = dataService.getMedicalrecords().stream()
                .filter(p -> personList.stream().map(Person::getFirstName).anyMatch(firstName -> firstName.equals(p.getFirstName())))
                .filter(p -> personList.stream().map(Person::getLastName).anyMatch(lastName -> lastName.equals(p.getLastName())))
                .collect(Collectors.toList());

        personList.forEach(person ->{
            MedicalRecord medicalRecord = medicalRecordList.stream().filter(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())).findFirst().get();
            PersonInfoForFireDTO info = new PersonInfoForFireDTO();
            info.setLastName(person.getLastName());
            info.setFirstName(person.getFirstName());
            info.setPhone(person.getPhone());
            info.setAge(calculateService.calculateAge(medicalRecord.getBirthdate()));
            info.setAllergies(medicalRecord.getAllergies());
            info.setMedications(medicalRecord.getMedications());
            personInfoForFireDTOList.add(info);
        });

            fireDTO.setPersonInfoForFireDTOList(personInfoForFireDTOList);
            fireDTO.setStation(firestation.getStation());

        return fireDTO;

    }

}