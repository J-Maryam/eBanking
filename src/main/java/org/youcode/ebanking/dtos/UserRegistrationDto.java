package org.youcode.ebanking.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record UserRegistrationDto(

        @NotBlank
        @Column(unique = true)
        String username,
        @NotBlank
        String password
) {
}
