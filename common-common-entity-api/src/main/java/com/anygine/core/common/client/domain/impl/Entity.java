package com.anygine.core.common.client.domain.impl;

import playn.core.Json;


public interface Entity extends JsonWritable {

  long getId();
  int getVersion();
//  Entity entityCopy(Json.Object updateSpec);  
//  <T extends Comparable<T>> int compareTo(T attribute, T value);
//  void write(EntityWriter entityWriter, String key, TypeOfData typeOfData);
  
}
