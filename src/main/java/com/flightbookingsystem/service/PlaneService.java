package com.flightbookingsystem.service;

import com.flightbookingsystem.error.IllegalArgumentException;
import com.flightbookingsystem.model.Flight;
import com.flightbookingsystem.model.Plane;
import com.flightbookingsystem.repository.PlaneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlaneService {

    private final PlaneRepository planeRepository;
    private final FlightService flightService;

    @Autowired
    public PlaneService(PlaneRepository planeRepository, FlightService flightService) {
        this.planeRepository = planeRepository;
        this.flightService = flightService;
    }

    public List<Plane> getAllPlanes(){
        return planeRepository.getAllPlanes();
    }

    public Plane getPlaneById(Integer planeId) {
       return planeRepository.getPlanesById(planeId)
            .orElseThrow(
                    () -> new IllegalArgumentException(
                            String.format("Plane with id: %d does not exist.", planeId)
                    )
            );
    }

    public Plane addPlane(Plane plane) {
        return planeRepository.save(plane);
    }

    @Transactional
    public Plane editPlane(Integer planeId, Plane updatedPlane){
        Plane plane = getPlaneById(planeId);

        plane.setModel(updatedPlane.getModel());
        plane.setFlights(updatedPlane.getFlights());

        return planeRepository.save(plane);
    }


    @Transactional
    public Plane deletePlane(Integer planeId) {
        Plane plane = getPlaneById(planeId);

        List<Flight> flightList = flightService.findAllByPlaneIn(new ArrayList<>(){{add(plane);}});
        if(!flightList.isEmpty()) {
            throw new IllegalArgumentException("Plane with id " + plane + " has active flights");
        }

        planeRepository.delete(plane);
        return plane;
    }
}
