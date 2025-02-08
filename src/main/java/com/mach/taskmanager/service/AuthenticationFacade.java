package com.mach.taskmanager.service;

import com.mach.taskmanager.domain.user.User;
import com.mach.taskmanager.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {

    private final UserRepository repository;

    public AuthenticationFacade(UserRepository repository) {
        this.repository = repository;
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
