package com.flightbookingsystem.service;

import com.flightbookingsystem.model.Flight;
import com.flightbookingsystem.model.Plane;
import com.flightbookingsystem.model.Ticket;
import com.flightbookingsystem.repository.FlightRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

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
        return flightRepository.findAll();
    }

    public Flight getFlightById(Integer flightId) {
        return flightRepository.getFlightById(flightId)
                .orElseThrow(
                        ()-> new IllegalArgumentException(
                                String.format("Flight with id: %d does not exist.", flightId)
                        )
                );
    }

    @Transactional
    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Transactional
    public Flight editFlight(Integer flightId, Flight updatedFlight) {
        Flight flight = getFlightById(flightId);
        List<Ticket> ticketList = ticketService.findAllByFlightIn(new ArrayList<>() {{
            add(flight);
        }});

        Integer seatsCount = flight.getSeatsCount();
        if(ticketList.size() > seatsCount) {
            throw new IllegalArgumentException("Seats count capacity is less than requested.");
        }

        flight.setPlane(updatedFlight.getPlane());
        flight.setDestination(updatedFlight.getDestination());
        flight.setOrigin(updatedFlight.getOrigin());
        flight.setDepartureTime(updatedFlight.getDepartureTime());
        flight.setDelayInMins(updatedFlight.getDelayInMins());
        flight.setPrice(updatedFlight.getPrice());
        flight.setSeatsCount(seatsCount);
        
        return flightRepository.save(flight);
    }

    @Transactional
    public Flight deleteFlights(Integer flightId)  {
        Flight flight = getFlightById(flightId);
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
