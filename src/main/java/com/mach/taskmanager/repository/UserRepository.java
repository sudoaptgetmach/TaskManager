package com.mach.taskmanager.repository;

import com.mach.taskmanager.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(@NotBlank String email);
}
