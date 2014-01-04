package com.anygine.common.common.function;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Match {
  
  NO_MATCH(false, null), WEAK_MATCH(true, MatchStrength.WEAK), 
  STRONG_MATCH(true, MatchStrength.STRONG);
  
  private static final Tuple<Match, Object> NO_MATCH_TUPLE = 
      new Tuple<Match, Object>(NO_MATCH, null);
  
  private final boolean match;
  private final MatchStrength strength;
  
  private Match(boolean match, MatchStrength strength) {
    this.match = match;
    this.strength = strength;
  }

  public boolean isMatch() {
    return match;
  }

  public MatchStrength getStrength() {
    return strength;
  }

  public static <T> Map<Match, Collection<T>> newMap() {
    Map<Match, Collection<T>> map = new HashMap<Match, Collection<T>>();
    map.put(NO_MATCH, Collections.<T>emptyList());
    map.put(WEAK_MATCH, Collections.<T>emptyList());
    map.put(STRONG_MATCH, Collections.<T>emptyList());
    return map;
  }
  
  @SuppressWarnings("unchecked")
  public static <T> Tuple<Match, T> noMatchTuple() {
    return (Tuple<Match, T>) NO_MATCH_TUPLE;
  }
  
  public static Match min(Match m1, Match m2) {
    return (m1.ordinal() <= m2.ordinal() ? m1 : m2);
  }
}
