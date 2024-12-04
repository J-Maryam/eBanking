package org.youcode.ebanking.services;

import org.youcode.ebanking.dtos.UserRegistrationDto;
import org.youcode.ebanking.dtos.UserResponseDto;

public interface UserService {
    UserResponseDto registerUser(UserRegistrationDto dto);
}
