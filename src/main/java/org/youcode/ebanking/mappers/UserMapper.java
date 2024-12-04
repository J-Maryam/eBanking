package org.youcode.ebanking.mappers;

import org.mapstruct.Mapper;
import org.youcode.ebanking.dtos.UserRegistrationDto;
import org.youcode.ebanking.models.User;

@Mapper
public interface UserMapper {
    User toEntity(UserRegistrationDto dto);
    UserRegistrationDto toDTO(User user);
}
