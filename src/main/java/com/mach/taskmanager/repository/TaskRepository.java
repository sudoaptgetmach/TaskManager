package com.mach.taskmanager.repository;

import com.mach.taskmanager.domain.tasks.Tasks;
import com.mach.taskmanager.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
    List<Tasks> findByUser(User user);
    Page<Tasks> findByUser(User user, Pageable pageable);
    Optional<Tasks> findByIdAndUser(Long id, User user);
}
