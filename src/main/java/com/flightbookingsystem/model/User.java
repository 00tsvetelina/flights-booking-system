package com.flightbookingsystem.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "full_name", nullable = false)
    private String firstAndLastNames;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name="password", unique = true, nullable = false)
    private String password;

    @Column(name="is_enabled", columnDefinition = "boolean default true")
    private Boolean isEnabled;

    @Column(name="role")
    private List<String> roles;

}
