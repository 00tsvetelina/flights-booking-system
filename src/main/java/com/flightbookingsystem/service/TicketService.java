package com.flightbookingsystem.service;

import com.flightbookingsystem.error.InvalidInputException;
import com.flightbookingsystem.model.Flight;
import com.flightbookingsystem.model.Promo;
import com.flightbookingsystem.model.Ticket;
import com.flightbookingsystem.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    // get all tickets
    public List<Ticket> getAllTickets(){
        List<Ticket> tickets = ticketRepository.getAllTickets();
        return tickets;
    }

    // get ticket by ticketId
    public Ticket getTicketById(Integer ticketId){
        return ticketRepository.getTicketById(ticketId)
                .orElseThrow(
                        ()-> new InvalidInputException(
                                String.format("Ticket with id: %d does not exist.", ticketId)
                        )
                );
    }

    // save ticket
    @Transactional
    public Ticket addTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    // edit ticket
    @Transactional
    public Ticket editTicket(Integer ticketId, Ticket updatedTicket) {
        Optional<Ticket> result = Optional.of(getTicketById(ticketId));
        Ticket ticket;

        if(result.isPresent()) {

            ticket = result.get();

            ticket.setFlight(updatedTicket.getFlight());
            ticket.setDestination(updatedTicket.getDestination());
            ticket.setDepartureTime(updatedTicket.getDepartureTime());
            ticket.setOrigin(updatedTicket.getOrigin());
            ticket.setSeat(updatedTicket.getSeat());
            ticket.setPromos(updatedTicket.getPromos());
            ticket.setUser(updatedTicket.getUser());
            ticket.setTicketPrice(updatedTicket.getTicketPrice());
            ticket.setPromos(updatedTicket.getPromos());

        } else {
            throw new IllegalArgumentException("No existing tickets with id " + ticketId);
        }

        return ticketRepository.save(ticket);
    }

    // delete ticket
    @Transactional
    public Ticket deleteTicket(Integer ticketId) {
        Optional<Ticket> result = ticketRepository.getTicketById(ticketId);
        if(result.isEmpty()){
            throw  new InvalidInputException(String.format("Object with id: %d cannot be found", ticketId));
        }

        Ticket ticket = result.get();
        ticketRepository.delete(ticket);
        return ticket;
    }

    // find all tickets for flights
    List<Ticket> findAllByFlightIn(List<Flight> flights) {

        return ticketRepository.findAllByFlightIn(flights);
    }

//    // find all tickets with promos
//    public List<Ticket> findAllByPromoIn(List<Promo> promos) {
//        return ticketRepository.findAllByTicketIn(promos);
//    }
}
