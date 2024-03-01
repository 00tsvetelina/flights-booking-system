package com.flightbookingsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightDto  {

    private Integer id;

    @NotNull
    @NotBlank
    private PlaneDto plane;

    @NotNull
    @NotBlank
    private String origin;

    @NotNull
    @NotBlank
    private String destination;

    @NotNull
    @NotBlank
    private LocalDate departureTime;

    @NotNull
    @NotBlank
    private  Integer delayInMins;

    @NotNull
    @NotBlank
    private  Float price;

    @NotNull
    @NotBlank
    private  Integer seatsCount;

}
