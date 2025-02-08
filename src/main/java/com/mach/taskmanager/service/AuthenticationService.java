package com.mach.taskmanager.service;

import com.mach.taskmanager.domain.user.AuthData;
import com.mach.taskmanager.domain.user.User;
import com.mach.taskmanager.domain.user.roles.Role;
import com.mach.taskmanager.domain.user.roles.RoleName;
import com.mach.taskmanager.repository.RoleRepository;
import com.mach.taskmanager.repository.UserRepository;
import com.mach.taskmanager.security.JWTTokenData;
import com.mach.taskmanager.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager manager;
    private final TokenService tokenService;

    public AuthenticationService(UserRepository repository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager manager, TokenService tokenService) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.manager = manager;
        this.tokenService = tokenService;
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.valueOf("ROLE_USER"))
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoles(Collections.singleton(userRole));

        return repository.save(user);
    }

    public JWTTokenData login(AuthData data) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = manager.authenticate(authenticationToken);
        System.out.println("Authorities do usuário autenticado: " + auth.getAuthorities());

        var tokenJWT = tokenService.gerarToken((User) auth.getPrincipal());

        return new JWTTokenData(tokenJWT);
    }

}
