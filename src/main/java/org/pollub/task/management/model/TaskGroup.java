package org.pollub.task.management.model;

import java.util.List;

public record TaskGroup(
        String categoryName,
        List<Task> tasks
) {
}
