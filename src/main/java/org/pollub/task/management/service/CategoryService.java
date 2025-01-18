package org.pollub.task.management.service;

import lombok.RequiredArgsConstructor;
import org.pollub.task.management.error.CategoryExists;
import org.pollub.task.management.error.CategoryNotFound;
import org.pollub.task.management.model.Category;
import org.pollub.task.management.model.CategoryRequest;
import org.pollub.task.management.persistance.entity.CategoryEntity;
import org.pollub.task.management.persistance.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService extends MappableService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return callRepositoryMappingToDomainList(categoryRepository::findAll);
    }

    public Category createCategory(CategoryRequest request) {
        if (categoryRepository.findByName(request.name()).isPresent()) {
            throw CategoryExists.byName(request.name());
        }

        final CategoryEntity createdCategory = new CategoryEntity(null, request.name());

        return callRepositoryMappingToDomain(() -> categoryRepository.save(createdCategory));
    }

    public Category editCategory(Integer id, CategoryRequest request) {

        final var maybeCategory = categoryRepository.findByName(request.name());

        if (maybeCategory.isPresent()) {
            throw CategoryExists.byName(request.name());
        }

        final CategoryEntity categoryToEdit = categoryRepository.findById(id).orElseThrow(() -> CategoryNotFound.byId(id));

        categoryToEdit.setName(request.name());

        return callRepositoryMappingToDomain(() -> categoryRepository.save(categoryToEdit));
    }

    public void deleteCategory(Integer id) {
        final var categoryToDelete = categoryRepository.findById(id).orElseThrow(() -> CategoryNotFound.byId(id));
        categoryRepository.delete(categoryToDelete);
    }
}
