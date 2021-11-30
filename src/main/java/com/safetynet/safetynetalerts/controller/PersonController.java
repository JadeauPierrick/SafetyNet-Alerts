package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.DataService;
import com.safetynet.safetynetalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/person")
    public List<Person> listOfPersons(){
        return personService.displayPersons();
    }

    @GetMapping(value = "/person/{firstName}")
    public Person findPersonByFirstName(@PathVariable String firstName){
        return personService.findPersonByFirstName(firstName);
    }

    @PostMapping(value = "/person")
    public void createPerson(@RequestBody Person person){
        personService.savePerson(person);
    }
}
