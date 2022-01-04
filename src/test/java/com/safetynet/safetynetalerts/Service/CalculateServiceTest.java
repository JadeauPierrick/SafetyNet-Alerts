package com.safetynet.safetynetalerts.Service;

import com.safetynet.safetynetalerts.service.CalculateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculateServiceTest {

    @Autowired
    private CalculateService calculateService;

    @Test
    public void testCalculteAge(){
        int age = calculateService.calculateAge("01/12/2010");

        assertThat(age).isEqualTo(11);
    }
}
