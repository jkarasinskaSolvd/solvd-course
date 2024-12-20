package com.solvd.supermarket.lambda;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

@FunctionalInterface
public interface MapAndCheckIfAnyMatchStream<T,R> {
    boolean mapAndCheckIfAnyMatch(Stream<T> stream, Function<T, R> function, Predicate<R> predicate);
}
