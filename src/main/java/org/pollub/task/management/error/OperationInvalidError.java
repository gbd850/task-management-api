package org.pollub.task.management.error;

import lombok.Getter;

@Getter
public class OperationInvalidError extends ProjectError {

    private final String message;

    protected OperationInvalidError(ErrorCode errorCode, String message) {
        super(errorCode);
        this.message = message;
    }

}
