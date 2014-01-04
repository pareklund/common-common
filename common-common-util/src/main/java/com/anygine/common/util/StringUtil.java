package com.anygine.common.util;

import java.util.List;
import java.util.Map;

public class StringUtil {

  public final static String NEW_LINE = System.getProperty("line.separator");
  
  private StringUtil() {}
  
  public static String listMapToString(
      Map<?, List<?>> mapOfLists, String keyName, String listName) {
    StringBuilder builder = new StringBuilder();
    write(builder, "{ ");
    if (mapOfLists != null) {
      for (Object key : mapOfLists.keySet()) {
        write(
            builder, "{ ", keyName, ": ", key.toString(), ", ", listName, ": ",
            listToString(mapOfLists.get(key)), " }");
      }
    }
    write(builder, " }");
    return builder.toString();
  }
  
  public static String objectMapToString(
      Map<?, ?> mapOfObjects, String keyName, String objectName) {
    StringBuilder builder = new StringBuilder();
    write(builder, "{ ");
    if (mapOfObjects != null) {
      for (Object key : mapOfObjects.keySet()) {
        write(
            builder, "{ ", keyName, ": ", key.toString(), ", ", objectName, ": ",
            mapOfObjects.get(key).toString(), " }");
      }
    }
    write(builder, " }");
    return builder.toString();
  }
  
  public static String listToString(List<?> list) {
    StringBuilder builder = new StringBuilder();
    write(builder, "{ ");
    String comma = "";
    for (Object object : list) {
      write(builder, comma, object.toString());
      comma = ", ";
    }
    write(builder, " }");
    return builder.toString();
  }

  private static StringBuilder write(
      StringBuilder stringBuilder, String ...strings) {
    for (String string : strings) {
      stringBuilder.append(string);
    }
    return stringBuilder;
  }
}
