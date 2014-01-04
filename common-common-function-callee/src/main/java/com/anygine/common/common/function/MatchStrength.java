package com.anygine.common.common.function;

public enum MatchStrength {
  
  WEAK("Weak"), STRONG("Strong");
  
  private final String friendlyName;
  
  private MatchStrength(String friendlyName) {
    this.friendlyName = friendlyName;
  }
  
  public String getFriendlyName() {
    return friendlyName;
  }
  
  public static MatchStrength resolve(String friendlyName) {
    for (MatchStrength indiciaStrength : MatchStrength.values()) {
      if (indiciaStrength.friendlyName.equals(friendlyName)) {
        return indiciaStrength;
      }
    }
    return null;
  }
}
