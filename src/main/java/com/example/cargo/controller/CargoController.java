package com.example.cargo.controller;

import com.example.cargo.entity.Cargo;
import com.example.cargo.entity.CargoStatus;
import com.example.cargo.service.CargoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    /**
     * Create Cargo.
     * @param cargo Cargo
     * @return Cargo
     */
    @PostMapping
    @ApiOperation(value = "Submit cargo details")
    public Cargo createCargo(@RequestBody Cargo cargo){
        return cargoService.createCargo(cargo);
    }

    /**
     * Update the cargo status.
     *
     * @param cargoNumber String
     * @param cargoStatus CargoStatus
     * @return Cargo
     */
    @PutMapping("/{cargoNumber}/status")
    @ApiOperation(value = "Update the status of the cargo by passing cargoNumber and status")
    public Cargo updateCargoStatus(@PathVariable String cargoNumber, @RequestParam CargoStatus cargoStatus){
        return cargoService.updateCargoStatus(cargoNumber,cargoStatus);
    }

    /**
     * Supply Details of the Cargo Onboard the Flight.
     *
     * @param flightNumber String
     * @param cargo Cargo
     * @return Cargo
     */
    @PostMapping("/flight/{flightNumber}")
    @ApiOperation(value = "Supply details of the cargo onboard the flight")
    public Cargo addCargoToFlight(@PathVariable String flightNumber, @RequestBody Cargo cargo) {
        return cargoService.addCargoToFlight(flightNumber, cargo);
    }
}
