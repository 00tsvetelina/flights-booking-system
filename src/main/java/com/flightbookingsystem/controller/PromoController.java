package com.flightbookingsystem.controller;

import com.flightbookingsystem.dto.PromoDto;
import com.flightbookingsystem.dto.TicketDto;
import com.flightbookingsystem.model.Promo;
import com.flightbookingsystem.service.PromoService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
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
@RequestMapping("api/admin/promos")
public class PromoController {

    private final PromoService promoService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public PromoController(PromoService promoService) {
        this.promoService = promoService;
    }

    // fetch all promos
    @GetMapping
    public ResponseEntity<List<PromoDto>> getAllPromos() {
        List<PromoDto> promoDtos = promoService.getAllPromos()
                .stream().map(promo -> modelMapper
                        .map(promo, PromoDto.class))
                .toList();
        return ResponseEntity.ok(promoDtos);
    }

    // fetch promo by promoId
    @GetMapping(value = "/{promoId}")
    public ResponseEntity<PromoDto> findPromoById(@PathVariable("promoId") Integer promoId){
        Promo promo = promoService.getPromoById(promoId);
        PromoDto promoDto = modelMapper.map(promo, PromoDto.class);
        return ResponseEntity.ok(promoDto);
    }


    // create promo
    @PostMapping
    public ResponseEntity<PromoDto> createPromo(@RequestBody PromoDto promoDto) {
        Promo entity = modelMapper.map(promoDto, Promo.class);
        entity = promoService.addPromo(entity);
        PromoDto responseDto = modelMapper.map(entity, PromoDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // edit promo
    @PutMapping(value = "/{promoId}")
    public ResponseEntity<PromoDto> updatePromo(@PathVariable("promoId") Integer promoId,
                                                @RequestBody PromoDto promoDTO) {
        Promo entity = modelMapper.map(promoDTO, Promo.class);
        Promo updatedPromo = promoService.editPromo(promoId, entity);
        PromoDto responseDto = modelMapper.map(updatedPromo, PromoDto.class);
        return ResponseEntity.ok(responseDto);
    }

    // delete plane
    @RequestMapping(value = "/{promoId}", method = RequestMethod.DELETE)
    public ResponseEntity<PromoDto> deletePromo(@PathVariable("promoId") Integer promoId){
        Promo deletedPromo = promoService.deletePromo(promoId);
        PromoDto promoDto = modelMapper.map(deletedPromo, PromoDto.class);
        return ResponseEntity.ok(promoDto);
    }

}
