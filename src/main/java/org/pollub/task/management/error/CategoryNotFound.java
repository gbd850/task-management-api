package org.pollub.task.management.error;

public class CategoryNotFound extends NotFoundError {
  protected CategoryNotFound(String message) {
    super(ErrorCode.NOT_FOUND, message);
  }

  public static CategoryNotFound byName(String name) {
    return new CategoryNotFound(String.format("Category with name: %s does not exist", name));
  }

  public static CategoryNotFound byId(Integer id) {
    return new CategoryNotFound(String.format("Category with id: %d does not exist", id));
  }
}
