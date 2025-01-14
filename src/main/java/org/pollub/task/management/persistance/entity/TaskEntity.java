package org.pollub.task.management.persistance.entity;

import jakarta.persistence.*;
import lombok.*;
import org.pollub.task.management.model.Task;
import org.pollub.task.management.persistance.SimpleMappableEntity;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class TaskEntity implements SimpleMappableEntity<Task> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Setter
    private String name;
    @Column(nullable = false)
    @Setter
    private Boolean isResolved;

    @Override
    public Task toDomain() {
        return new Task(id, name, isResolved);
    }
}
