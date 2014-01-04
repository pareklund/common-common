package com.anygine.core.common.codegen.api;

import java.util.Set;
import java.util.SortedMap;

import playn.core.Json;

import com.anygine.core.common.client.api.UniqueConstraintViolationException;
import com.anygine.core.common.client.domain.impl.Entity;
import com.anygine.core.common.client.domain.impl.EntityWriter;

public interface EntityInternal<T extends Object> extends Entity, JsonWritableInternal {

  EntityInternal<T> entityCopy(Json.Object updateSpec);
  void checkUniqueConstraints(
      SortedMap<Long, EntityInternal<T>> typedEntities) throws UniqueConstraintViolationException;      
  <C extends Comparable<? extends C>> int compareTo(C attribute, C value);
  <E> boolean equals(MetaModel<E> metaModel, E otherEntity);
  // TODO: Not part of interface - only generate internal implementation
  // void _write(EntityWriter entityWriter);
  // void _writeJson(Writer writer);
  long getId();
  void setId(long id);
  int getVersion();
  void setVersion(int version);
  void write(EntityWriter entityWriter, String key, TypeOfData typeOfData);
//  void writeJson(Writer writer, String key, TypeOfData typeOfData); 
  Set<? extends EntityInternal<?>> getEntities();
  Set<? extends EntityInternal<?>> getReferers();
  T getObject();
  Class<T> getKlass();
}
