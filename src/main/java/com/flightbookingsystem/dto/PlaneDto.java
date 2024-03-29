package com.flightbookingsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaneDto {

    private Integer id;

    @NotNull
    @NotBlank
    private String model;

    @JsonIgnore
    private List<FlightDto> flights;

}
