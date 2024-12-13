package com.solvd.lambda;

import java.util.Objects;
import java.util.function.Predicate;

public interface IsEqualToZero<T> extends Predicate<T> {
    @Override
    default boolean test(T t){
        return Objects.nonNull(t) &&
                switch (t){
                    case Integer i -> i==0;
                    case Double d -> d==0;
                    default -> false;
                };
    };
}
