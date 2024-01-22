package com.flightbookingsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name="plane")
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="model", nullable = false)
    private String model;

    @JsonIgnore
    @OneToMany(mappedBy = "plane")
    private List<Flight> flights;

}
