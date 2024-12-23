package org.youcode.ebanking.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.ebanking.dtos.RoleDTO;
import org.youcode.ebanking.dtos.UserRegistrationDto;
import org.youcode.ebanking.dtos.UserResponseDto;
import org.youcode.ebanking.exceptions.IncorrectPasswordException;
import org.youcode.ebanking.exceptions.RoleNotFoundException;
import org.youcode.ebanking.exceptions.UsernameAlreadyExistsException;
import org.youcode.ebanking.exceptions.UsernameNotFoundException;
import org.youcode.ebanking.mappers.UserMapper;
import org.youcode.ebanking.models.AppUser;
import org.youcode.ebanking.models.Role;
import org.youcode.ebanking.repositories.RoleRepository;
import org.youcode.ebanking.repositories.UserRepository;

import java.util.List;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto registerUser(UserRegistrationDto dto) {
        if (userRepository.findByUsername(dto.username()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username '" + dto.username() + "' already exists");
        }

        Role role = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Role 'ROLE_USER' not found"));

        AppUser appUser = userMapper.toEntity(dto);
        appUser.setRole(role);
        appUser.setPassword(passwordEncoder.encode(dto.password()));
        AppUser saved = userRepository.save(appUser);
        return userMapper.toDTO(saved);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<AppUser> users = userRepository.findByRoleName("ROLE_USER");
        return users.stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @Override
    public UserResponseDto getUserByUsername(String username, String role) {
        AppUser user = userRepository.findByUsernameAndRole_Name(username, "ROLE_USER")
                .orElseThrow(() -> new UsernameNotFoundException("User with username '" + username + "' and role 'USER' not found"));

        return userMapper.toDTO(user);
    }

    @Override
    public void deleteUserByUsername(String username) {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username '" + username + "' not found"));
        userRepository.delete(user);
    }

    @Override
    public UserResponseDto updateUserRole(String username, RoleDTO roleDTO) {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username '" + username + "' not found"));

        Role newRole = roleRepository.findByName(roleDTO.name())
                .orElseThrow(() -> new RoleNotFoundException("Role '" + roleDTO.name() + "' not found"));

        user.setRole(newRole);
        AppUser updatedUser = userRepository.save(user);

        return userMapper.toDTO(updatedUser);
    }

    @Override
    public void updatePassword(String username, String oldPassword, String newPassword) {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username '" + username + "' not found"));

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IncorrectPasswordException("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
