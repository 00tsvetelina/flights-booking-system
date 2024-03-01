package com.flightbookingsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;
@NoArgsConstructor
@Data
@Entity
@Table(name="ticket")
public class Ticket  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="flight_id", nullable = false)
    private Flight flight;

    @Column(name="destination", nullable = false)
    private String destination;

    @Column(name="departure_time", nullable = false)
    private LocalDate departureTime;

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name="seat", nullable = false, unique = true)
    private String seat;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(name="ticket_price", nullable = false)
    private Float ticketPrice;

//    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="promo_ticket",
            joinColumns = @JoinColumn(name = "ticket_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "promo_id", referencedColumnName = "id")
    )
    private List<Promo> promos;

}
