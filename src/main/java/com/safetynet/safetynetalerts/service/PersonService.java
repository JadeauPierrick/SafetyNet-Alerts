package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Person;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private DataService dataService;


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
}