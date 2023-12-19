package com.flightbookingsystem.dto;

import com.flightbookingsystem.model.Plane;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

@RequiredArgsConstructor
@Data
public class FlightDTO {

    private Integer id;
    private Plane plane;
    private String origin;
    private String destination;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private Integer delay;
    private Float price;
    private Integer seatsCount;
}
