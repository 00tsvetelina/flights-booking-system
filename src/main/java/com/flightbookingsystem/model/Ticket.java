package com.flightbookingsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
    @JoinColumn(name="flight_id", nullable = false)
    private Flight flight;

    @Column(name="destination", nullable = false)
    private String destination;

    @Column(name="departure_time", nullable = false)
    private LocalDate departureTime;

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name="seat", nullable = false)
    private String seat;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(name="ticket_price", nullable = false)
    private Float ticketPrice;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="promo_ticket",
            joinColumns = @JoinColumn(name = "ticket_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "promo_id", referencedColumnName = "id")
    )
    private List<Promo> promos;
}
