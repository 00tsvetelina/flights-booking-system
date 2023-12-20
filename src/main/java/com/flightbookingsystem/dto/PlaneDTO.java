package com.flightbookingsystem.dto;

import com.flightbookingsystem.model.Flight;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class PlaneDTO {

    private Integer id;
    private String model;
    private Flight flight;
}
