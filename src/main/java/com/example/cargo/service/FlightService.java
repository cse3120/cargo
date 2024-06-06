package com.example.cargo.service;

import com.example.cargo.entity.Cargo;
import com.example.cargo.entity.CargoStatus;
import com.example.cargo.entity.Flight;
import com.example.cargo.exception.DuplicateEntityException;
import com.example.cargo.exception.ResourceNotFoundException;
import com.example.cargo.repository.CargoRepository;
import com.example.cargo.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final CargoRepository cargoRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository, CargoRepository cargoRepository) {
        this.flightRepository = flightRepository;
        this.cargoRepository = cargoRepository;
    }

    public Flight createFlight(Flight flight) {
        if (flightRepository.existsById(flight.getFlightNumber())) {
            throw new DuplicateEntityException("Flight number already exists");
        }
        return flightRepository.save(flight);
    }

    public Flight getFlight(String flightNumber) {
        return flightRepository.findById(flightNumber).orElseThrow(() -> new ResourceNotFoundException("Flight not found"));
    }

    public Flight confirmFlightArrival(String flightNumber) {
        Flight flight = flightRepository.findById(flightNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found"));
        List<Cargo> cargos = cargoRepository.findByFlight_FlightNumberAndCargoStatus(flightNumber, CargoStatus.NOT_ARRIVED);
        if (cargos.isEmpty()) {
            throw new ResourceNotFoundException("No cargo found to update the status");
        } else {
            cargos.forEach(cargo -> {
                cargo.setCargoStatus(CargoStatus.ARRIVED);
                cargoRepository.save(cargo);
            });
            return flight;
        }
    }
}
