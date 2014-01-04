package com.anygine.core.common.client.api;

import playn.core.Json;

import com.anygine.core.common.codegen.api.EntityInternal;

public interface JsonToEntityMapper {

  <T> EntityInternal<T> getEntity(Json.Object jsonObj);
}
