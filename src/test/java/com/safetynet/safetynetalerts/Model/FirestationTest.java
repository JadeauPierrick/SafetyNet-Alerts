package com.safetynet.safetynetalerts.Model;

import com.safetynet.safetynetalerts.model.Firestation;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FirestationTest {

    private Firestation firestation;

    @Test
    public void testGetAddress(){
        String address = "11 Campbell St";

        firestation = new Firestation();
        firestation.setAddress(address);

        assertThat(firestation.getAddress()).isEqualTo(address);
    }

    @Test
    public void testGetStation(){
        int station = 2;

        firestation = new Firestation();
        firestation.setStation(station);

        assertThat(firestation.getStation()).isEqualTo(station);
    }

    @Test
    public void testFirestation(){
        firestation = new Firestation("11 Campbell St",2);

        assertThat(firestation.getAddress()).isEqualTo("11 Campbell St");
        assertThat(firestation.getStation()).isEqualTo(2);
    }

    @Test
    public void equalsContract(){
        EqualsVerifier.simple().forClass(Firestation.class).verify();
    }
}
