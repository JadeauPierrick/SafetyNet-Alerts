package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.Person;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

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
        Person[] personArray = personList.toArray(new Person[personList.size()]);
        for (Person person : personArray) {
            //System.out.println(person);
            if ((person.getFirstName().equals(firstName)) && (person.getLastName().equals(lastName))){
                System.out.println(person);
                personArray = ArrayUtils.removeElement(personArray, person);
            }
        }
    }
}