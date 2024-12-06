package org.youcode.ebanking.services;

import org.youcode.ebanking.dtos.UserRegistrationDto;
import org.youcode.ebanking.dtos.UserResponseDto;
import org.youcode.ebanking.models.AppUser;

import java.util.List;

public interface UserService {
    UserResponseDto registerUser(UserRegistrationDto dto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserByUsername(String username, String roleName);
}
