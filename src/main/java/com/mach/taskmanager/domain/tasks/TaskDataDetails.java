package com.mach.taskmanager.domain.tasks;

import com.mach.taskmanager.domain.tasks.enums.Priority;
import com.mach.taskmanager.domain.tasks.enums.Status;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record TaskDataDetails(Long id,
                              String title,
                              String description,
                              LocalDateTime due_date,
                              Priority priority,
                              Status status,
                              Long category_id,
                              Timestamp created_at) {

    public TaskDataDetails(Tasks task) {
        this(task.getId(), task.getTitle(), task.getDescription(), task.getDue_date(), task.getPriority(), task.getStatus(), task.getCategory().getId(), task.getCreated_at());
    }

}