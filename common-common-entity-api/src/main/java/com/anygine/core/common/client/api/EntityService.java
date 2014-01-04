package com.anygine.core.common.client.api;

import playn.core.Json;

import com.anygine.core.common.codegen.api.EntityInternal;

public interface EntityService extends JsonWritableService {

  <T> Class<T> getEntityClass(Json.Object jsonObj);

//  <T> EntityInternal<T> getInstance(Class<T> clazz, Json.Object entityObj);

  <T> EntityInternal<T> getInstance(Json.Object entityObj);
}
