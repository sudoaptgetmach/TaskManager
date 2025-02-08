package com.mach.taskmanager.controller;

import com.mach.taskmanager.domain.tasks.TaskCreationData;
import com.mach.taskmanager.domain.tasks.TaskDataDetails;
import com.mach.taskmanager.domain.tasks.TaskListData;
import com.mach.taskmanager.domain.tasks.TaskUpdateData;
import com.mach.taskmanager.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("tasks")
@SecurityRequirement(name = "bearer-key")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<TaskDataDetails> addTask(@RequestBody @Valid TaskCreationData data, UriComponentsBuilder uriBuilder) {
        return service.addTask(data, uriBuilder);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<TaskListData>> taskList(@PageableDefault(sort = {"id"}) Pageable paginable) {
        return ResponseEntity.ok(service.taskList(paginable));
    }

    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<TaskListData> atualizar(@RequestBody @Valid TaskUpdateData dados) {
        return ResponseEntity.ok(service.updateTask(dados));
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        return service.deleteTask(id);
    }
}
