package com.example.cargo.controller;

import com.example.cargo.entity.Flight;
import com.example.cargo.service.FlightService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    /**
     * Create flight.
     *
     * @param flight Flight
     * @return Flight
     */
    @PostMapping
    @ApiOperation(value = "Submit aircraft flight details")
    public Flight createFlight(@RequestBody Flight flight){
        return flightService.createFlight(flight);
    }

    /**
     * Get flight.
     *
     * @param flightNumber String
     * @return Flight
     */
    @GetMapping("/{flightNumber}")
    @ApiOperation(value = "Get flight details by passing flight number")
    public Flight getFlight(@PathVariable String flightNumber){
        return flightService.getFlight(flightNumber);
    }

    /**
     * Update the Status of the Cargo from "Not Arrived" to "Arrived" when the flight lands.
     *
     * @param flightNumber String
     * @return Flight
     */
    @PutMapping("/{flightNumber}/landed")
    @ApiOperation(value = "Update the status of the cargo from “not arrived” to “arrived” when the flight lands")
    public Flight confirmFlightArrival(@PathVariable String flightNumber) {
        return flightService.confirmFlightArrival(flightNumber);
    }
}
