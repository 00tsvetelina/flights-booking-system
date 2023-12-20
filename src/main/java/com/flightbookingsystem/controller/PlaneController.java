package com.flightbookingsystem.controller;

import com.flightbookingsystem.dto.PlaneDTO;
import com.flightbookingsystem.model.Plane;
import com.flightbookingsystem.service.PlaneService;
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

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/admin/")
public class PlaneController {

    private final PlaneService planeService;

    @Autowired
    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    // fetch all planes
    @GetMapping(value = "/planes")
    public ResponseEntity<List<Plane>> getAllPlanes() {
        return ResponseEntity.ok(planeService.getAllPlanes());
    }

    // fetch plane by id
    @GetMapping(value = "planes/{planeId}")
    public ResponseEntity<Plane> findPlaneById(@PathVariable("planeId") Integer planeId) {
        Plane plane = planeService.getPlaneById(planeId);
        return ResponseEntity.ok(plane);
    }

    // create plane
    @PostMapping(value = "/planes")
    public ResponseEntity<Plane> createPlane(@RequestBody Plane plane){
        Plane createdPlane = planeService.addPlane(plane);
        return ResponseEntity.ok(createdPlane);
    }

    // edit plane
    @PutMapping(value = "/planes/{planeId}")
    public ResponseEntity<Plane> updatePlane(@PathVariable("planeId") Integer planeId,
                                             @RequestBody Plane planeDTO) {
        Plane updatedPlane = planeService.editPlane(planeId, planeDTO);
        return ResponseEntity.ok(updatedPlane);
    }

    // delete plane
    @RequestMapping(value = "/airplane/{planeId}", method = RequestMethod.DELETE)
    public ResponseEntity<Plane> deletePlane(@PathVariable("planeId") Integer planeId){
        Plane deletedPlane = planeService.deletePlane(planeId);
        return ResponseEntity.ok(deletedPlane);
    }
}
