package com.anygine.core.common.client.api;

import java.util.ArrayList;
import java.util.List;

import playn.core.Json;
import playn.core.Json.Object;

import com.anygine.core.common.codegen.api.JsonWritableInternal;

// TODO: Code generate!

// TODO: Place in same package in order to instantiate non-public classes
// TODO: Possibly place stuff in common base class
public abstract class JsonWritableFactoryBase implements JsonWritableFactory {

  @Override
  public <JW extends JsonWritableInternal> JW newInstance(
      Class<JW> clazz, Object jsonObj) {
    throw new IllegalArgumentException(
        "Cannot create JsonWritable of type: " 
            + jsonObj.getString("type"));
  }

  @Override
  public <JW extends JsonWritableInternal> List<JW> newList(
      Class<JW> clazz, Object jsonObj) {
    List<JW> collection = new ArrayList<JW>();
    Json.Array array = jsonObj.getArray("items");
    for (int i = 0; i < array.length(); i++) {
      collection.add(newInstance(clazz, array.getObject(i)));
    }
    return collection;
  }

  @Override
  public <JW extends JsonWritableInternal> JW[][] newArrayOfArrays(
      Class<JW> clazz, Object jsonObj) {
    Json.Array arrayOfArrays = jsonObj.getArray("items");
    int rowNum = arrayOfArrays.length();
    int colNum = arrayOfArrays.getObject(0).getArray("items").length();
    JsonWritableInternal[][] result = new JsonWritableInternal[rowNum][colNum]; 
    for (int i = 0; i < arrayOfArrays.length(); i++) {
      Json.Object arrayObj = arrayOfArrays.getObject(i);
      Json.Array array = arrayObj.getArray("items");
      for (int j = 0; j < array.length(); j++) {
        result[i][j] = newInstance(clazz, array.getObject(j));
      }
    }
    return (JW[][]) result;
  }

  @Override
  public <JW> JW newInstance(JW object) {
    throw new UnsupportedOperationException("Not yet implemented");
  }
}
