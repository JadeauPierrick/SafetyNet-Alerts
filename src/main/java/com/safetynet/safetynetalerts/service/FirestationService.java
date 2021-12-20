package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.DTO.FloodDTO;
import com.safetynet.safetynetalerts.model.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirestationService {

    @Autowired
    private DataService dataService;


    public List<Firestation> displayFirestations() {
        return dataService.getFirestations();
    }

    public Firestation findFirestationByAddress(String address) {
        try {
            List<Firestation> firestationList = dataService.getFirestations();
            Firestation firestation = firestationList.stream()
                    .filter(x -> x.getAddress().equals(address))
                    .findFirst()
                    .get();

            return firestation;
        } catch (Exception exception) {
            return null;
        }
    }

    public Firestation saveFirestation(Firestation firestation) {
        try {
            List<Firestation> firestationList = dataService.getFirestations();
            firestationList.add(firestation);
            return firestation;
        }catch (Exception exception){
            return null;
        }
    }

    public Firestation updateFirestation(String address, Firestation firestation) {
        try {
            Firestation fs = findFirestationByAddress(address);

            int station = firestation.getStation();
            if (station != 0) {
                fs.setStation(station);
            }
            saveFirestation(fs);
            return fs;
        } catch (Exception exception) {
            return null;
        }
    }

    public boolean deleteFirestation(String address) {
        try {
            List<Firestation> firestationList = dataService.getFirestations();
            Firestation firestation = firestationList.stream()
                    .filter(x -> x.getAddress().equals(address))
                    .findFirst()
                    .get();

            firestationList.remove(firestation);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

}


