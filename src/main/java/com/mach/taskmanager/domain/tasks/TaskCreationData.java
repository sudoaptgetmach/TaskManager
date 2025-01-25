package com.mach.taskmanager.domain.tasks;

import com.mach.taskmanager.domain.tasks.enums.Priority;
import com.mach.taskmanager.domain.tasks.enums.Status;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record TaskCreationData(@NotBlank String title,
                               @Nullable String description,
                               @NotNull @FutureOrPresent LocalDateTime due_date,
                               Priority priority,
                               Status status,
                               Integer category_id,
                               Timestamp created_at,
                               Long user_id) {
}