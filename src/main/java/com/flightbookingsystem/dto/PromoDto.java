package com.flightbookingsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flightbookingsystem.model.Ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PromoDto {

    private Integer id;

    private String promoCode;

    private Integer percentOff;

    private LocalDateTime durationStart;

    private LocalDateTime durationEnd;

    private Boolean singleUse;

    private List<Ticket> tickets;
}
