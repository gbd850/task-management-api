package org.pollub.task.management.service;

import lombok.RequiredArgsConstructor;
import org.pollub.task.management.error.TaskNotFound;
import org.pollub.task.management.model.Task;
import org.pollub.task.management.model.TaskRequest;
import org.pollub.task.management.persistance.entity.TaskEntity;
import org.pollub.task.management.persistance.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskService extends MappableService {

    private final TaskRepository taskRepository;

    public List<Task> getTasks() {
        return callRepositoryMappingToDomainList(taskRepository::findAll);
    }

    public Task createTask(TaskRequest request) {
        final TaskEntity createdTask = new TaskEntity(null, request.name(), false);
        return callRepositoryMappingToDomain(() -> taskRepository.save(createdTask));
    }

    public Task editTask(Integer id, TaskRequest request) {
        final var taskToEdit = taskRepository.findById(id).orElseThrow(() -> TaskNotFound.byId(id));
        taskToEdit.setName(Objects.requireNonNullElse(request.name(), taskToEdit.getName()));
        taskToEdit.setIsResolved(Objects.requireNonNullElse(request.isResolved(), taskToEdit.getIsResolved()));


        return callRepositoryMappingToDomain(() -> taskRepository.save(taskToEdit));
    }

    public void deleteTask(Integer id) {
        final var taskToDelete = taskRepository.findById(id).orElseThrow(() -> TaskNotFound.byId(id));
        taskRepository.delete(taskToDelete);
    }
}