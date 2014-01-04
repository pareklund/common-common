package com.anygine.core.common.client.api;

import playn.core.Json;

import com.anygine.core.common.codegen.api.JsonWritableInternal;

public interface JsonWritableService {

  <JW extends JsonWritableInternal> Class<JW> getClass(Json.Object jsonObj);

  Class<?> getClass(String string);
}
