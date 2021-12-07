package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/person")
    public List<Person> listOfPersons(){
        return personService.displayPersons();
    }

    @GetMapping(value = "/person/{firstName}/{lastName}")
    public Person findPersonByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName){
        Person findPerson = personService.findPersonByFirstNameAndLastName(firstName, lastName);
        return findPerson;
    }

    @PostMapping(value = "/person")
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        if(person == null){
            return ResponseEntity.noContent().build();
        }else{
            Person newPerson = personService.savePerson(person);
            return ResponseEntity.ok(newPerson);
        }
    }

    @PutMapping(value = "/person/{firstName}/{lastName}")
    public Person updatePerson(@PathVariable String firstName, @PathVariable String lastName, @RequestBody Person person){
        Person ps = personService.findPersonByFirstNameAndLastName(firstName, lastName);

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
        personService.savePerson(ps);
        return ps;
    }

    @DeleteMapping(value = "/person/{firstName}/{lastName}")
    public void deletePerson(@PathVariable String firstName, @PathVariable String lastName){
        System.out.println(firstName);
        System.out.println(lastName);
        personService.deletePerson(firstName, lastName);
    }
}
