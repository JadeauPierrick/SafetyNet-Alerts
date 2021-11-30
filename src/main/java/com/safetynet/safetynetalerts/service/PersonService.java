package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class PersonService {

    @Autowired
    private DataService dataService;


    public List<Person> displayPersons(){
        return dataService.getPersons();
    }

    public Person findPersonByFirstName(String firstName){
        List<Person> personList = dataService.getPersons();
        for (Person person : personList) {
            if (person.getFirstName().equalsIgnoreCase(firstName)){
                System.out.println(person);
                return person;
            }
        }
        return null;
    }

    public Person savePerson(Person person){

        List<Person> personList = new ArrayList<>();
        personList = dataService.getPersons();
        personList.add(person);
        return person;
    }
}
