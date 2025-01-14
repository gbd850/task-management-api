package org.pollub.task.management.error;

public class TaskNotFound extends NotFoundError {

    protected TaskNotFound(String message) {
        super(ErrorCode.NOT_FOUND, message);
    }

    public static TaskNotFound byId(Integer id) {
        return new TaskNotFound(String.format("Task with id: %d does not exist", id));
    }
}
