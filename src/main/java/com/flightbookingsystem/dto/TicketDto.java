package com.flightbookingsystem.dto;

import com.flightbookingsystem.model.Flight;
import com.flightbookingsystem.model.Promo;
import com.flightbookingsystem.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private User user;

    @NotNull
    @NotBlank
    private String userName;

    @NotBlank
    private Float ticketPrice;

    private List<Promo> promos;
}
