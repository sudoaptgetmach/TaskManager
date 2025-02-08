package com.mach.taskmanager.repository;

import com.mach.taskmanager.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(@NotBlank String email);
}
