package com.mach.taskmanager.domain.categories;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record CategoryCreationData(@NotBlank String name,
                                   @Nullable String description) {
}
