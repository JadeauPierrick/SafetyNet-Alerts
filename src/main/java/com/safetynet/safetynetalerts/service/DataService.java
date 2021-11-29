package com.safetynet.safetynetalerts.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class DataService {

    private ReadJSONFile readJSONFile;

    public DataService() {
        this.loadData();
    }

    public List<Person> getPersons(){
        return readJSONFile.getPersons();
    }

    public List<Firestation> getFirestations(){
        return readJSONFile.getFirestations();
    }

    public List<MedicalRecord> getMedicalrecords(){
        return readJSONFile.getMedicalrecords();
    }


    private void loadData(){
        ObjectMapper objectMapper = new ObjectMapper();


        try {
            this.readJSONFile = objectMapper.readValue(new File("src/main/resources/data.json"), ReadJSONFile.class);
            System.out.println(readJSONFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
