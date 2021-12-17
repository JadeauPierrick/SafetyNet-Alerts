package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/person")
    public ResponseEntity<List<Person>> listOfPersons(){
        List<Person> personList = personService.displayPersons();
        if (personList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(personList, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/person/{firstName}/{lastName}")
    public ResponseEntity<Person> findPersonByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName){
        Person person = personService.findPersonByFirstNameAndLastName(firstName, lastName);
        if(person == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/person")
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        if(person == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            Person newPerson = personService.savePerson(person);
            return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
        }
    }

    @PutMapping(value = "/person/{firstName}/{lastName}")
    public ResponseEntity<Person> updatePerson(@PathVariable String firstName, @PathVariable String lastName, @RequestBody Person person){
        if (firstName == null || lastName == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            Person person1 = personService.updatePerson(firstName, lastName, person);
            return new ResponseEntity<>(person1, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/person/{firstName}/{lastName}")
    public ResponseEntity<Void> deletePerson(@PathVariable String firstName, @PathVariable String lastName){
        boolean delete = personService.deletePerson(firstName, lastName);
        if (delete){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
