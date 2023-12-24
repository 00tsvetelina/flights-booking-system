package com.flightbookingsystem.repository;


import com.flightbookingsystem.model.Plane;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, Integer> {

    // find plane by planeId
    Optional<Plane> getPlanesById(Integer planeId);

    // select all planes from db
    @Query(value = "select * from plane", nativeQuery = true)
    List<Plane> getAllPlanes();
}
