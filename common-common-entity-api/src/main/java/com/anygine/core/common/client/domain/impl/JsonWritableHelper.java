package com.anygine.core.common.client.domain.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import playn.core.Json;
import playn.core.Json.Writer;

import com.anygine.core.common.client.api.EntityService;
import com.anygine.core.common.client.api.JsonWritableFactory;
import com.anygine.core.common.client.api.JsonWritableService;
import com.anygine.core.common.codegen.api.EntityInternal;
import com.anygine.core.common.codegen.api.JsonWritableInternal;
import com.anygine.core.common.codegen.api.JsonWritableInternal.TypeOfData;

public class JsonWritableHelper {

  @Inject
  private static JsonWritableFactory jsonWritableFactory;

  @Inject
  private static EntityService entityService;

  
  private JsonWritableHelper() {}
  
  // Stateless/static helper methods below

  // Add support for changed set of items, not just items being changed
  private static final <JW extends JsonWritableInternal> void updateCollection(
      Collection<JW> jsonWritables, Json.Object jsonObj) {
    Json.Array jsonArray = jsonObj.getArray("items");
    int i = 0;
    for (JW jsonWritable : jsonWritables) {
      jsonWritable.update(jsonArray.getObject(i));
    }
  }

  protected static final <JW extends JsonWritableInternal> List<JW> updateList(
      Class<JW> clazz, List<JW> jsonWritables, Json.Object parentObj, 
      String attrName) {
    List<JW> updatedCollection = jsonWritables;
    if (parentObj.containsKey(attrName)) {
      if (jsonWritables != null) {
        updateCollection(jsonWritables, parentObj.getObject(attrName));
      } else {
        updatedCollection = jsonWritableFactory.newList(
            clazz, parentObj.getObject(attrName));
      }
    }
    return updatedCollection;
  }

  public static final <JW extends JsonWritableInternal> void write(
      JW jsonWritable, Json.Writer writer, String attrName) {
    if (jsonWritable != null) {
      jsonWritable.writeJson(attrName, writer);
    }
  }

  public static final <T extends Object> void writeEntityHolder(
      EntityHolder<T> entityHolder, EntityWriter entityWriter, String attrName) {
    if (entityHolder != null) {
      entityHolder.write(entityWriter, attrName);
    }
  }

  public static final void writeString(
      String str, Json.Writer writer, String attrName) {
    if (str != null) {
      writer.value(attrName, str);
    }
  }

  public static final void writeEnum(
      Enum<?> e, Json.Writer writer, String attrName) {
    if (e != null) {
      writer.value(attrName, e.name());
    }
  }

//  public static final <JW extends JsonWritableInternal> void writeList(
//      List<JW> jsonWritables, Writer writer, String attrName) {
  public static final <T> void writeList(
      List<T> jsonWritables, Writer writer, String attrName) {
    if (jsonWritables != null) {
      writer.object(attrName);
      writer.array("items");
      for (T jsonWritable : jsonWritables) {
        ((JsonWritableInternal) jsonWritable).writeJson(null, writer);
      }    
      writer.end();
      writer.end();
    }
  }

  public static final <JW extends JsonWritableInternal> void writeArrayOfArrays(
      JW[][] jsonWritables, Writer writer, String attrName) {
    if (jsonWritables != null) {
      writer.object(attrName);
      writer.array("items");
      for (JW[] jsonWritableArray : jsonWritables) {
        writer.object();
        writer.array("items");
        for (JW jsonWritable : jsonWritableArray) {
          jsonWritable.writeJson(null, writer);
        }
        writer.end();
        writer.end();
      }
      writer.end();
      writer.end();
    }
  }

  public static final <T extends Object> void writeEntity(
      EntityInternal<?> entity, EntityWriter entityWriter, String attrName) {
    if (entity != null) {
      entityWriter.ensurePersisted(entity);
      entity.write(entityWriter, attrName, TypeOfData.Id);
    }    
  }

//  Class<T> clazz, EntityInternal<T> current, Json.Object parentObj, 
//  String attrName) {
  
  // TODO: Re-use common logic with writeList method
//  public static final <T extends Object> void writeEntityList(
//      List<EntityInternal<T>> entities, EntityWriter entityWriter, String attrName) {
  public static final <T extends Object> void writeEntityList(
      List<T> entities, EntityWriter entityWriter, String attrName) {
    if (entities != null) {
      Json.Writer writer = entityWriter.getWriter();
      writer.object(attrName);
      writer.array("items");
//      for (EntityInternal<T> entity : entities) {
      for (T entity : entities) {
        writeEntity((EntityInternal<T>) entity, entityWriter, attrName);
      }    
      writer.end();
      writer.end();
    }
  }

  // TODO: Re-use common logic with writeArrayOfArrays method
  public static final <T extends Object> void writeEntityArrayOfArrays(
      EntityInternal<T>[][] entities, EntityWriter entityWriter, String attrName) {
    if (entities != null) {
      Json.Writer writer = entityWriter.getWriter();
      writer.object(attrName);
      writer.array("items");
      for (EntityInternal<T>[] entityArray : entities) {
        writer.object();
        writer.array("items");
        for (EntityInternal<T> entity : entityArray) {
          writeEntity(entity, entityWriter, attrName);
        }
        writer.end();
        writer.end();
      }
      writer.end();
      writer.end();
    }
  }

  // TODO: Handle changes to set of items, not just items themselves
  private static final <JW extends JsonWritableInternal> void updateArrayOfArrays(
      JW[][] jsonWritables, Json.Object jsonObj) {
    Json.Array arrayOfArrays = jsonObj.getArray("items");
    for (int i = 0; i < arrayOfArrays.length(); i++) {
      Json.Object arrayObj = arrayOfArrays.getObject(i);
      Json.Array array = arrayObj.getArray("items");
      for (int j = 0; j < array.length(); j++) {
        jsonWritables[i][j].update(array.getObject(j));
      }
    }
  }

  protected static final <JW extends JsonWritableInternal> JW[][] updateArrayOfArrays(
      Class<JW> clazz, JW[][] jsonWritables, Json.Object parentObj, 
      String attrName) {
    JW[][] updatedArrayOfArrays = jsonWritables;
    if (jsonWritables != null) {
      updateArrayOfArrays(jsonWritables, parentObj.getObject(attrName));
    } else {
      updatedArrayOfArrays = jsonWritableFactory.newArrayOfArrays(
          clazz, parentObj.getObject(attrName));
    }
    return updatedArrayOfArrays;
  }

  public static final boolean update(
      boolean currentValue, Json.Object parentObj, String attrName) {
    if (parentObj.containsKey(attrName)) {
      return parentObj.getBoolean(attrName);
    }
    return currentValue;
  }

  public static final int update(
      int currentValue, Json.Object parentObj, String attrName) {
    if (parentObj.containsKey(attrName)) {
      return parentObj.getInt(attrName);
    }
    return currentValue;
  }

  public static final float update(
      float currentValue, Json.Object parentObj, String attrName) {
    if (parentObj.containsKey(attrName)) {
      return (float) parentObj.getNumber(attrName);
    }
    return currentValue;
  }

  public static final String update(
      String currentValue, Json.Object parentObj, String attrName) {
    if (parentObj.containsKey(attrName)) {
      return parentObj.getString(attrName);
    }
    return currentValue;
  }

  public static final <E extends Enum<E>> E updateEnum(
      E currentValue, Json.Object parentObj, String attrName) {
    if (parentObj.containsKey(attrName)) {
      return (E) E.valueOf(
          currentValue.getClass(), parentObj.getString(attrName));
    }
    return currentValue;
  }

  public static final <JW extends JsonWritableInternal> JW update(
      Class<JW> clazz, JW current, Json.Object parentObj, String attrName) {
    JW updated = current;
    if (parentObj.containsKey(attrName)) {
      if (current != null) {
        current.update(parentObj.getObject(attrName));
      } else {
        updated = jsonWritableFactory.newInstance(
            clazz, parentObj.getObject(attrName));
      }
    }
    return updated;    
  }

  public static final <T extends Object> EntityInternal<T> updateEntity(
      Class<T> clazz, EntityInternal<T> current, Json.Object parentObj, 
      String attrName) {
    EntityInternal<T> updated = current;
    if (parentObj.containsKey(attrName)) {
      if (current != null) {
        current.update(parentObj.getObject(attrName));
      } else {
        updated = entityService.<T>getInstance(
            parentObj.getObject(attrName));
      }
    }
    return updated;
  }

  public static final <T extends Object> void readCollection(
      Class<T> clazz, Collection<T> collectionToRead, 
      Json.Object collectionObj) {
    collectionToRead.clear();
    Json.Object contentsObj = collectionObj.getObject("contents");
    if (contentsObj != null) {
      Json.Array contentsArray = contentsObj.getArray("items");
      for (int i = 0; i < contentsArray.length(); i++) {
        Json.Object itemObj = contentsArray.getObject(i);
        collectionToRead.add(clazz.cast(jsonWritableFactory.newInstance(
            entityService.getClass(itemObj), itemObj)));
      }
    }
    
  }
  
  protected static final <T extends Object> EntityInternal<T> copyEntityField(
      EntityInternal<T> fromField, Json.Object updateSpec) {
    if (updateSpec == null || TypeOfData.Id.equals(
        TypeOfData.valueOf(updateSpec.getString("type")))) {
      return fromField;
    } else {
      return fromField.entityCopy(updateSpec);
    }    
  }

  protected static final <T extends Object> EntityHolder<T> copyEntityHolderField(
      EntityHolder<T> fromField, Json.Object updateSpecObj) {
    if (updateSpecObj == null) {
      return fromField;
    } else if (TypeOfData.Id.equals(
        TypeOfData.valueOf(updateSpecObj.getString("type")))) {
      throw new UnsupportedOperationException(
      "TypeOfData == Id not supported in update spec for entity holders");
    }
    else {
      return fromField.entityHolderCopy(updateSpecObj);
    }    
  }

  protected static final <T extends Object> List<EntityInternal<T>> copyEntityListField(
      List<EntityInternal<T>> fromField, Json.Array updateSpecArray) {
    if (updateSpecArray == null || fromField == null) {
      return fromField;
    } else {
      List<EntityInternal<T>> entityList = new ArrayList<EntityInternal<T>>(fromField.size());
      int i = 0;
      for (EntityInternal<T> entity : fromField) {
        entityList.add(entity.entityCopy(updateSpecArray.getObject(i++)));
      }
      return entityList;
    }    
  }

  protected static final <T extends Object> EntityInternal<T>[][] copyEntityArrayArrayField(
      EntityInternal<T>[][] fromField, Json.Array updateSpecArray) {
    if (updateSpecArray == null || fromField == null) {
      return fromField;
    } else {
      EntityInternal<T>[][] entityArrayArray = 
        (EntityInternal<T>[][]) new EntityInternal[fromField.length][fromField[0].length];
      int i = 0;
      for (EntityInternal<T>[] fromArray : fromField) {
        int j = 0;
        for (EntityInternal<T> entity : fromArray) {
          entityArrayArray[i][j] = copyEntityField(
              entity, updateSpecArray.getArray(i).getObject(j));
          j++;
        }
        i++;
      }
      return entityArrayArray;
    }
  }
  
}
