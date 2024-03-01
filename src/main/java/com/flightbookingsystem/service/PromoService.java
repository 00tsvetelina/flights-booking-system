package com.flightbookingsystem.service;

import com.flightbookingsystem.model.Promo;
import com.flightbookingsystem.repository.PromoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PromoService {

    private final PromoRepository promoRepository;

    @Autowired
    public PromoService(PromoRepository promoRepository) {
        this.promoRepository = promoRepository;
    }

    public List<Promo> getAllPromos() {
        return promoRepository.findAll();
    }

    public Promo getPromoById(Integer promoId){
        return promoRepository.getPromoById(promoId)
                .orElseThrow(
                        ()->new IllegalArgumentException(
                                String.format("Promo with id: %d does not exist.", promoId)));
    }

    @Transactional
    public Promo addPromo(Promo promo) {
        return promoRepository.save(promo);
    }

   @Transactional
    public Promo editPromo(Integer promoId, Promo updatedPromo) {
        Promo promo = getPromoById(promoId);

        promo.setPromoCode(updatedPromo.getPromoCode());
        promo.setDurationEnd(updatedPromo.getDurationEnd());
        promo.setSingleUse(updatedPromo.getSingleUse());
        promo.setTickets(updatedPromo.getTickets());

        return promoRepository.save(promo);
   }

    @Transactional
    public Promo deletePromo(Integer promoId) {
        Promo promo  = getPromoById(promoId);
        promoRepository.delete(promo);
        return promo;
    }

}
