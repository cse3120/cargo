package com.example.cargo.repository;

import com.example.cargo.entity.Cargo;
import com.example.cargo.entity.CargoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRepository extends JpaRepository<Cargo,String> {
    List<Cargo> findByFlight_FlightNumberAndCargoStatus(String flightNumber, CargoStatus cargoStatus);
}
