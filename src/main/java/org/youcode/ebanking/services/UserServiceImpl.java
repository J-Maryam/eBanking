package org.youcode.ebanking.services;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.ebanking.dtos.UserRegistrationDto;
import org.youcode.ebanking.dtos.UserResponseDto;
import org.youcode.ebanking.mappers.UserMapper;
import org.youcode.ebanking.models.AppUser;
import org.youcode.ebanking.repositories.UserRepository;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto registerUser(UserRegistrationDto dto) {
        if(userRepository.findByUsername(dto.username()).isPresent()){
            throw new EntityExistsException("Username '"+dto.username()+"' already exists");
        }
        AppUser appUser = userMapper.toEntity(dto);
        appUser.setRole("USER");
        AppUser saved = userRepository.save(appUser);
        return userMapper.toDTO(saved);
    }
}
