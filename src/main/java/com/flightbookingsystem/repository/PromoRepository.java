package com.flightbookingsystem.repository;

import com.flightbookingsystem.model.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PromoRepository extends JpaRepository<Promo, Integer> {

    Optional<Promo> getPromoById(Integer promoId);

    List<Promo> findAll();

}
