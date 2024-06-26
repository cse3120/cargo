# Cargo API 

## Tools and Technologies Used

Java        -  8  
Spring Boot - v2.5.4  
Junit       - 4.13.2 

## API Details

(1) Submit aircraft flight details.

 Endpoint (POST): [http://localhost:8080/api/flights/](http://localhost:8080/api)

 sample request:
```
 {
     "flightNumber": "FL125",
     "arrivalDateTime": "2024-06-18T18:30:00",
     "airportCode": "LGW"   
 }
 ```
(2) Supply details of the cargo onboard the flight.

Endpoint (POST): [http://localhost:8080/api/cargos/flight/{flightNumber}](http://localhost:8080/api)

sample request:
[http://localhost:8080/api/cargos/flight/FL123](http://localhost:8080/api/cargos/flight/FL123)
```
{
    "cargoNumber": "CG123",
    "weight": 876.45,
    "cargoStatus": "NOT_ARRIVED"
 }
 ```
 
(3) Update the status of the cargo from “not arrived” to “arrived” when the flight lands.

 Endpoint (PUT): [http://localhost:8080/api/flights/{flightNumber}/landed](http://localhost:8080/api) 
 
 sample request:
 [http://localhost:8080/api/flights/FL123/landed](http://localhost:8080/api/cargos/flight/FL123)

## Swagger

Implemented Open API (Swagger) for this API,
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) 

## Implementation Details

1. Used Spring Boot for rapid development.
2. Simplified data access using Spring Data JPA, which provides an abstraction over JPA, reducing boilerplate code.
3. Utilizes Javax Validation annotations to ensure data integrity by validating fields and objects.
4. Implemented error management using @ControllerAdvice.
5. Implemented JUnit test cases to verify the functionality of the application.

