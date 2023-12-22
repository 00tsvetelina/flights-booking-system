package com.flightbookingsystem.repository;

import com.flightbookingsystem.model.Flight;
import com.flightbookingsystem.model.Promo;
import com.flightbookingsystem.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {


    // find ticket by ticketId
    Optional<Ticket> getTicketById(Integer ticketId);

    // select all tickets from db
    @Query(value = "select * from ticket", nativeQuery = true)
    List<Ticket> getAllTickets();

    // find all tickets for flights
    List<Ticket> findAllByFlightIn(List<Flight> flights);

    // find all tickets with promos
    List<Ticket> findAllByTicketsIn(ArrayList<Promo> promos);
}
