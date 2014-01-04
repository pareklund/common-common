package com.anygine.common.common.rules;

public interface RuleManager<T> {
  Rules<T> getRules();
  Rules<T> alwaysTrueRules();
  DismissalRule<T> getDismissalRule(long id);
  IndiciaRule<T> getIndiciaRule(long id);
}
