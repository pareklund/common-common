package com.anygine.core.common.client.api;

import java.util.List;

import playn.core.Json;

import com.anygine.core.common.codegen.api.JsonWritableInternal;

public interface JsonWritableFactory {

  <JW> JW newInstance(JW object);
  
  <JW extends JsonWritableInternal> JW newInstance(Class<JW> clazz, Json.Object jsonObj);
  
  <JW extends JsonWritableInternal> List<JW> newList(Class<JW> clazz, Json.Object jsonObj); 
  
  <JW extends JsonWritableInternal> JW[][] newArrayOfArrays(Class<JW> clazz, Json.Object jsonObj);
}
