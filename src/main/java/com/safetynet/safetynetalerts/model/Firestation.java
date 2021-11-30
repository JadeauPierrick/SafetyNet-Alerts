package com.safetynet.safetynetalerts.model;

import lombok.Data;

@Data
public class Firestation {

    private String address;
    private int station;

    public Firestation(String address, int station) {
        this.address = address;
        this.station = station;
    }

    public Firestation(){}

}
