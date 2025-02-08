package com.mach.taskmanager.controller;

import com.mach.taskmanager.domain.user.User;
import com.mach.taskmanager.domain.user.UserListData;
import com.mach.taskmanager.domain.user.roles.Role;
import com.mach.taskmanager.domain.user.roles.RoleName;
import com.mach.taskmanager.repository.RoleRepository;
import com.mach.taskmanager.repository.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping
public class UserController {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository repository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.valueOf("ROLE_USER"))
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoles(Collections.singleton(userRole));
        User savedUser = repository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @SecurityRequirement(name = "bearer-key")
    @GetMapping("/admin/users/list")
    public ResponseEntity<Page<UserListData>> userList(@PageableDefault(sort = {"id"}) Pageable paginable) {
        var page = repository.findAll(paginable).map(UserListData::new);
        return ResponseEntity.ok(page);
    }
}