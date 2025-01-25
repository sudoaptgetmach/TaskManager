package com.mach.taskmanager.domain.categories;

public record CategoryDataDetails(String name,
                                  String description) {
    public CategoryDataDetails(Categories category) {
        this(category.getName(), category.getDescription());
    }
}
