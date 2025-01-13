package com.solvd.supermarket.lambda;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

@FunctionalInterface
public interface FilterAndCompareStream<T>{
    Optional<T> filterAndCompare(Stream<T> stream, Predicate<T> predicate, Comparator<? super T> comparator);
}
