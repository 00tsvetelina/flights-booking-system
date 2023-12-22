package com.flightbookingsystem.dto;

import com.flightbookingsystem.model.Flight;
import com.flightbookingsystem.model.Promo;
import com.flightbookingsystem.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketDto {
    private Integer id;

    private Flight flight;

    private String destination;

    private LocalDateTime departureTime;

    private String origin;

    private LocalDateTime arrivalTime;

    private String ticketType;

    private String seat;

    private User user;

    private String userName;

    private Float ticketPrice;

    private List<Promo> promos;
}
