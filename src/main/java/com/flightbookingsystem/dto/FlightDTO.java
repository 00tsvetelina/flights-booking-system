package com.flightbookingsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightDto {

    private  Integer id;

    private PlaneDto plane;

    private  String origin;

    private  String destination;

    private  LocalDateTime departureTime;

    private  LocalDateTime arrivalTime;

    private  Integer delayInMins;

    private  Float price;

    private  Integer seatsCount;

}
