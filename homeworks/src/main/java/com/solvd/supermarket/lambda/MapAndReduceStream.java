package com.solvd.supermarket.lambda;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

@FunctionalInterface
public interface MapAndReduceStream<T,R> {
    R mapAndReduce(Stream<T> t, Function<T,R> function, BinaryOperator<R> reducer);
}
