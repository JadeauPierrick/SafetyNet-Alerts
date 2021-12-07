package com.safetynet.safetynetalerts.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.Root;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class DataService {

    private Root root;

    public DataService() {
        this.loadData();
    }

    public List<Person> getPersons(){
        return root.getPersons();
    }

    public List<Firestation> getFirestations(){
        return root.getFirestations();
    }

    public List<MedicalRecord> getMedicalrecords(){
        return root.getMedicalrecords();
    }


    private void loadData(){
        ObjectMapper objectMapper = new ObjectMapper();


        try {
            this.root = objectMapper.readValue(new File("src/main/resources/data.json"), Root.class);
            System.out.println(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
