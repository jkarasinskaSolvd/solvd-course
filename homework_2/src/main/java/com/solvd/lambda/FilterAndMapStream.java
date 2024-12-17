package com.solvd.lambda;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

@FunctionalInterface
public interface FilterAndMapStream<T,R> {
    Stream<R> filterAndMap(Stream<T> stream, Predicate<T> predicate, Function<T,R> function);
}
