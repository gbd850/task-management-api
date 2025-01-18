package org.pollub.task.management.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pollub.task.management.model.Category;
import org.pollub.task.management.persistance.SimpleMappableEntity;

import java.util.Collections;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class CategoryEntity implements SimpleMappableEntity<Category> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    @Setter
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    private List<TaskEntity> tasks;

    public CategoryEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.tasks = Collections.emptyList();
    }

    @PreRemove
    private void preRemove() {
        tasks.forEach(task -> task.setCategory(null));
    }

    @Override
    public Category toDomain() {
        return new Category(id, name);
    }
}
