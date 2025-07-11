package com.wallet.walletapplication.controller;

import com.wallet.walletapplication.dto.CreateUserRequest;
import com.wallet.walletapplication.service.UserRegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody CreateUserRequest request) {
        registrationService.createUser(request);
        return ResponseEntity.ok("User registered successfully");
    }
}

