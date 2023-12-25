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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    @NotBlank
    @Column(name="origin")
    private String origin;

    @NotBlank
    @Column(name="destination")
    private String destination;

    @NotNull
    @Column(name="departure_time")
    private LocalDate departureTime;

    @NotNull
    @Column(name="delay")
    private Integer delayInMins;

    @NotNull
    @Column(name="price")
    private Float price;

    @NotNull
    @Column(name="seatsCount")
    private Integer seatsCount;

}
