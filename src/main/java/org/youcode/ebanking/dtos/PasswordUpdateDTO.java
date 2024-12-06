package org.youcode.ebanking.dtos;

public record PasswordUpdateDTO(
        String oldPassword,
        String newPassword
) {
}
