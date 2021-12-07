package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FirestationService {

    @Autowired
    private DataService dataService;


    public List<Firestation> displayFirestations() {
        return dataService.getFirestations();
    }

    public Firestation findFirestationByAddress(String address) {
        List<Firestation> firestationList = dataService.getFirestations();
        Optional<Firestation> firestation = firestationList.stream()
                .filter(x -> x.getAddress().equals(address))
                .findFirst();

        return firestation.get();
    }

    public Firestation saveFirestation(Firestation firestation) {
        List<Firestation> firestationList = dataService.getFirestations();
        firestationList.add(firestation);
        return firestation;
    }

    public void deleteFirestation(String address) {
        List<Firestation> firestationList = dataService.getFirestations();
        Firestation firestation = firestationList.stream()
                .filter(x -> x.getAddress().equals(address))
                .findFirst()
                .get();

        firestationList.remove(firestation);
    }
}


