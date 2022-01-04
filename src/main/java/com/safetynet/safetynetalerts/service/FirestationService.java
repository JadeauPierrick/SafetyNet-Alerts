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

        return firestation.orElse(null);
    }

    public Firestation saveFirestation(Firestation firestation) {
        if (firestation != null){
            List<Firestation> firestationList = dataService.getFirestations();
            firestationList.add(firestation);
            return firestation;
        }else {
            return null;
        }
    }

    public Firestation updateFirestation(String address, Firestation firestation) {
        Firestation fs = findFirestationByAddress(address);
        if (fs != null){
            int station = firestation.getStation();
            if (station != 0) {
                fs.setStation(station);
            }
            return fs;
        }else {
            return null;
        }
    }

    public boolean deleteFirestation(String address) {
        List<Firestation> firestationList = dataService.getFirestations();
        Firestation fs = findFirestationByAddress(address);
        if (fs != null){
            firestationList.remove(fs);
            return true;
        }
        return false;
    }
}


