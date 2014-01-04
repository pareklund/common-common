package com.anygine.core.common.client.api;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import playn.core.Json;
import playn.core.Json.Object;

import com.anygine.core.common.codegen.api.EntityInternal;
import com.anygine.core.common.codegen.api.JsonWritableInternal;

// TODO: Code generate
@Singleton
public class EntityFactoryBase extends JsonWritableFactoryBase implements EntityFactory {

  protected final EntityService entityService;
  
  protected final JsonToEntityMapper jsonToEntityMapper;
  
  @Inject
  public EntityFactoryBase(
      EntityService entityService, JsonToEntityMapper stringToEntityMapper) {
    this.entityService = entityService;
    this.jsonToEntityMapper = stringToEntityMapper;
  }

  @Override
  public <T> EntityInternal<T> newEntity(T object, long id, int version) {
    // TODO: Add instantiation of any common-common entities here
    throw new UnsupportedOperationException("Not yet implemented");
  }
  
  @Override
  public <JW extends JsonWritableInternal> JW newInstance(
      Class<JW> clazz, Object jsonObj) {
    // TODO: Add instantiation of any common-common JW:s here
    return super.newInstance(clazz, jsonObj);
  }

  @Override
  public <T> List<EntityInternal<T>> newEntityList(
      Class<T> clazz, Object jsonObj) {
    List<EntityInternal<T>> collection = new ArrayList<EntityInternal<T>>();
    Json.Array array = jsonObj.getArray("items");
    for (int i = 0; i < array.length(); i++) {
      collection.add(entityService.<T>getInstance(array.getObject(i)));
    }
    return collection;
  }

  @Override
  public <T> Set<EntityInternal<T>> newEntitySet(
      Class<T> clazz, Object jsonObj) {
    Set<EntityInternal<T>> collection = new HashSet<EntityInternal<T>>();
    Json.Array array = jsonObj.getArray("items");
    for (int i = 0; i < array.length(); i++) {
      collection.add(entityService.<T>getInstance(array.getObject(i)));
    }
    return collection;
  }

  @Override
  public <T> EntityInternal<T>[][] newEntityArrayOfArrays(
      Class<T> clazz, Object jsonObj) {
    Json.Array arrayOfArrays = jsonObj.getArray("items");
    int rowNum = arrayOfArrays.length();
    int colNum = arrayOfArrays.getObject(0).getArray("items").length();
    EntityInternal<T>[][] result = new EntityInternal[rowNum][colNum]; 
    for (int i = 0; i < arrayOfArrays.length(); i++) {
      Json.Object arrayObj = arrayOfArrays.getObject(i);
      Json.Array array = arrayObj.getArray("items");
      for (int j = 0; j < array.length(); j++) {
        result[i][j] = entityService.<T>getInstance(array.getObject(j));
      }
    }
    return result;
  }

  @Override
  public <T> EntityInternal<T> newEntity(Object jsonObj) {
    return jsonToEntityMapper.getEntity(jsonObj);
  }
  
  @Override
  public <T> EntityInternal<T> newEntity(Class<T> clazz, Object jsonObj) {
    // TODO: Implement
    throw new UnsupportedOperationException("Not yet implemented");
  }
}
