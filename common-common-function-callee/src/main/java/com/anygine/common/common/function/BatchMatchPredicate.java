package com.anygine.common.common.function;

import java.util.Collection;

public interface BatchMatchPredicate<T> extends MatchPredicate<T> {
  Match apply(Collection<T> ts);
}
