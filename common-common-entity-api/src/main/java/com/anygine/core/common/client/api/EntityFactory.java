package com.anygine.core.common.client.api;

import java.util.List;
import java.util.Set;

import playn.core.Json;
import playn.core.Json.Object;

import com.anygine.core.common.codegen.api.EntityInternal;

public interface EntityFactory extends JsonWritableFactory {

  <T> EntityInternal<T> newEntity(T object, long id, int version);

  <T> List<EntityInternal<T>> newEntityList(
      Class<T> clazz, Json.Object jsonObj);

  <T> Set<EntityInternal<T>> newEntitySet(Class<T> clazz, Object jsonObj);

  <T> EntityInternal<T>[][] newEntityArrayOfArrays(
      Class<T> clazz, Json.Object jsonObj);

  <T> EntityInternal<T> newEntity(Class<T> clazz, Json.Object jsonObj);

  <T> EntityInternal<T> newEntity(Json.Object jsonObj);
}
