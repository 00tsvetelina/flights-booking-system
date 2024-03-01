package com.flightbookingsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flightbookingsystem.model.Flight;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketDto {

    private Integer id;

    @NotNull
    @NotBlank
    private Flight flight;

    @NotNull
    @NotBlank
    private String destination;

    @NotNull
    @NotBlank
    private LocalDate departureTime;

    @NotBlank
    @NotNull
    private String origin;

    @NotNull
    @NotBlank
    private String seat;

    private UserDto user;

    @NotBlank
    private Float ticketPrice;

    @JsonIgnore
    private List<PromoDto> promos;
}
