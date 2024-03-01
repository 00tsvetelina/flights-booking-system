package com.flightbookingsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity
@Table(name="flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="plane_id")
    private Plane plane;

    @Column(name="origin", nullable = false)
    private String origin;

    @Column(name="destination", nullable = false)
    private String destination;

    @Column(name="departure_time", nullable = false)
    private LocalDate departureTime;

    @Column(name="delay", nullable = false)
    private Integer delayInMins;

    @Column(name="price", nullable = false)
    private Float price;

    @Column(name="seatsCount", nullable = false)
    private Integer seatsCount;

}
