package org.pollub.task.management.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pollub.task.management.model.Category;
import org.pollub.task.management.persistance.SimpleMappableEntity;

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

    @Override
    public Category toDomain() {
        return new Category(id, name);
    }
}
