package com.example.cargo.entity;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property ="cargoNumber")
public class Cargo {
    @Id
    @NotBlank
    @Column(unique = true)
    @Schema(required = true,defaultValue ="CG123")
    private String cargoNumber;

    @NotNull
    @Schema(required = true,defaultValue ="750.34")
    @Positive(message = "Weight must be positive value")
    private Double weight;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Schema(required = true,defaultValue ="NOT_ARRIVED")
    private CargoStatus cargoStatus;

    @ManyToOne
    @JoinColumn(name="flight_number")
    private Flight flight;

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getCargoNumber() {
        return cargoNumber;
    }

    public void setCargoNumber(String cargoNumber) {
        this.cargoNumber = cargoNumber;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public CargoStatus getCargoStatus() {
        return cargoStatus;
    }

    public void setCargoStatus(CargoStatus cargoStatus) {
        this.cargoStatus = cargoStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cargo cargo = (Cargo) o;
        return Objects.equals(cargoNumber, cargo.cargoNumber) && Objects.equals(weight, cargo.weight) && cargoStatus == cargo.cargoStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cargoNumber, weight, cargoStatus);
    }
}
