# SafetyNet Alerts
SafetyNet Alerts is an application that sends essential information to emergency services

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

## Prerequisites

What things you need to install the software and how to install them

- Java 1.8
- Maven 4.0.0
- Spring Boot 2.3.3
- Log4j

## Installing

A step by step series of examples that tell you how to get a development env running:

1.Install Java:

https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

2.Install Maven:

https://maven.apache.org/install.html


## Running App

File Json is present under the resources folder in the code base.

`src/main/resources/data.json`

Finally, you will be ready to import the code into an IDE of your choice and run the App.java to launch the application.

## Endpoints

To check all endpoints, you need to load the following file into Postman :

`src/main/resources/Postman_collection.json`

###URL
Some details on this folder :

http://localhost:8080/firestation?stationNumber=<station_number>

Returns the list of people covered by their fire station including the first and last name, the address and the phone. Moreover, the list displays the number of adults and children.

http://localhost:8080/childAlert?address=<address>

Returns a list of children and a list of adults living at the address including the first name, the last name and their age. If there are no children, it returns an empty list.

http://localhost:8080/phoneAlert?firestation=<firestation_number>

Returns a list of the phone numbers of the residents served by the fire station

http://localhost:8080/fire?address=<address>

Returns a list of people living at the address including the first and last name, the phone, their age and their medical history. Also, it displays the corresponding fire station.

http://localhost:8080/flood/stations?stations=<aListOfstation_numbers>

Returns a list of all the homes served by the fire station including the first and last name, the phone, their age and their medical history. People are grouped by address.

http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>

Returns the first and last name, the address, the age, the email and the medical history of each inhabitant.

http://localhost:8080/communityEmail?city=<city>

Returns the email of all the inhabitants of the city.

### Testing

The app has unit tests and integration tests written. 

To run the tests from maven, go to the folder that contains the pom.xml file and execute the below command.

`mvn test`
