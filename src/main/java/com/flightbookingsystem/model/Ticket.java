package com.flightbookingsystem.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name="flight_id")
    private Flight flight;

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

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="user_name")
    private String userName;

    @Column(name="ticket_price")
    private Float ticketPrice;

    @Column (name="promo_id")
    private Integer promoId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="promo_ticket",
            joinColumns = @JoinColumn(name = "promo_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id")
    )
    private List<Ticket> tickets;
}
