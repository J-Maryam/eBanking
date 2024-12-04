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
import org.youcode.ebanking.models.Role;
import org.youcode.ebanking.repositories.RoleRepository;
import org.youcode.ebanking.repositories.UserRepository;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto registerUser(UserRegistrationDto dto) {
        if (userRepository.findByUsername(dto.username()).isPresent()) {
            throw new RuntimeException("Username '" + dto.username() + "' already exists");
        }

        Role role = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Role 'ROLE_USER' not found"));

        AppUser appUser = userMapper.toEntity(dto);
        appUser.setRole(role);
        AppUser saved = userRepository.save(appUser);
        return userMapper.toDTO(saved);
    }
}
