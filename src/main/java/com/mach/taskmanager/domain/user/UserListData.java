package com.mach.taskmanager.domain.user;

import java.sql.Timestamp;

public record UserListData(Long id,
                           String name,
                           String email,
                           Timestamp created_at) {

    public UserListData(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getCreated_at());
    }

}
