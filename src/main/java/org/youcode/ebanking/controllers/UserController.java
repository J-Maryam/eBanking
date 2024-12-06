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
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/my_profile/{username}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> updatePassword(@PathVariable String username, @RequestBody PasswordUpdateDTO dto, Authentication authentication) {
        if (!authentication.getName().equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can only update your password");
        }
        userService.updatePassword(username, dto.oldPassword(), dto.newPassword());
        return ResponseEntity.ok("Password updated successfully");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/myLoans")
    public String myLoans() {
        return "Here are your loan details.";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/myCards")
    public String myCards() {
        return "Here are your card details.";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/myAccount")
    public String myAccount() {
        return "Here are your account details.";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/myBalance")
    public String myBalance() {
        return "Here is your total balance.";
    }
}
