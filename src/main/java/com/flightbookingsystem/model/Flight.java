package com.flightbookingsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="plane_id")
    private Plane plane;

    @Column(name="destination")
    private String destination;

    @Column(name="departure_time")
    private LocalTime departureTime;

    @Column(name="arrival_time")
    private LocalTime arrivalTime;

    @Column(name="delay")
    private Duration delay;
    //    Instant start = Instant.now();
    //    Instant end = Instant.parse("2017-10-03T10:16:30.00Z");
    //    Duration duration = Duration.between(start, end);

    @Column(name="price")
    private Integer price;



}
