package com.flightbookingsystem.service;

import com.flightbookingsystem.model.Flight;
import com.flightbookingsystem.model.Ticket;
import com.flightbookingsystem.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Integer ticketId){
        return ticketRepository.getTicketById(ticketId)
                .orElseThrow(
                        ()-> new IllegalArgumentException(
                                String.format("Ticket with id: %d does not exist.", ticketId)
                        )
                );
    }

    @Transactional
    public Ticket addTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket editTicket(Integer ticketId, Ticket updatedTicket) {
        Ticket ticket = getTicketById(ticketId);
        ticket.setFlight(updatedTicket.getFlight());
        ticket.setDestination(updatedTicket.getDestination());
        ticket.setDepartureTime(updatedTicket.getDepartureTime());
        ticket.setOrigin(updatedTicket.getOrigin());
        ticket.setSeat(updatedTicket.getSeat());
        ticket.setPromos(updatedTicket.getPromos());
        ticket.setUser(updatedTicket.getUser());
        ticket.setTicketPrice(updatedTicket.getTicketPrice());
        ticket.setPromos(updatedTicket.getPromos());
        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket deleteTicket(Integer ticketId) {
        Ticket ticket = getTicketById(ticketId);
        ticketRepository.delete(ticket);

        return ticket;
    }


    List<Ticket> findAllByFlightIn(List<Flight> flights) {
        return ticketRepository.findAllByFlightIn(flights);
    }

}
