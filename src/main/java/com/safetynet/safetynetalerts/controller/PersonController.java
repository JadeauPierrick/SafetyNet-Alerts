package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/person")
    public ResponseEntity<List<Person>> listOfPersons(){
        List<Person> personList = personService.displayPersons();
        if (personList.isEmpty()){
            log.error("The person's list is empty");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            log.info("The person's list has been loaded");
            return new ResponseEntity<>(personList, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/person/{firstName}/{lastName}")
    public ResponseEntity<Person> findPersonByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName){
        Person person = personService.findPersonByFirstNameAndLastName(firstName, lastName);
        if(person == null){
            log.error("The first or the last name does not match anyone on the list");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            log.info("The person was found");
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/person")
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        if(person == null){
            log.error("Your request does not contain all the necessary fields");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            Person newPerson = personService.savePerson(person);
            log.info("The person is created");
            return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
        }
    }

    @PutMapping(value = "/person/{firstName}/{lastName}")
    public ResponseEntity<Person> updatePerson(@PathVariable String firstName, @PathVariable String lastName, @RequestBody Person person){
        if (firstName == null || lastName == null){
            log.error("The first or the last name does not match anyone on the list");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else if (person == null) {
            log.error("Your request does not contain all the necessary fields");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            Person person1 = personService.updatePerson(firstName, lastName, person);
            log.info("The person is updated");
            return new ResponseEntity<>(person1, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/person/{firstName}/{lastName}")
    public ResponseEntity<Void> deletePerson(@PathVariable String firstName, @PathVariable String lastName){
        boolean delete = personService.deletePerson(firstName, lastName);
        if (delete){
            log.info("The person is deleted");
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            log.error("The person was not deleted because the first or the last name does not match anyone on the list");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/phoneAlert", params = { "firestation" })
    public ResponseEntity<List<String>> phoneAlert(@RequestParam("firestation") Integer firestationNumber){
        List<String> phoneList = personService.phoneAlertService(firestationNumber);
        if (firestationNumber == null) {
            log.error("The fire station number is not correct");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(phoneList, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/communityEmail", params = { "city" })
    public ResponseEntity<List<String>> emailAlert(@RequestParam("city") String city){
        List<String> emailList = personService.emailService(city);
        if (city == null){
            log.error("The city is not correct");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(emailList, HttpStatus.OK);
        }
    }
}
