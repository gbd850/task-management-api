package org.pollub.task.management.service;

import lombok.RequiredArgsConstructor;
import org.pollub.task.management.error.CategoryNotFound;
import org.pollub.task.management.error.TaskNotFound;
import org.pollub.task.management.model.Task;
import org.pollub.task.management.model.TaskGroup;
import org.pollub.task.management.model.TaskRequest;
import org.pollub.task.management.persistance.entity.CategoryEntity;
import org.pollub.task.management.persistance.entity.TaskEntity;
import org.pollub.task.management.persistance.repository.CategoryRepository;
import org.pollub.task.management.persistance.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService extends MappableService {

    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;

    public List<TaskGroup> getTasks() {
        return callRepositoryMappingToDomainList(taskRepository::findAll).stream()
                .collect(Collectors.groupingBy(Task::categoryName)).entrySet().stream()
                .map(taskEntry -> new TaskGroup(taskEntry.getKey(), taskEntry.getValue()))
                .toList();
    }

    public Task getTaskById(Integer id) {
        return callRepositoryMappingToDomainOptional(() -> taskRepository.findById(id)).orElseThrow(() -> TaskNotFound.byId(id));
    }

    public Task createTask(TaskRequest request) {
        final CategoryEntity categoryEntity = request.categoryName() != null ?
                categoryRepository.findByName(request.categoryName()).orElseThrow(() -> CategoryNotFound.byName(request.categoryName())) : null;

        final TaskEntity createdTask = new TaskEntity(null, request.name(), false, categoryEntity);
        return callRepositoryMappingToDomain(() -> taskRepository.save(createdTask));
    }

    public Task editTask(Integer id, TaskRequest request) {
        final CategoryEntity categoryChange = request.categoryName() != null ?
                categoryRepository.findByName(request.categoryName()).orElseThrow(() -> CategoryNotFound.byName(request.categoryName())) : null;

        final var taskToEdit = taskRepository.findById(id).orElseThrow(() -> TaskNotFound.byId(id));
        taskToEdit.setName(Objects.requireNonNullElse(request.name(), taskToEdit.getName()));
        taskToEdit.setIsResolved(Objects.requireNonNullElse(request.isResolved(), taskToEdit.getIsResolved()));

        if (categoryChange != null) {
            taskToEdit.setCategory(categoryChange);
        }
        return callRepositoryMappingToDomain(() -> taskRepository.save(taskToEdit));
    }

    public void deleteTask(Integer id) {
        final var taskToDelete = taskRepository.findById(id).orElseThrow(() -> TaskNotFound.byId(id));
        taskRepository.delete(taskToDelete);
    }
}