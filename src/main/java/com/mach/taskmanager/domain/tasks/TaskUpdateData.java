package com.mach.taskmanager.domain.tasks;

import com.mach.taskmanager.domain.categories.Categories;
import com.mach.taskmanager.domain.tasks.enums.Priority;
import com.mach.taskmanager.domain.tasks.enums.Status;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TaskUpdateData(@NotNull Long id,
                             String title,
                             String description,
                             LocalDateTime due_date,
                             Priority priority,
                             Status status,
                             Categories category) {
}
