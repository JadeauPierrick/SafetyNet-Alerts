package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.DTO.PersonByFirestationNumberDTO;
import com.safetynet.safetynetalerts.DTO.PersonCoveredByItsFirestationNumberDTO;
import com.safetynet.safetynetalerts.mapper.Mapper;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
        List<Person> personList = dataService.getPersons();
        Optional<Person> person = personList.stream()
                .filter(x -> x.getFirstName().equals(firstName) && x.getLastName().equals(lastName))
                .findFirst();

        return person.get();
    }

    public Person savePerson(Person person) {
        List<Person> personList = dataService.getPersons();
        personList.add(person);
        return person;
    }

    public void deletePerson(String firstName, String lastName) {
        List<Person> personList = dataService.getPersons();
        Person person = personList.stream()
                .filter(x -> x.getFirstName().equals(firstName) && x.getLastName().equals(lastName))
                .findFirst()
                .get();

        personList.remove(person);
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

}