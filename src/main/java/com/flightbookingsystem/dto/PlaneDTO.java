package com.flightbookingsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flightbookingsystem.model.Flight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaneDto {

    private Integer id;

    private String model;


}
