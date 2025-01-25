package com.mach.taskmanager.repository;

import com.mach.taskmanager.domain.tasks.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
}
