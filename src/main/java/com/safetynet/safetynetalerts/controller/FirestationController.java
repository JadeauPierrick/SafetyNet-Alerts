package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FirestationController {

    @Autowired
    private FirestationService firestationService;

    @GetMapping(value = "/firestation")
    public List<Firestation> listOfFirestations(){
        return firestationService.displayFirestations();
    }

    @GetMapping(value = "/firestation/{address}")
    public Firestation findFirestationByAddress(@PathVariable String address){
        return firestationService.findFirestationByAddress(address);
    }

    @PostMapping(value = "/firestation")
    public ResponseEntity<Firestation> createFirestation(@RequestBody Firestation firestation){
        if(firestation == null){
            return ResponseEntity.noContent().build();
        }else{
            Firestation newFirestation = firestationService.saveFirestation(firestation);
            return ResponseEntity.ok(newFirestation);
        }
    }

    @PutMapping(value = "/firestation/{address}")
    public Firestation updateFirestation(@PathVariable String address, @RequestBody Firestation firestation){
        Firestation fs = firestationService.findFirestationByAddress(address);

        int station = firestation.getStation();
        if (station != 0){
            fs.setStation(station);
        }
        firestationService.saveFirestation(fs);
        return fs;
    }

    @DeleteMapping(value = "/firestation/{address}")
    public void deleteFirestation(@PathVariable String address){
            firestationService.deleteFirestation(address);
    }
}
