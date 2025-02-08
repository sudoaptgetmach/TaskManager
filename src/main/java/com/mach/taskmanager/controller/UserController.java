package com.mach.taskmanager.controller;

import com.mach.taskmanager.domain.user.UserListData;
import com.mach.taskmanager.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @SecurityRequirement(name = "bearer-key")
    @GetMapping("/admin/users/list")
    public ResponseEntity<Page<UserListData>> userList(@PageableDefault(sort = {"id"}) Pageable pageable) {
        return ResponseEntity.ok(service.listUsers(pageable));
    }
}