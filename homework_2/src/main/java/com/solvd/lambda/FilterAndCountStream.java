package com.solvd.lambda;

import java.util.function.Predicate;
import java.util.stream.Stream;

@FunctionalInterface
public interface FilterAndCountStream<T> {
    long filterAndCount(Stream<T> stream, Predicate<T> predicate);
}
