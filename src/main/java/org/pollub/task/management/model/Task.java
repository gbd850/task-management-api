package org.pollub.task.management.model;

public record Task(
        Integer id,
        String name,
        String categoryName,
        Boolean isResolved
) {
}
