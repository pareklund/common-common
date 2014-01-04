package com.anygine.common.common.rules;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;


public class Rules<T> {

  private final Collection<IndiciaRule<T>> indiciaRules;
  private final Collection<DismissalRule<T>> dismissalRules;

  public Rules(
      Collection<IndiciaRule<T>> indiciaRules,
      Collection<DismissalRule<T>> dismissalRules) {
    this.indiciaRules = Collections.unmodifiableCollection(indiciaRules);
    this.dismissalRules = Collections.unmodifiableCollection(dismissalRules);
  }

  public Collection<IndiciaRule<T>> getIndiciaRules() {
    return indiciaRules;
  }

  public Collection<DismissalRule<T>> getDismissalRules() {
    return dismissalRules;
  }

  public String toString() {
    StringBuilder builder = new StringBuilder();
    final String newLine = System.getProperty("line.separator");
    builder.append(
        "Rules = {").append(newLine).append(
            "  indiciaRules: " + Arrays.toString(
                indiciaRules.toArray())).append(newLine).append(
                    "  dismissalRules: " + Arrays.toString(
                        dismissalRules.toArray())).append(newLine).append("}");
    return builder.toString();
  }
}
