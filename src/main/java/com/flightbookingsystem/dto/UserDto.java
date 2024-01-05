package com.flightbookingsystem.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class UserDto {

    @Valid

    private Integer id;

    @NotNull(message = "email is mandatory")
    @NotBlank(message = "email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "userName is mandatory")
    @NotBlank(message = "userName is mandatory")
    @Size(min = 6, max = 60, message = "userName must be between 6 and 30 characters")
    private String userName;

    @NotNull(message = "password is mandatory")
    @NotBlank(message = "password is mandatory")
    @Size(min = 6, max = 16, message = "password must be between 6 and 16 characters")
    private String password;

    private Boolean isBanned;

    private List<String> roles;


}
