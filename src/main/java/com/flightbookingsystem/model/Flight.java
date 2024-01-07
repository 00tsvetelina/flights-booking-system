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
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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

    @Column(name="departure_time",nullable = false)
    private LocalDate departureTime;

    @Column(name="delay", nullable = false)
    private Integer delayInMins;

    @Column(name="price", nullable = false)
    private Float price;

    @Column(name="seatsCount", nullable = false)
    private Integer seatsCount;

}
