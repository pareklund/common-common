package com.anygine.core.common.client.domain.impl;

import java.util.Map;
import java.util.SortedMap;

import playn.core.Json.Writer;

import com.anygine.core.common.codegen.api.EntityInternal;
import com.anygine.core.common.codegen.api.JsonWritableInternal.TypeOfData;

public interface EntityWriter {

  Writer getWriter();

  void ensurePersisted(EntityInternal<?> entity);

  void write();

  Map<Class<?>, SortedMap<Long, ? extends EntityInternal<?>>> getEntitiesToWrite();

  void writeEntity(EntityInternal<?> entity, String key, TypeOfData typeOfData);

}
