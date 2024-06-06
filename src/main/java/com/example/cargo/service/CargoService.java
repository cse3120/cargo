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

@Service
public class CargoService {

    private final CargoRepository cargoRepository;
    private final FlightRepository flightRepository;

    @Autowired
    public CargoService(CargoRepository cargoRepository, FlightRepository flightRepository) {
        this.cargoRepository = cargoRepository;
        this.flightRepository = flightRepository;
    }

    public Cargo createCargo(Cargo cargo) {
        if(cargoRepository.existsById(cargo.getCargoNumber())){
            throw new DuplicateEntityException("Cargo already exist with the number");
        }
        Flight flight=flightRepository.findById(cargo.getFlight().getFlightNumber())
                .orElseThrow((()-> new ResourceNotFoundException("Flight not found")));
        cargo.setFlight(flight);
        return cargoRepository.save(cargo);
    }

    public Cargo updateCargoStatus(String cargoNumber, CargoStatus cargoStatus) {
        if (cargoRepository.existsById(cargoNumber)) {
            throw new DuplicateEntityException("Cargo already exist with this number");
        }
        Cargo cargo = cargoRepository.findById(cargoNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Cargo not found"));
        cargo.setCargoStatus(cargoStatus);
        return cargo;
    }

    public Cargo addCargoToFlight(String flightNumber, Cargo cargo) {
        if (cargoRepository.existsById(cargo.getCargoNumber())) {
            throw new DuplicateEntityException("Cargo already exist with this number");
        }
        Flight flight = flightRepository.findById(flightNumber).orElseThrow(() -> new ResourceNotFoundException("Flight not found"));
        cargo.setFlight(flight);
        cargoRepository.save(cargo);
        return cargo;
    }
}
