package com.flightbookingsystem.service;

import com.flightbookingsystem.model.Flight;
import com.flightbookingsystem.model.Ticket;
import com.flightbookingsystem.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    List<Ticket> findAllByFlightIn(List<Flight> flights) {
        return ticketRepository.findAllByFlightIn(flights);
    }

}
