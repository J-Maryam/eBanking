package org.youcode.ebanking.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.ebanking.dtos.UserRegistrationDto;
import org.youcode.ebanking.dtos.UserResponseDto;
import org.youcode.ebanking.services.UserService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody @Valid UserRegistrationDto dto) {
        UserResponseDto createdDTO = userService.registerUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO);
    }
}
