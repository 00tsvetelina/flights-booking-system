package com.flightbookingsystem.model;

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

import java.time.Duration;
import java.time.Instant;
import java.time.Period;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="promo")
public class Promo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="promo_code")
    private String promoCode;

    @Column(name="percent_off")
    private Integer percentOff;

    @Column(name="duration")
    private Duration duration;
//    Instant start = Instant.now();
//    Instant end = Instant.parse("2017-10-03T10:16:30.00Z");
//    Duration duration = Duration.between(start, end);

    @Column(name="single_use")
    private Boolean singleUse;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="promo_ticket",
            joinColumns = @JoinColumn(name="ticket_id"),
            inverseJoinColumns = @JoinColumn(name="promo_id")
    )
    private List<Promo> promos;
}
