package com.anygine.common.common.function;

import java.util.Collection;

public abstract class BatchMatchPredicateBase<T> implements BatchMatchPredicate<T> {

  @Override
  public Match apply(Collection<T> ts) {
    Match highestMatch = Match.NO_MATCH;
    for (T t : ts) {
      Match currentMatch = apply(t);
      if (currentMatch.compareTo(highestMatch) > 0) {
        highestMatch = currentMatch;
      }
    }
    return highestMatch;
  }

}
