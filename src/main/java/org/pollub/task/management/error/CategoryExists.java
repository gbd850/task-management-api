package org.pollub.task.management.error;

public class CategoryExists extends OperationInvalidError {
    protected CategoryExists(String message) {
        super(ErrorCode.BAD_REQUEST, message);
    }

    public static CategoryExists byName(String name) {
        return new CategoryExists(String.format("Category with name: %s already exists", name));
    }
}
