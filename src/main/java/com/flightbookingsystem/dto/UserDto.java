package com.flightbookingsystem.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

    @Valid

    private Integer id;

    @NotNull(message = "full name is mandatory")
    @NotBlank(message = "full name is mandatory")
    @Size(min = 6, max = 60, message = "fullName must be between 6 and 60 characters")
    private String firstAndLastNames;

    @NotNull(message = "email is mandatory")
    @NotBlank(message = "email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "userName is mandatory")
    @NotBlank(message = "userName is mandatory")
    @Size(min = 3, max = 15, message = "userName must be between 6 and 15 characters")
    private String userName;

    private Boolean isEnabled;

    private List<String> roles;


}
