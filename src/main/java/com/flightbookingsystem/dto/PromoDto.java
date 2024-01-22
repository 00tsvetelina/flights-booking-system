package com.flightbookingsystem.dto;

import com.flightbookingsystem.model.Ticket;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PromoDto {

    private Integer id;

    @NotNull
    @NotBlank
    private String promoCode;

    @NotNull
    @NotBlank
    private Integer percentOff;

    @NotNull
    @NotBlank
    private LocalDate durationEnd;

    @NotNull
    @NotBlank
    private Boolean singleUse;

    private List<Ticket> tickets;
}
