package com.flightbookingsystem.controller;
import com.flightbookingsystem.dto.FlightDto;
import com.flightbookingsystem.model.Flight;
import com.flightbookingsystem.service.FlightService;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/admin/flights")
public class FlightController {

    private final ModelMapper modelMapper = new ModelMapper();
    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // fetch all flights
    @GetMapping
    public List<FlightDto> getFlights(){
        List<FlightDto> flightDtos = flightService.getAllFlights()
                .stream().map(flight -> modelMapper
                        .map(flight, FlightDto.class))
                        .toList();
        return flightDtos;
    }

    // fetch flight by id
    @GetMapping(value = "/{flightId}")
    public FlightDto findFlightById(@PathVariable("flightId") Integer flightId) {

        Flight flight = flightService.getFlightById(flightId);
        FlightDto flightDto = modelMapper.map(flight, FlightDto.class);
        return flightDto;
//        return ResponseEntity.ok(flight);
    }

    // create flight
    @PostMapping
    public FlightDto createFlight(@RequestBody FlightDto flightDto) {
        Flight entity = modelMapper.map(flightDto, Flight.class);
        entity = flightService.addFlight(entity);
        FlightDto responseDto = modelMapper.map(entity, FlightDto.class);
        return responseDto;
//        return ResponseEntity.ok(createdFlight);
    }

    // edit flight
    @PutMapping(value = "/{flightId}")
    public ResponseEntity<Flight> updateFlight(@PathVariable("flightId") Integer flightId,
                                               @RequestBody Flight flightDTO) {

        Flight updatedFlight = flightService.editFlight(flightId, flightDTO);

        return ResponseEntity.ok(updatedFlight);
    }

    @RequestMapping(value="/{flightId}", method=RequestMethod.DELETE)
    public ResponseEntity<Flight> deleteFlight(@PathVariable("flightId") Integer flightId) {
        Flight deletedFlight = flightService.deleteFlights(flightId);

        return ResponseEntity.ok(deletedFlight);
    }


}
