package org.pollub.task.management.model;

import jakarta.validation.constraints.NotBlank;

public record TaskRequest(
        @NotBlank
        String name,
        Boolean isResolved
) {
}
