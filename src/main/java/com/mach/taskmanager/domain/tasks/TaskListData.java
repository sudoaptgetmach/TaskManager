package com.mach.taskmanager.domain.tasks;

import com.mach.taskmanager.domain.categories.Categories;
import com.mach.taskmanager.domain.tasks.enums.Priority;
import com.mach.taskmanager.domain.tasks.enums.Status;
import com.mach.taskmanager.domain.user.UserListData;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record TaskListData(Long id,
                           String title,
                           String description,
                           LocalDateTime due_date,
                           Priority priority,
                           Status status,
                           Categories category,
                           UserListData user,
                           Timestamp created_at
) {

    public TaskListData(Tasks tasks) {
        this(tasks.getId(), tasks.getTitle(), tasks.getDescription(), tasks.getDue_date(), tasks.getPriority(), tasks.getStatus(), tasks.getCategory(), new UserListData(tasks.getUser()), tasks.getCreated_at());
    }

}
