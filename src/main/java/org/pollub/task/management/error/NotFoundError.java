package org.pollub.task.management.error;

import lombok.Getter;

public abstract class NotFoundError extends ProjectError {

    @Getter
    private final String message;

    protected NotFoundError(ErrorCode errorCode, String message) {
        super(errorCode);
        this.message = message;
    }
}
