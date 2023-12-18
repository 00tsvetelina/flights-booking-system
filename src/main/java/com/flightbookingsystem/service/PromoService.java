package com.flightbookingsystem.service;

import com.flightbookingsystem.repository.PromoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromoService {

    private final PromoRepository promoRepository;
}
