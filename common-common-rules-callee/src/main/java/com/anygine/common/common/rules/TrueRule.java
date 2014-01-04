package com.anygine.common.common.rules;

import com.anygine.common.common.function.Match;

public class TrueRule<T> implements IndiciaRule<T> {

  public static final long ID = 1L;
  
  private static final String NAME = "True Rule";
  
  private static final String DESCRIPTION = 
      "When applying this rule, customer is always deemed temporary reportable. Used for Reason to Know scenarios.";
  
	@Override
	public Match apply(T customer) {
	  return Match.STRONG_MATCH;
	}

  @Override
  public long getId() {
    return ID;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public String getDescription() {
    return DESCRIPTION;
  }
}
