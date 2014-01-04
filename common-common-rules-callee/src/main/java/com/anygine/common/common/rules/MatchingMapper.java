package com.anygine.common.common.rules;

import java.util.Collection;

import com.anygine.common.common.function.Match;
import com.anygine.common.common.function.MatchPredicate;

public interface MatchingMapper<T1, T2> {
  Match map(
      Collection<T1> src, MatchPredicate<T1> srcPredicate, 
      MatchPredicate<T2> dstPredicate, T2 defaultForSrcPredicateMatch);
  Match map(
      T1 srcItem, MatchPredicate<T1> srcPredicate, 
      MatchPredicate<T2> dstPredicate, T2 defaultForSrcPredicateMatch);
}
