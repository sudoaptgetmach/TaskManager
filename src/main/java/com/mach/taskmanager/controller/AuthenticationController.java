package com.mach.taskmanager.controller;

import com.mach.taskmanager.domain.user.AuthData;
import com.mach.taskmanager.domain.user.User;
import com.mach.taskmanager.security.JWTTokenData;
import com.mach.taskmanager.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<JWTTokenData> efetuarLogin(@RequestBody @Valid AuthData data) {
        return ResponseEntity.ok(service.login(data));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        return ResponseEntity.ok(service.createUser(user));
    }
}