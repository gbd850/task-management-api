package org.pollub.task.management.error;

import lombok.Getter;

@Getter
public abstract class NotFoundError extends ProjectError {

    private final String message;

    protected NotFoundError(ErrorCode errorCode, String message) {
        super(errorCode);
        this.message = message;
    }
}
