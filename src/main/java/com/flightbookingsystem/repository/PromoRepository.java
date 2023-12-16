package com.flightbookingsystem.repository;

import com.flightbookingsystem.model.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoRepository extends JpaRepository<Promo, Integer> {
}
