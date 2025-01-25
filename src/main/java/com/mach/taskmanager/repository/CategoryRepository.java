package com.mach.taskmanager.repository;

import com.mach.taskmanager.domain.categories.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories, Long> {
}
