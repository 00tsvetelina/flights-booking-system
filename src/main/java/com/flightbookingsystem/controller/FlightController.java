package com.flightbookingsystem.controller;
import com.flightbookingsystem.model.Flight;
import com.flightbookingsystem.service.FlightService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@RequestMapping("api/admin")
@ResponseStatus(HttpStatus.OK)
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // fetch all flights
    @GetMapping(value = "/flights")
    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    // fetch flight by id
    @GetMapping(value = "/flights/{flightId}")
    public ResponseEntity<Flight> findFlightById(@PathVariable("flightId") Integer flightId) {
        Flight flight = flightService.getFlightById(flightId);
        return ResponseEntity.ok(flight);
    }

    // create flight
    @PostMapping(value = "/flights")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        Flight createdFlight = flightService.addFlight(flight);
        return ResponseEntity.ok(createdFlight);
    }

    // edit flight
    @PutMapping(value = "/flights/{flightId}")
    public ResponseEntity<Flight> updateFlight(@PathVariable("flightId") Integer flightId,
                                               @RequestBody Flight flightDTO) {

        Flight updatedFlight = flightService.editFlight(flightId, flightDTO);

        return ResponseEntity.ok(updatedFlight);
    }

    @RequestMapping(value="/airline/{flightId}", method=RequestMethod.DELETE)
    public ResponseEntity<Flight> deleteFlight(@PathVariable("flightId") Integer flightId) {
        Flight deletedFlight = flightService.deleteFlights(flightId);

        return ResponseEntity.ok(deletedFlight);
    }


}
