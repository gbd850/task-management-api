package org.pollub.task.management.error;

import org.springframework.http.HttpStatus;

public record ErrorMessage(
        HttpStatus status,
        String message
) {
}
