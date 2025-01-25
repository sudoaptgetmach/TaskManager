package com.mach.taskmanager.domain.tasks;

import com.mach.taskmanager.domain.categories.Categories;
import com.mach.taskmanager.domain.tasks.enums.Priority;
import com.mach.taskmanager.domain.tasks.enums.Status;
import com.mach.taskmanager.domain.user.User;
import com.mach.taskmanager.repository.UserRepository;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Table(name = "tasks")
@Entity(name = "Task")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @Nullable
    private String description;

    @NotNull
    private LocalDateTime due_date;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Categories category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Timestamp created_at;

    public Tasks(TaskCreationData data, UserRepository userRepository) {
        this.title = data.title();
        this.description = data.description();
        this.due_date = data.due_date();
        this.priority = data.priority();
        this.status = data.status();
        this.category = new Categories(data.category_id());
        this.user = userRepository.findById(data.user_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        this.created_at = data.created_at();
    }
}