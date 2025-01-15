package org.pollub.task.management.model;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank
        String name
) {
}
