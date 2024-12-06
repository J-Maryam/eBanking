package org.youcode.ebanking.dtos;

public record UserResponseDto(
        Long id,
        String username,
//        String password,
        RoleDTO role
) {
}
