package com.mach.taskmanager.domain.tasks;

import jakarta.validation.constraints.NotNull;

public record TaskDeletionData(@NotNull Long id) {
}
