package com.flightbookingsystem.controller;


import com.flightbookingsystem.dto.PlaneDto;
import com.flightbookingsystem.model.Plane;
import com.flightbookingsystem.service.PlaneService;
import org.modelmapper.ModelMapper;
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
@RequestMapping("api/admin/planes")
public class PlaneController {

    private final ModelMapper modelMapper = new ModelMapper();
    private final PlaneService planeService;

    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }


    @GetMapping
    public ResponseEntity<List<PlaneDto>> getPlanes(){
        List<PlaneDto> planeDtos = planeService.getAllPlanes()
                .stream().map(
                        plane -> modelMapper.map(plane, PlaneDto.class)
                )
                .toList();
        return ResponseEntity.ok(planeDtos);
    }

    @GetMapping(value = "/{planeId}")
    public ResponseEntity<PlaneDto> getPlaneById(@PathVariable Integer planeId){
        Plane plane = planeService.getPlaneById(planeId);
        PlaneDto planeDto = modelMapper.map(plane, PlaneDto.class);
        return ResponseEntity.ok(planeDto);
    }

    @PostMapping
    public ResponseEntity<PlaneDto> createPlane(@RequestBody PlaneDto planeDto) {
        Plane entity = modelMapper.map(planeDto, Plane.class);
        entity = planeService.addPlane(entity);
        PlaneDto responseDto = modelMapper.map(entity, PlaneDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


    @PutMapping(value = "/{planeId}")
    public ResponseEntity<PlaneDto> updatePlane(@PathVariable("planeId") Integer planeId,
                                                @RequestBody PlaneDto planeDto) {
        Plane entity = modelMapper.map(planeDto, Plane.class);
        Plane updatedPlane = planeService.editPlane(planeId, entity);
        PlaneDto responseDto = modelMapper.map(updatedPlane, PlaneDto.class);
        return ResponseEntity.ok(responseDto);
    }

    @RequestMapping(value = "/{planeId}", method = RequestMethod.DELETE)
    public ResponseEntity<PlaneDto> deletePlane(@PathVariable("planeId") Integer planeId){
        Plane deletedPlane = planeService.deletePlane(planeId);
        PlaneDto planeDto = modelMapper.map(deletedPlane, PlaneDto.class);
        return ResponseEntity.ok(planeDto);
    }

}
