package com.safetynet.safetynetalerts.Model;

import com.safetynet.safetynetalerts.model.Person;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PersonTest {

    private Person person;

    @Test
    public void testGetFirstName(){
        String firstName = "Jacob";

        person = new Person();
        person.setFirstName(firstName);

        assertThat(person.getFirstName()).isEqualTo(firstName);
    }

    @Test
    public void testGetLastName(){
        String lastName = "Dale";

        person = new Person();
        person.setLastName(lastName);

        assertThat(person.getLastName()).isEqualTo(lastName);
    }

    @Test
    public void testGetAddress(){
        String address = "11 Campbell St";

        person = new Person();
        person.setAddress(address);

        assertThat(person.getAddress()).isEqualTo(address);
    }

    @Test
    public void testGetCity(){
        String city = "Culver";

        person = new Person();
        person.setCity(city);

        assertThat(person.getCity()).isEqualTo(city);
    }

    @Test
    public void testGetZip(){
        int zip = 99999;

        person = new Person();
        person.setZip(zip);

        assertThat(person.getZip()).isEqualTo(zip);
    }

    @Test
    public void testGetPhone(){
        String phone = "999-999-999";

        person = new Person();
        person.setPhone(phone);

        assertThat(person.getPhone()).isEqualTo(phone);
    }

    @Test
    public void testGetEmail(){
        String email = "e@email.com";

        person = new Person();
        person.setEmail(email);

        assertThat(person.getEmail()).isEqualTo(email);
    }

    @Test
    public void testPerson(){
        person = new Person("Jacob","Dale","11 Campbell St","Culver",99999,"999-999-999","e@email.com");

        assertThat(person.getFirstName()).isEqualTo("Jacob");
        assertThat(person.getLastName()).isEqualTo("Dale");
        assertThat(person.getAddress()).isEqualTo("11 Campbell St");
        assertThat(person.getCity()).isEqualTo("Culver");
        assertThat(person.getZip()).isEqualTo(99999);
        assertThat(person.getPhone()).isEqualTo("999-999-999");
        assertThat(person.getEmail()).isEqualTo("e@email.com");
    }

    @Test
    public void equalsContract(){
        EqualsVerifier.simple().forClass(Person.class).verify();
    }
}
