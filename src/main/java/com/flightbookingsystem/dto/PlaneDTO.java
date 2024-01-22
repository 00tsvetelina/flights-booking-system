package com.flightbookingsystem.dto;

import com.flightbookingsystem.model.Flight;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
    private List<Flight> flights;

}
