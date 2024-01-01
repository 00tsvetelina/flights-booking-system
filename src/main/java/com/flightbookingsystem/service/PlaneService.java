package com.flightbookingsystem.service;

import com.flightbookingsystem.error.IllegalArgumentException;
import com.flightbookingsystem.error.InvalidInputException;
import com.flightbookingsystem.model.Flight;
import com.flightbookingsystem.model.Plane;
import com.flightbookingsystem.repository.FlightRepository;
import com.flightbookingsystem.repository.PlaneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaneService {

    private final PlaneRepository planeRepository;
    private final FlightService flightService;

    @Autowired
    public PlaneService(PlaneRepository planeRepository, FlightService flightService) {
        this.planeRepository = planeRepository;
        this.flightService = flightService;
    }

    // get all planes
    public List<Plane> getAllPlanes(){
        return planeRepository.getAllPlanes();
    }

    // get plane by planeId
    public Plane getPlaneById(Integer planeId) {
       return planeRepository.getPlanesById(planeId)
            .orElseThrow(
                    () -> new InvalidInputException(
                            String.format("Plane with id: %d does not exist.", planeId)
                    )
            );
    }

    // save plane
    public Plane addPlane(Plane plane) {
        return planeRepository.save(plane);
    }

    // edit plane
    @Transactional
    public Plane editPlane(Integer planeId, Plane updatedPlane){
            Optional<Plane> result = Optional.of(getPlaneById(planeId));
            Plane plane;

            if(result.isPresent()){
                plane = result.get();
                plane.setModel(updatedPlane.getModel());
                plane.setFlights(updatedPlane.getFlights());
            } else {
                throw new IllegalArgumentException("No existing planes with id " + planeId);
            }

            return planeRepository.save(plane);
    }

    // check if tickets with planeId are already connected to flights
    // delete plane, if not
    @Transactional
    public Plane deletePlane(Integer planeId) {
        Optional<Plane> result = planeRepository.getPlanesById(planeId);
        if(result.isEmpty()) {
            throw new InvalidInputException(String.format("Object with id: %d cannot be found", planeId));
        }

        Plane plane = result.get();
        List<Flight> flightList = flightService.findAllByPlaneIn(new ArrayList<>(){{add(plane);}});
        if(!flightList.isEmpty()) {
            throw new IllegalArgumentException("Plane with id " + plane + " has active flights");
        }
        planeRepository.delete(plane);
        return plane;
    }
}
