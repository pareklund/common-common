package com.anygine.common.common.rules;

import java.util.Collection;
import java.util.Map;

import com.anygine.common.common.function.Match;
import com.anygine.common.common.function.MatchPredicate;

public abstract class MatchingMapperBase<T1, T2> implements MatchingMapper<T1, T2> {

//  @Override
  public Match map(
      Collection<T1> srcItems, MatchPredicate<T1> srcPredicate, 
      MatchPredicate<T2> dstPredicate, T2 defaultForSrcPredicateMatch) {
    Match highestMatch = Match.NO_MATCH;
    Map<Match, Collection<T2>> results = Match.newMap();
    for (T1 srcItem : srcItems) {
      Match currentMatch = map(
          srcItem, srcPredicate, dstPredicate, defaultForSrcPredicateMatch);
      if (highestMatch.compareTo(currentMatch) < 0) {
        highestMatch = currentMatch;
      }
    }
    return highestMatch;
  }
}
