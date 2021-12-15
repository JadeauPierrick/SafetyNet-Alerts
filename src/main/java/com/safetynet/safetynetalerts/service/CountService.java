package com.safetynet.safetynetalerts.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountService {

    @Autowired
    private DataService dataService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private CalculateService calculateService;

}
