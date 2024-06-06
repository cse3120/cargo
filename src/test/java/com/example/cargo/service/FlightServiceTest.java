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
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightServiceTest {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Test(expected = DuplicateEntityException.class)
    public void testCreateDuplicateFlight() {
        Flight flight1 = new Flight();
        flight1.setFlightNumber("FL123");
        flight1.setArrivalDateTime(LocalDateTime.now().plusHours(5));
        flight1.setAirportCode("LHR");

        flightService.createFlight(flight1);

        Flight flight2 = new Flight();
        flight2.setFlightNumber("FL123");
        flight2.setArrivalDateTime(LocalDateTime.now().plusHours(6));
        flight2.setAirportCode("LGW");

        flightService.createFlight(flight2);
    }

    @Test
    public void testConfirmFlightArrival() {
        Flight flight = new Flight();
        flight.setFlightNumber("FL123");
        flight.setArrivalDateTime(LocalDateTime.now().plusHours(1));
        flight.setAirportCode("LHR");

        Cargo cargo = new Cargo();
        cargo.setCargoNumber("CG123");
        cargo.setWeight(750.0);
        cargo.setCargoStatus(CargoStatus.NOT_ARRIVED);
        cargo.setFlight(flight);
        Set<Cargo> cargos=new HashSet<>();
        cargos.add(cargo);
        flight.setCargos(cargos);
        flightRepository.save(flight);

        flightService.confirmFlightArrival(flight.getFlightNumber());
        Cargo updatedCargo = cargoRepository.findById(cargo.getCargoNumber()).orElse(null);

        assertNotNull(updatedCargo);
        assertEquals(CargoStatus.ARRIVED, updatedCargo.getCargoStatus());
    }

}
