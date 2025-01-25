package com.mach.taskmanager.domain.categories;

public record CategoryListData(Long id,
                               String name,
                               String description) {

    public CategoryListData(Categories categories) {
        this(categories.getId(), categories.getName(), categories.getDescription());
    }

}
