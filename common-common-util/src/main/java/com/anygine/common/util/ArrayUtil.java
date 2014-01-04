package com.anygine.common.util;

public class ArrayUtil {

  private ArrayUtil() {}
  
  public static int max(int[] values) {
    int max = 0;
    for (int value : values) {
      if (value > max) {
        max = value;
      }
    }
    return max;
  }
  
  public static boolean contains(int[] values, int value, int maxPos) {
    for (int i = 0; i < maxPos; i++) {
      if (values[i] == value) {
        return true;
      }
    }
    return false;
  }
  
}
