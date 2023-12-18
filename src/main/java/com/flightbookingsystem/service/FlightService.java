package com.flightbookingsystem.service;

import com.flightbookingsystem.model.Flight;
import com.flightbookingsystem.model.Ticket;
import com.flightbookingsystem.repository.FlightRepository;
import com.flightbookingsystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Flight getFlight(Integer flightId) {
        return flightRepository.getById(flightId);
    }

    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight editFlight(Flight updatedFlight) {
        LocalTime depTime = updatedFlight.getDepartureTime();
        LocalTime arrTime = updatedFlight.getArrivalTime();

        String origin = updatedFlight.getOrigin();
        String destination = updatedFlight.getDestination();

        Integer flightId = updatedFlight.getId();

        if (origin.equals(destination) || !arrTime.isAfter(depTime)) {
            throw new IllegalArgumentException("Illegal arguments entered to <edit flight>.");
        }

        Optional<Flight> result = Optional.of(flightRepository.getById(flightId));
        Flight flight;

        if(result.isPresent()) {
            flight = result.get();
            Flight finalFlight = flight;

            List<Ticket> ticketList = ticketService.findAllByFlightIn(new ArrayList<>() {{
                add(finalFlight);
            }});

            Integer seatsCount = flight.getSeatsCount();
            if(ticketList.size() > seatsCount) {
                throw new IllegalArgumentException("Seats count capacity is less than requested.");
            }

            flight.setDestination(updatedFlight.getDestination());
            flight.setOrigin(updatedFlight.getOrigin());
            flight.setDepartureTime(depTime);
            flight.setArrivalTime(arrTime);
            flight.setDelay(updatedFlight.getDelay());
            flight.setPrice(updatedFlight.getPrice());
            flight.setSeatsCount(seatsCount);

        } else {
            throw new IllegalArgumentException("No existing flights with id " + flightId);
        }

        return flightRepository.save(flight);
    }

    public void deleteFlights(Integer flightId)  {
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
    }


}
