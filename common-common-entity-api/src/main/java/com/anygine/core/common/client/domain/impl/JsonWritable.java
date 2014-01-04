package com.anygine.core.common.client.domain.impl;

import com.anygine.core.common.codegen.api.JsonWritableInternal;

import playn.core.Json;

public interface JsonWritable {

  JsonWritableInternal.JsonType getJsonType();
  
  void writeJson(String key, Json.Writer writer);
  
  void update(Json.Object jsonObj);

}
