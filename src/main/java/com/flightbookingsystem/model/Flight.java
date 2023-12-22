package com.flightbookingsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
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

    @Column(name="origin")
    private String origin;

    @Column(name="destination")
    private String destination;

    @Column(name="departure_time")
    private LocalDateTime departureTime;

    @Column(name="arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name="delay")
    private Integer delayInMins;

    @Column(name="price")
    private Float price;

    @Column(name="seatsCount")
    private Integer seatsCount;

}
