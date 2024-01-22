package com.flightbookingsystem.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInputDto extends UserDto {

    @NotNull(message = "password is mandatory")
    @NotBlank(message = "password is mandatory")
    @Size(min = 6, max = 16, message = "password must be between 6 and 16 characters")
    private String password;

}
