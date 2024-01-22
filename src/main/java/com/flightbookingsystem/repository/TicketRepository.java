package com.flightbookingsystem.repository;

import com.flightbookingsystem.model.Flight;
import com.flightbookingsystem.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Optional<Ticket> getTicketById(Integer ticketId);

    @Query(value = "select * from ticket", nativeQuery = true)
    List<Ticket> getAllTickets();

    List<Ticket> findAllByFlightIn(List<Flight> flights);

}
