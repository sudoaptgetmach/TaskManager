package com.mach.taskmanager.controller;

import com.mach.taskmanager.domain.tasks.*;
import com.mach.taskmanager.repository.CategoryRepository;
import com.mach.taskmanager.repository.TaskRepository;
import com.mach.taskmanager.repository.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TaskRepository repository;

    @Autowired
    private UserRepository userrepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/add")
    @Transactional
    public ResponseEntity addTask(@RequestBody @Valid TaskCreationData data, UriComponentsBuilder uriBuilder) {
        var task = new Tasks(data, userrepository);
        var uri = uriBuilder.path("/tasks/{id}").buildAndExpand(task.getId()).toUri();

        repository.save(task);
        return ResponseEntity.created(uri).body(new TaskDataDetails(task));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<TaskListData>> taskList(@PageableDefault(sort = {"id"}) Pageable paginable) {
        var page = repository.findAll(paginable).map(TaskListData::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid TaskUpdateData dados) {
        var task = repository.getReferenceById(dados.id());
        task.atualizarInformacoes(dados, categoryRepository);

        return ResponseEntity.ok(new TaskListData(task));
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity deleteTask(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
