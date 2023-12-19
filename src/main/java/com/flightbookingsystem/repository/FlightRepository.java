package com.flightbookingsystem.repository;

import com.flightbookingsystem.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    Optional<Flight> getFlightById(Integer flightId);

    @Query(value = "select * from flight", nativeQuery = true)
    List<Flight> getAllFlights();
}
