package com.mach.taskmanager.controller;

import com.mach.taskmanager.domain.tasks.TaskCreationData;
import com.mach.taskmanager.domain.tasks.TaskDataDetails;
import com.mach.taskmanager.domain.tasks.Tasks;
import com.mach.taskmanager.repository.TaskRepository;
import com.mach.taskmanager.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private UserRepository userrepository;

    @PostMapping
    @Transactional
    public ResponseEntity addTask(@RequestBody @Valid TaskCreationData data, UriComponentsBuilder uriBuilder) {

        var task = new Tasks(data, userrepository);
        var uri = uriBuilder.path("/tasks/{id}").buildAndExpand(task.getId()).toUri();

        repository.save(task);
        return ResponseEntity.created(uri).body(new TaskDataDetails(task));
    }
}
