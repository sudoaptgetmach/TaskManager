package com.mach.taskmanager.repository;

import com.mach.taskmanager.domain.user.roles.Role;
import com.mach.taskmanager.domain.user.roles.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
    Optional<Role> findByName(RoleName roleName);

}
