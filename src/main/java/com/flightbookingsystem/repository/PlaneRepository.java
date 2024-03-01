package com.flightbookingsystem.repository;


import com.flightbookingsystem.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, Integer> {

    Optional<Plane> getPlanesById(Integer planeId);

    List<Plane> findAll();
}
