package com.anygine.common.common.rules;

import com.anygine.common.common.function.Predicate;

public interface DismissalRule<T> extends Rule<T>, Predicate<T> {
}