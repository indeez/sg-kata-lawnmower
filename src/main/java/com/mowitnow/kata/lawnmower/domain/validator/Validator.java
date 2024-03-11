package com.mowitnow.kata.lawnmower.domain.validator;

import java.util.function.Predicate;

public interface Validator<T> extends Predicate<T> {
}
