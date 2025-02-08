package com.mach.taskmanager.service;

import com.mach.taskmanager.domain.tasks.*;
import com.mach.taskmanager.domain.user.User;
import com.mach.taskmanager.exception.TaskNotFoundException;
import com.mach.taskmanager.repository.CategoryRepository;
import com.mach.taskmanager.repository.TaskRepository;
import com.mach.taskmanager.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class TaskService {

    private final AuthenticationFacade authenticationFacade;
    private final TaskRepository repository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public TaskService(AuthenticationFacade authenticationFacade, TaskRepository repository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.authenticationFacade = authenticationFacade;
        this.repository = repository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Adiciona uma nova task para o usuário autenticado.
     */
    public ResponseEntity<TaskDataDetails> addTask(TaskCreationData data, UriComponentsBuilder uriBuilder) {
        User user = authenticationFacade.getAuthenticatedUser();
        var task = new Tasks(data, userRepository);
        repository.save(task);

        var uri = uriBuilder.path("/tasks/{id}").buildAndExpand(task.getId()).toUri();
        return ResponseEntity.created(uri).body(new TaskDataDetails(task));
    }

    /**
     * Retorna todas as tasks do usuário autenticado.
     */
    public Page<TaskListData> taskList(Pageable pageable) {
        User user = authenticationFacade.getAuthenticatedUser();
        return repository.findByUser(user, pageable).map(TaskListData::new);
    }

    /**
     * Atualiza uma task, garantindo que o usuário só possa modificar suas próprias tasks.
     */
    public TaskListData updateTask(TaskUpdateData data) {
        User user = authenticationFacade.getAuthenticatedUser();
        var task = repository.findByIdAndUser(data.id(), user)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa não encontrada ou não pertence ao usuário"));

        task.atualizarInformacoes(data, categoryRepository);
        repository.save(task);
        return new TaskListData(task);
    }

    /**
     * Deleta uma task do usuário autenticado.
     */
    public ResponseEntity<Void> deleteTask(Long id) {
        User user = authenticationFacade.getAuthenticatedUser();
        var task = repository.findByIdAndUser(id, user)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa não encontrada ou não pertence ao usuário"));

        repository.delete(task);
        return ResponseEntity.noContent().build();
    }
}
