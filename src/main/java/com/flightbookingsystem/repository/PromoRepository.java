package com.flightbookingsystem.repository;

import com.flightbookingsystem.model.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PromoRepository extends JpaRepository<Promo, Integer> {

    // find promo by promoId
    Optional<Promo> getPromoById(Integer promoId);

    // select all promos from db
    @Query(value = "select  * from promo", nativeQuery = true)
    List<Promo> getAllPromos();
}
