package com.flightbookingsystem.controller;

import com.flightbookingsystem.dto.TicketDto;
import com.flightbookingsystem.model.Ticket;
import com.flightbookingsystem.service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/admin/tickets")
public class TicketController {

    private final ModelMapper modelMapper = new ModelMapper();
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        List<TicketDto> ticketDtos = ticketService.getAllTickets()
                .stream().map(ticket -> modelMapper
                        .map(ticket, TicketDto.class))
                .toList();
        return ResponseEntity.ok(ticketDtos);
    }

    @GetMapping(value = "/{ticketId}")
    public ResponseEntity<TicketDto> findTicketById(@PathVariable("ticketId") Integer ticketId){
        Ticket ticket = ticketService.getTicketById(ticketId);
        TicketDto ticketDto = modelMapper.map(ticket, TicketDto.class);
        return ResponseEntity.ok(ticketDto);
    }

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto) {
        Ticket entity = modelMapper.map(ticketDto, Ticket.class);
        entity = ticketService.addTicket(entity);
        TicketDto responseDto = modelMapper.map(entity, TicketDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{ticketId}")
    public ResponseEntity<TicketDto> editTicket(@PathVariable("ticketId") Integer ticketId,
                                                @RequestBody TicketDto ticketDto) {
        Ticket entity = modelMapper.map(ticketDto, Ticket.class);
        Ticket updatedTicket = ticketService.editTicket(ticketId, entity);
        TicketDto responseDto = modelMapper.map(updatedTicket, TicketDto.class);
        return ResponseEntity.ok(responseDto);
    }

    @RequestMapping(value = "/{ticketId}", method = RequestMethod.DELETE)
    public ResponseEntity<TicketDto> deleteTicket(@PathVariable("ticketId") Integer ticketId){
        Ticket deletedTicket = ticketService.deleteTicket(ticketId);
        TicketDto ticketDto = modelMapper.map(deletedTicket, TicketDto.class);
        return ResponseEntity.ok(ticketDto);
    }

}
