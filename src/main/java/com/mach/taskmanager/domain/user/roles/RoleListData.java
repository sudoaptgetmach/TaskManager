package com.mach.taskmanager.domain.user.roles;

public record RoleListData(Long id,
                           RoleName name) {

    public RoleListData(Role role) {
        this(role.getId(), role.getName());
    }
}
