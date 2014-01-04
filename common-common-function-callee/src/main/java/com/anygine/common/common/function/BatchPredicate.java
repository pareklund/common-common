package com.anygine.common.common.function;

import java.util.Collection;

public interface BatchPredicate<T> extends Predicate<T> {
  boolean apply(Collection<T> t);
}
