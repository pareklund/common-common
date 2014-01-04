package com.anygine.common.util;

public class LogUtil {
  
  public static enum Level { DEBUG, INFO, ERROR };
  
  private static Level level = Level.INFO;

  public static void setLevel(Level level) {
    LogUtil.level = level;
  }
  
  private LogUtil() {}
  
  public static String debug(String logName, String message) {
    if (level == Level.DEBUG) {
      String logString = "[" + logName + "] " + message;
      System.out.println(logString);
      return logString;
    }
    return null;
  }

  public static String info(String logName, String message) {
    if (level != Level.ERROR) {
      String logString = "[" + logName + "] " + message;
      System.out.println(logString);
      return logString;
    }
    return null;
  }

  public static String error(String logName, String message) {
    String logString = "[" + logName + "] " + message;
    System.err.println(logString);
    return logString;
  }
}
