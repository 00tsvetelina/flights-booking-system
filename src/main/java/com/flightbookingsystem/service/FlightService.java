package com.flightbookingsystem.service;

import com.flightbookingsystem.model.Flight;
import com.flightbookingsystem.model.Plane;
import com.flightbookingsystem.model.Ticket;
import com.flightbookingsystem.repository.FlightRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final TicketService ticketService;

    @Autowired
    public FlightService(FlightRepository flightRepository, TicketService ticketService) {
        this.flightRepository = flightRepository;
        this.ticketService = ticketService;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.getAllFlights();
    }

    //find flight by flightId
    public Flight getFlightById(Integer flightId) {
        Optional<Flight> flight = flightRepository.getFlightById(flightId);
        return flight.orElse(null);
    }


    // save flight
    @Transactional
    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    // edit flight details
    @Transactional
    public Flight editFlight(Integer flightId, Flight updatedFlight) {
        LocalDateTime depTime = updatedFlight.getDepartureTime();
        LocalDateTime arrTime = updatedFlight.getArrivalTime();

        String origin = updatedFlight.getOrigin();
        String destination = updatedFlight.getDestination();


        if (origin.equals(destination) || !arrTime.isAfter(depTime)) {
            throw new IllegalArgumentException("Illegal arguments entered to <edit flight>.");
        }
        //CHECK
        Optional<Flight> result = Optional.of(getFlightById(flightId));
        Flight flight;

        if(result.isPresent()) {
            flight = result.get();
            Flight ticketsToFlight = flight;

            // add flight to ticket list
            List<Ticket> ticketList = ticketService.findAllByFlightIn(new ArrayList<>() {{
                add(ticketsToFlight);
            }});

            Integer seatsCount = flight.getSeatsCount();
            if(ticketList.size() > seatsCount) {
                throw new IllegalArgumentException("Seats count capacity is less than requested.");
            }

            flight.setDestination(updatedFlight.getDestination());
            flight.setOrigin(updatedFlight.getOrigin());
            flight.setDepartureTime(depTime);
            flight.setArrivalTime(arrTime);
            flight.setDelayInMins(updatedFlight.getDelayInMins());
            flight.setPrice(updatedFlight.getPrice());
            flight.setSeatsCount(seatsCount);

        } else {
            throw new IllegalArgumentException("No existing flights with id " + flightId);
        }

        return flightRepository.save(flight);
    }

    // check if tickets with flightId are already booked
    // and if not, delete flight
    @Transactional
    public Flight deleteFlights(Integer flightId)  {
        Optional<Flight> result = flightRepository.getFlightById(flightId);

        if(result.isEmpty()) {
            throw new RuntimeException(String.format("Object with id: %d cannot be found", flightId));
            //TODO Change with custom exception
        }

        Flight flight = result.get();
        List<Ticket> ticketList = ticketService.findAllByFlightIn(new ArrayList<>() {{
            add(flight);
        }});
        if (!ticketList.isEmpty()) {
            throw new IllegalArgumentException("Flight with id " + flightId + " has active tickets");
        }

        flightRepository.delete(flight);
        return flight;
    }

    public List<Flight> findAllByPlaneIn(List<Plane> planes) {
        return flightRepository.findAllByPlaneIn(planes);
    }

}
