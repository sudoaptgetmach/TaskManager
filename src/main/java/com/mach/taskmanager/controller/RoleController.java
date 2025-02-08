package com.mach.taskmanager.controller;

import com.mach.taskmanager.domain.user.User;
import com.mach.taskmanager.domain.user.UserListData;
import com.mach.taskmanager.domain.user.roles.Role;
import com.mach.taskmanager.domain.user.roles.RoleListData;
import com.mach.taskmanager.domain.user.roles.RoleUpdateData;
import com.mach.taskmanager.repository.RoleRepository;
import com.mach.taskmanager.repository.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/roles")
public class RoleController {

    private final UserRepository userRepository;
    private final RoleRepository repository;

    public RoleController(UserRepository userRepository, RoleRepository repository) {
        this.userRepository = userRepository;
        this.repository = repository;
    }

    @SecurityRequirement(name = "bearer-key")
    @GetMapping("/list")
    public ResponseEntity<List<RoleListData>> roleList() {
        List<RoleListData> roleList = repository.findAll().stream()
                .map(RoleListData::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(roleList);
    }

    @SecurityRequirement(name = "bearer-key")
    @PostMapping("/set")
    @Transactional
    public ResponseEntity<String> setRole(@RequestBody @Valid RoleUpdateData roleUpdateData) {
        User user = userRepository.findById(roleUpdateData.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Role role = repository.findById(roleUpdateData.roleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRoles(Collections.singleton(role));
        userRepository.save(user);

        return ResponseEntity.ok("Role updated successfully.\n" + new UserListData(user));
    }
}
