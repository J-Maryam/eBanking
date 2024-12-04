package org.youcode.ebanking.mappers;

import org.mapstruct.Mapper;
import org.youcode.ebanking.dtos.UserRegistrationDto;
import org.youcode.ebanking.dtos.UserResponseDto;
import org.youcode.ebanking.models.AppUser;

@Mapper(componentModel = "spring")
public interface UserMapper {
    AppUser toEntity(UserRegistrationDto dto);
    UserResponseDto toDTO(AppUser appUser);
}
