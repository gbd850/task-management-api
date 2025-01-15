package org.pollub.task.management.error.advice;

import org.pollub.task.management.error.ErrorMessage;
import org.pollub.task.management.error.NotFoundError;
import org.pollub.task.management.error.OperationInvalidError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TaskControllerExceptionHandler {

    @ExceptionHandler(value = NotFoundError.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(NotFoundError error) {
        return new ErrorMessage(HttpStatus.NOT_FOUND, error.getMessage());
    }

    @ExceptionHandler(value = OperationInvalidError.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage resourceNotFoundException(OperationInvalidError error) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST, error.getMessage());
    }

}
