package org.pollub.task.management.model;

public record Task(
        Integer id,
        String name,
        Boolean isResolved
) {
}
