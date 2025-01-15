package org.pollub.task.management.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.pollub.task.management.model.CategoryRequest;
import org.pollub.task.management.model.TaskRequest;
import org.pollub.task.management.service.CategoryService;
import org.pollub.task.management.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;
    private final CategoryService categoryService;
    private final ApiMapper apiMapper;

    @GetMapping
    public ResponseEntity<?> getTasks() {
        return apiMapper.handleTo200(taskService::getTasks);
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody @Valid TaskRequest request) {
        return apiMapper.handleTo201(() -> taskService.createTask(request));
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> editTask(@RequestBody TaskRequest request, @PathVariable("id") Integer id) {
        return apiMapper.handleTo200(() -> taskService.editTask(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") Integer id) {
        return apiMapper.handleTo204(() -> taskService.deleteTask(id));
    }

    @PostMapping("category")
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryRequest request) {
        return apiMapper.handleTo201(() -> categoryService.createCategory(request));
    }

    @PatchMapping("category/{id}")
    public ResponseEntity<?> editCategory(@RequestBody @Valid CategoryRequest request, @PathVariable("id") Integer id) {
        return apiMapper.handleTo200(() -> categoryService.editCategory(id, request));
    }

    @DeleteMapping("category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Integer id) {
        return apiMapper.handleTo204(() -> categoryService.deleteCategory(id));
    }

}
