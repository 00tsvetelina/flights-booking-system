package com.flightbookingsystem.service;

import com.flightbookingsystem.model.Promo;
import com.flightbookingsystem.repository.PromoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromoService {

    private final PromoRepository promoRepository;
    private final TicketService ticketService;

    @Autowired
    public PromoService(PromoRepository promoRepository, TicketService ticketService) {
        this.promoRepository = promoRepository;
        this.ticketService = ticketService;
    }

    // get all promos
    public List<Promo> getAllPromos() {
        return promoRepository.getAllPromos();
    }

    //get promo by promoId
    public Promo getPromoById(Integer promoId){
        return promoRepository.getPromoById(promoId)
                .orElseThrow(
                        ()->new IllegalArgumentException(
                                String.format("Promo with id: %d does not exist.", promoId)));
    }

    // save promo
    @Transactional
    public Promo addPromo(Promo promo) {
        return promoRepository.save(promo);
    }

    //edit promo
   @Transactional
    public Promo editPromo(Integer promoId, Promo updatedPromo) {
        Optional<Promo> result = Optional.of(getPromoById(promoId));
        Promo promo;

        if(result.isPresent()){
            promo = result.get();
            promo.setPromoCode(updatedPromo.getPromoCode());
            promo.setDurationEnd(updatedPromo.getDurationEnd());
            promo.setSingleUse(updatedPromo.getSingleUse());
            promo.setTickets(updatedPromo.getTickets());

        } else {
            throw new IllegalArgumentException("No existing promos with id " + promoId);
        }

       return promoRepository.save(promo);
   }

   // delete promo
    @Transactional
    public Promo deletePromo(Integer promoId) {
        Optional<Promo> result = promoRepository.getPromoById(promoId);
        if(result.isEmpty()) {
            throw new IllegalArgumentException(String.format("Object with id: %d cannot be found", promoId));
        }

        Promo promo = result.get();
        promoRepository.delete(promo);
        return promo;
    }

}
