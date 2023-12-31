package com.flightbookingsystem.repository;

import com.flightbookingsystem.model.Flight;
import com.flightbookingsystem.model.Plane;
import com.flightbookingsystem.model.Ticket;
import org.springframework.beans.factory.annotation.Qualifier;
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

    List<Flight> findAllByPlaneIn(List<Plane> planes);

    List<Flight> findAllByPlane_Id(Integer planeId);
}
