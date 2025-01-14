package org.pollub.task.management.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class ProjectError extends Error {

    private ErrorCode errorCode;

}
