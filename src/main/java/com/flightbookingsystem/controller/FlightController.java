package com.flightbookingsystem.controller;
import com.flightbookingsystem.dto.FlightDto;
import com.flightbookingsystem.model.Flight;
import com.flightbookingsystem.service.FlightService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("api/admin/flights")
public class FlightController {

    private final ModelMapper modelMapper = new ModelMapper();
    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<FlightDto>> getFlights(){
        List<FlightDto> flightDtos = flightService.getAllFlights()
                .stream().map(flight -> modelMapper
                        .map(flight, FlightDto.class))
                        .toList();
        return ResponseEntity.ok(flightDtos);
    }

    @GetMapping(value = "/{flightId}")
    public ResponseEntity<FlightDto> findFlightById(@PathVariable("flightId") Integer flightId) {

        Flight flight = flightService.getFlightById(flightId);
        FlightDto flightDto = modelMapper.map(flight, FlightDto.class);
        return ResponseEntity.ok(flightDto);
    }

    @PostMapping
    public ResponseEntity<FlightDto> createFlight(@RequestBody FlightDto flightDto) {
        Flight entity = modelMapper.map(flightDto, Flight.class);
        entity = flightService.addFlight(entity);
        FlightDto responseDto = modelMapper.map(entity, FlightDto.class);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping(value = "/{flightId}")
    public ResponseEntity<Flight> updateFlight(@PathVariable("flightId") Integer flightId,
                                               @RequestBody Flight flightDTO) {

        Flight updatedFlight = flightService.editFlight(flightId, flightDTO);

        return ResponseEntity.ok(updatedFlight);
    }

    @DeleteMapping(value="/{flightId}")
    public ResponseEntity<Flight> deleteFlight(@PathVariable("flightId") Integer flightId) {
        Flight deletedFlight = flightService.deleteFlights(flightId);

        return ResponseEntity.ok(deletedFlight);
    }

}
