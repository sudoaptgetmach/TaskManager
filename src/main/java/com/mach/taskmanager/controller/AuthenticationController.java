package com.mach.taskmanager.controller;

import com.mach.taskmanager.domain.user.AuthData;
import com.mach.taskmanager.domain.user.User;
import com.mach.taskmanager.security.JWTTokenData;
import com.mach.taskmanager.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<JWTTokenData> efetuarLogin(@RequestBody @Valid AuthData dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.password());
        var auth = manager.authenticate(authenticationToken);
        System.out.println("Authorities do usuário autenticado: " + auth.getAuthorities());

        var tokenJWT = tokenService.gerarToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new JWTTokenData(tokenJWT));
    }
}