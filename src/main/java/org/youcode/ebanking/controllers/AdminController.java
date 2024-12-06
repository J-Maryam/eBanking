package org.youcode.ebanking.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.youcode.ebanking.dtos.RoleDTO;
import org.youcode.ebanking.dtos.UserResponseDto;
import org.youcode.ebanking.models.AppUser;
import org.youcode.ebanking.security.SecurityConfig;
import org.youcode.ebanking.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto> getUserByUserName(@PathVariable String username) {
        UserResponseDto user = userService.getUserByUsername(username, "ROLE_USER");
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{username}/updateRole")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto> updateRoleUser(@PathVariable String username, @RequestBody RoleDTO roleDTO) {
        UserResponseDto updatedUser = userService.updateUserRole(username, roleDTO);
        return ResponseEntity.ok(updatedUser);
    }

}
