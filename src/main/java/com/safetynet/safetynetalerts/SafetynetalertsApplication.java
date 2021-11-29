package com.safetynet.safetynetalerts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.service.ReadJSONFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class SafetynetalertsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SafetynetalertsApplication.class, args);

    }


}
