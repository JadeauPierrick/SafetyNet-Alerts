package com.safetynet.safetynetalerts.model;

import lombok.Data;

import java.util.List;

@Data
public class Root {

    List<Person> persons;
    List<Firestation> firestations;
    List<MedicalRecord> medicalrecords;

    public Root(List<Person> persons, List<Firestation> firestations, List<MedicalRecord> medicalrecords) {
        this.persons = persons;
        this.firestations = firestations;
        this.medicalrecords = medicalrecords;
    }

    public Root() {
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Root{" +
                "persons=" + persons +
                ", firestations=" + firestations +
                ", medicalrecords=" + medicalrecords +
                '}';
    }
}
