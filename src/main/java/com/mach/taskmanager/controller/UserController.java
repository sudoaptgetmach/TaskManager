package com.mach.taskmanager.controller;

import com.mach.taskmanager.domain.user.User;
import com.mach.taskmanager.domain.user.UserListData;
import com.mach.taskmanager.repository.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = repository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @SecurityRequirement(name = "bearer-key")
    @GetMapping("/admin/list/users")
    public ResponseEntity<Page<UserListData>> userList(@PageableDefault(sort = {"id"}) Pageable paginable) {
        var page = repository.findAll(paginable).map(UserListData::new);
        return ResponseEntity.ok(page);
    }
}