package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.DTO.*;
import com.safetynet.safetynetalerts.mapper.Mapper;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private DataService dataService;

    @Autowired
    private CalculateService calculateService;


    public List<Person> displayPersons() {
        return dataService.getPersons();
    }

    public Person findPersonByFirstNameAndLastName(String firstName, String lastName) {
        try {
            List<Person> personList = dataService.getPersons();
            Optional<Person> person = personList.stream()
                    .filter(x -> x.getFirstName().equals(firstName) && x.getLastName().equals(lastName))
                    .findFirst();

            return person.get();
        }catch (Exception exception){
            return null;
        }
    }

    public Person savePerson(Person person) {
        try {
            List<Person> personList = dataService.getPersons();
            personList.add(person);
            return person;
        }catch (Exception exception){
            return null;
        }
    }

    public Person updatePerson(String firstName, String lastName, Person person){
        try {
            Person ps = findPersonByFirstNameAndLastName(firstName, lastName);

            String address = person.getAddress();
            if (address != null){
                ps.setAddress(address);
            }
            String city = person.getCity();
            if (city != null){
                ps.setCity(city);
            }
            int zip = person.getZip();
            if (zip != 0){
                ps.setZip(zip);
            }
            String phone = person.getPhone();
            if (phone != null){
                ps.setPhone(phone);
            }
            String email = person.getEmail();
            if (email != null){
                ps.setEmail(email);
            }
            savePerson(ps);
            return ps;
        }catch (Exception exception){
            return null;
        }
    }

    public boolean deletePerson(String firstName, String lastName) {
        try {
            List<Person> personList = dataService.getPersons();
            Person person = findPersonByFirstNameAndLastName(firstName, lastName);

            personList.remove(person);
            return true;
        }catch (Exception exception){
            return false;
        }
    }

    public List<Person> findAllPersonsByItsFirestationNumber(int station){
        try{
            List<Firestation> firestationList = dataService.getFirestations().stream()
                    .filter(x -> x.getStation() == station)
                    .collect(Collectors.toList());

            List<Person> personList = dataService.getPersons().stream()
                    .filter(add -> firestationList.stream().map(Firestation::getAddress).anyMatch(address -> address.equals(add.getAddress())))
                    .collect(Collectors.toList());

            return personList;
        }catch (Exception exception){
            return null;
        }
    }

    public PersonCoveredByItsFirestationNumberDTO personCoveredByItsFirestationNumber(int station){
        try{
            List<Person> personList = findAllPersonsByItsFirestationNumber(station);

            List<PersonByFirestationNumberDTO> personByFirestationNumberDTOList = personList.stream()
                    .map(Mapper::toPersonByFirestationNumberDTO)
                    .collect(Collectors.toList());

            List<MedicalRecord> medicalRecordList = dataService.getMedicalrecords().stream()
                    .filter(x -> personByFirestationNumberDTOList.stream().map(PersonByFirestationNumberDTO::getFirstName).anyMatch(firstName -> firstName.equals(x.getFirstName())))
                    .filter(x -> personByFirestationNumberDTOList.stream().map(PersonByFirestationNumberDTO::getLastName).anyMatch(lastName -> lastName.equals(x.getLastName())))
                    .collect(Collectors.toList());

            int numberOfChildren = 0;

            for (MedicalRecord medicalRecord : medicalRecordList) {
                if (calculateService.calculateAge(medicalRecord.getBirthdate()) <=18){
                    numberOfChildren++;
                }
            }

            int numberOfAdults = medicalRecordList.size() - numberOfChildren;

            return new PersonCoveredByItsFirestationNumberDTO(personByFirestationNumberDTOList, numberOfAdults, numberOfChildren);
        }catch (Exception exception){
            return null;
        }
    }

    public List<String> phoneAlertService(int firestationNumber){
        try {
            List<Firestation> firestationList = dataService.getFirestations().stream()
                    .filter(x -> x.getStation() == firestationNumber)
                    .collect(Collectors.toList());

            List<String> phoneList = dataService.getPersons().stream()
                    .filter(add -> firestationList.stream().map(Firestation::getAddress).anyMatch(address -> address.equals(add.getAddress())))
                    .map(Person::getPhone)
                    .distinct()
                    .collect(Collectors.toList());

            return phoneList;
        }catch (Exception exception){
            return null;
        }
    }

    public FireDTO fireAlertService(String address){
        try {
            FireDTO fireDTO = new FireDTO();
            List<PersonInfoForFireAndFloodDTO> personInfoForFireAndFloodDTOList = new ArrayList<>();

            List<Person> personList = dataService.getPersons().stream()
                    .filter(x -> x.getAddress().equals(address))
                    .collect(Collectors.toList());

            Firestation firestation = dataService.getFirestations().stream()
                    .filter(add -> personList.stream().map(Person::getAddress).anyMatch(a -> a.equals(add.getAddress())))
                    .findFirst().get();


            personList.forEach(person ->{
                MedicalRecord medicalRecord = dataService.getMedicalrecords().stream().filter(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())).findFirst().get();
                PersonInfoForFireAndFloodDTO info = new PersonInfoForFireAndFloodDTO();
                info.setLastName(person.getLastName());
                info.setFirstName(person.getFirstName());
                info.setPhone(person.getPhone());
                info.setAge(calculateService.calculateAge(medicalRecord.getBirthdate()));
                info.setAllergies(medicalRecord.getAllergies());
                info.setMedications(medicalRecord.getMedications());
                personInfoForFireAndFloodDTOList.add(info);
            });

            fireDTO.setPersonInfoDTOList(personInfoForFireAndFloodDTOList);
            fireDTO.setStation(firestation.getStation());

            return fireDTO;
        }catch (Exception exception){
            return null;
        }
    }


    public List<FloodDTO> floodByStationNumber(List<Integer> listOfStationNumbers){
        try {
            List<FloodDTO> floodDTOList = new ArrayList<>();

            listOfStationNumbers.forEach(station ->{
                List<Person> personList = findAllPersonsByItsFirestationNumber(station);

                Map<String, List<Person>> groupedByAddress = personList.stream().collect(Collectors.groupingBy(Person::getAddress));

                FloodDTO floodDTO = new FloodDTO();
                Map<String, List<PersonInfoForFireAndFloodDTO>> personCoveredByAddress = new HashMap<>();


                groupedByAddress.forEach((address, person) ->{
                    List<PersonInfoForFireAndFloodDTO> personInfoForFireAndFloodDTOList = new ArrayList<>();
                    person.forEach(person1 -> {
                        MedicalRecord medicalRecord = dataService.getMedicalrecords().stream().filter(p -> p.getFirstName().equals(person1.getFirstName()) && p.getLastName().equals(person1.getLastName())).findFirst().get();
                        PersonInfoForFireAndFloodDTO info = new PersonInfoForFireAndFloodDTO();
                        info.setFirstName(person1.getFirstName());
                        info.setLastName(person1.getLastName());
                        info.setPhone(person1.getPhone());
                        info.setAge(calculateService.calculateAge(medicalRecord.getBirthdate()));
                        info.setAllergies(medicalRecord.getAllergies());
                        info.setMedications(medicalRecord.getMedications());
                        personInfoForFireAndFloodDTOList.add(info);
                    });

                    personCoveredByAddress.put(address, personInfoForFireAndFloodDTOList);
                });

                floodDTO.setPersonCoveredByAddress(personCoveredByAddress);
                floodDTO.setStation(station);

                floodDTOList.add(floodDTO);
            });

            return floodDTOList;
        }catch (Exception exception){
            return null;
        }
    }

    public List<PersonInfoDTO> personInfoService(String firstName, String lastName){
        try {
            List<PersonInfoDTO> personInfoDTOList = new ArrayList<>();

            List<Person> personList = dataService.getPersons().stream()
                    .filter(x -> x.getFirstName().equals(firstName) && x.getLastName().equals(lastName))
                    .collect(Collectors.toList());

            personList.forEach(person -> {
                MedicalRecord medicalRecord = dataService.getMedicalrecords().stream().filter(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())).findFirst().get();
                PersonInfoDTO personInfoDTO = new PersonInfoDTO();
                personInfoDTO.setFirstName(person.getFirstName());
                personInfoDTO.setLastName(person.getLastName());
                personInfoDTO.setAddress(person.getAddress());
                personInfoDTO.setAge(calculateService.calculateAge(medicalRecord.getBirthdate()));
                personInfoDTO.setEmail(person.getEmail());
                personInfoDTO.setMedications(medicalRecord.getMedications());
                personInfoDTO.setAllergies(medicalRecord.getAllergies());
                personInfoDTOList.add(personInfoDTO);
            });


            return personInfoDTOList;
        }catch (Exception exception){
            return null;
        }
    }

    public List<String> emailService(String city){
        try {
            List<String> emailList = dataService.getPersons().stream()
                    .filter(x -> x.getCity().equals(city))
                    .map(Person::getEmail)
                    .distinct()
                    .collect(Collectors.toList());

            return emailList;
        }catch (Exception exception){
            return null;
        }
    }
}