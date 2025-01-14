package org.pollub.task.management.service;

import org.pollub.task.management.persistance.SimpleMappableEntity;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

public abstract class MappableService {

    <T> List<T> callRepositoryMappingToDomainList(Supplier<Collection<? extends SimpleMappableEntity<T>>> entitySupplier) {
        return nullSafeStream(entitySupplier.get()).map(SimpleMappableEntity::toDomain).toList();
    }

    <T> T callRepositoryMappingToDomain(Supplier<? extends SimpleMappableEntity<T>> entitySupplier) {
        return Objects.requireNonNullElse(entitySupplier.get().toDomain(), null);
    }

    private <T> Stream<T> nullSafeStream(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return Stream.empty();
        }
        return collection.stream();
    }

}
