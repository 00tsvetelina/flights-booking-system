package com.flightbookingsystem.repository;

import com.flightbookingsystem.model.Flight;
import com.flightbookingsystem.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    Optional<Flight> getFlightById(Integer flightId);

    List<Flight> findAll();

    List<Flight> findAllByPlaneIn(List<Plane> planes);

}
