package org.pollub.task.management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class ApiMapper {
    public <T> ResponseEntity<?> handleTo200(Supplier<T> callback) {
        return ResponseEntity.status(HttpStatus.OK).body(callback.get());
    }

    public <T> ResponseEntity<T> handleTo201(Supplier<T> callback) {
        return ResponseEntity.status(HttpStatus.CREATED).body(callback.get());
    }

    public ResponseEntity<Void> handleTo204(Runnable callback) {
        callback.run();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
