package org.youcode.ebanking.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.youcode.ebanking.dtos.PasswordUpdateDTO;
import org.youcode.ebanking.services.UserService;

@RestController
@RequestMapping("/api/my_profile")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/{username}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> updatePassword(@PathVariable String username, @RequestBody PasswordUpdateDTO dto, Authentication authentication) {
        if (!authentication.getName().equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can only update your password");
        }
        userService.updatePassword(username, dto.oldPassword(), dto.newPassword());
        return ResponseEntity.ok("Password updated successfully");
    }
}
