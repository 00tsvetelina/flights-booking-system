package com.flightbookingsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="flight_id")
    private Integer flightId;

    @Column(name="destination")
    private String destination;

    @Column(name="departure_time")
    private LocalTime departureTime;

    @Column(name = "arriving_at")
    private String arrivingAt;

    @Column(name="arriving_time")
    private LocalTime arrivingTime;

    @Column(name="ticket_type")
    private String ticketType;

    @Column(name="seat")
    private String seat;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="user_name")
    private String userName;

    @Column(name="ticket_price")
    private Integer ticketPrice;

    @Column (name="promo_id")
    private Integer promoId;
}
