package com.example.cargo.service;

import com.example.cargo.entity.Cargo;
import com.example.cargo.entity.CargoStatus;
import com.example.cargo.entity.Flight;
import com.example.cargo.exception.DuplicateEntityException;
import com.example.cargo.repository.CargoRepository;
import com.example.cargo.repository.FlightRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CargoServiceTest {
    @Autowired
    private CargoService cargoService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Test(expected = DuplicateEntityException.class)
    public void testCreateDuplicateCargo() {
        Flight flight1 = new Flight();
        flight1.setFlightNumber("FL123");
        flight1.setArrivalDateTime(LocalDateTime.now().plusHours(5));
        flight1.setAirportCode("LHR");
        flightService.createFlight(flight1);
        Cargo cargo1=new Cargo();
        cargo1.setFlight(flight1);
        cargo1.setCargoNumber("CG123");
        cargo1.setWeight(734.24);
        cargo1.setCargoStatus(CargoStatus.NOT_ARRIVED);
        cargoService.createCargo(cargo1);

        Flight flight2 = new Flight();
        flight2.setFlightNumber("FL123");
        flight2.setArrivalDateTime(LocalDateTime.now().plusHours(6));
        flight2.setAirportCode("LGW");
        flightService.createFlight(flight1);
        Cargo cargo2=new Cargo();
        cargo2.setFlight(flight2);
        cargo2.setCargoNumber("CG234");
        cargo2.setWeight(458.24);
        cargo2.setCargoStatus(CargoStatus.NOT_ARRIVED);
        cargoService.createCargo(cargo2);
    }

    @Test
    public void testAddCargoToFlight(){
        Flight flight1 = new Flight();
        flight1.setFlightNumber("FL456");
        flight1.setArrivalDateTime(LocalDateTime.now().plusHours(5));
        flight1.setAirportCode("LHR");
        flightService.createFlight(flight1);

        Cargo cargo1=new Cargo();
        cargo1.setCargoNumber("CG234");
        cargo1.setWeight(458.24);
        cargo1.setCargoStatus(CargoStatus.NOT_ARRIVED);
        Cargo updatedCargo= cargoService.addCargoToFlight("FL456",cargo1);
        assertNotNull(updatedCargo.getFlight());
    }


}