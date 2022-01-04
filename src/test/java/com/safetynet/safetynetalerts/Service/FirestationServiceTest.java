package com.safetynet.safetynetalerts.Service;

import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.service.DataService;
import com.safetynet.safetynetalerts.service.FirestationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class FirestationServiceTest {

    @Autowired
    private FirestationService firestationService;

    @MockBean
    private DataService dataService;

    @BeforeEach
    private void setUp(){
        List<Firestation> firestationList = new ArrayList<>();
        firestationList.add(new Firestation("11 Silver St",1));
        firestationList.add(new Firestation("87 Maine St",2));
        when(dataService.getFirestations()).thenReturn(firestationList);
    }

    @Test
    public void findFirestationByAddress(){
        Firestation firestation = firestationService.findFirestationByAddress("11 Silver St");

        assertThat(firestation.getAddress()).isEqualTo("11 Silver St");
        assertThat(firestation.getStation()).isEqualTo(1);
    }

    @Test
    public void saveFirestation(){
        Firestation firestation = firestationService.saveFirestation(new Firestation("45 Oslo St",4));

        assertThat(firestationService.displayFirestations().size()).isEqualTo(3);
        assertThat(firestationService.displayFirestations().get(0).getAddress()).isEqualTo("11 Silver St");
        assertThat(firestationService.displayFirestations().get(0).getStation()).isEqualTo(1);
        assertThat(firestationService.displayFirestations().get(1).getAddress()).isEqualTo("87 Maine St");
        assertThat(firestationService.displayFirestations().get(1).getStation()).isEqualTo(2);
        assertThat(firestationService.displayFirestations().get(2).getStation()).isEqualTo(firestation.getStation());
        assertThat(firestationService.displayFirestations().get(2).getAddress()).isEqualTo(firestation.getAddress());
    }

    @Test
    public void updateFirestation(){
        Firestation firestation = firestationService.updateFirestation("11 Silver St",new Firestation("11 Silver St", 8));

        assertThat(firestationService.displayFirestations().size()).isEqualTo(2);
        assertThat(firestationService.displayFirestations().get(0).getAddress()).isEqualTo(firestation.getAddress());
        assertThat(firestationService.displayFirestations().get(0).getStation()).isEqualTo(firestation.getStation());
    }

    @Test
    public void deleteFirestation(){
        boolean delete = firestationService.deleteFirestation("87 Maine St");

        assertThat(firestationService.displayFirestations().size()).isEqualTo(1);
        assertThat(firestationService.displayFirestations().get(0).getAddress()).isEqualTo("11 Silver St");
        assertThat(firestationService.displayFirestations().get(0).getStation()).isEqualTo(1);
        assertThat(delete).isEqualTo(true);
    }
}
