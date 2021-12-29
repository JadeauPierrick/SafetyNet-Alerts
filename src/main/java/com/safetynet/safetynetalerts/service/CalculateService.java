package com.safetynet.safetynetalerts.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class CalculateService {

    public int calculateAge(final String birthday) {
        log.debug("start CalculateService");
        try{
            LocalDate birthDate = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            LocalDate currentDate = LocalDate.now();
            if(birthDate.isAfter(currentDate)) {
                log.error("Birthday after the current date");
                throw new IllegalArgumentException("BirthDay Date format");
            }
            log.debug("end CalculateService");
            return Period.between(birthDate, currentDate).getYears();
        }catch(Exception e) {
            System.out.println(birthday);
            log.error("Person format date invalid");
            throw new IllegalArgumentException("BirthDay Date format");
        }
    }
}
