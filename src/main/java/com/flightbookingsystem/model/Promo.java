package com.flightbookingsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name="promo")
public class Promo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="promo_code", nullable = false)
    private String promoCode;

    @Column(name="percent_off", nullable = false)
    private Integer percentOff;

    @Column(name="duration_end", nullable = false)
    private LocalDate durationEnd;

    @Column(name="single_use", nullable = false)
    private Boolean singleUse;

    @ManyToMany(mappedBy = "promos", fetch = FetchType.EAGER)
    private List<Ticket> tickets;


}
