package com.anygine.core.common.client.api;

public class UniqueConstraintViolationException_Embeddable extends com.anygine.core.common.client.api.UniqueConstraintViolationException implements com.anygine.core.common.codegen.api.JsonWritableInternal { 

  public UniqueConstraintViolationException_Embeddable(java.lang.String message) {
    super(message);
  }

  public UniqueConstraintViolationException_Embeddable(playn.core.Json.Object fields) {
    super(
      fields.getString("message")
    );
  }

  @Override
  public com.anygine.core.common.codegen.api.JsonWritableInternal.JsonType getJsonType() {
    return com.anygine.core.common.codegen.api.JsonWritableInternal.JsonType.UniqueConstraintViolationException;
  }

  @Override
  public void writeJson(java.lang.String key, playn.core.Json.Writer writer) {
    _writeJsonHeader(writer, key, com.anygine.core.common.codegen.api.JsonWritableInternal.TypeOfData.Object);
    _writeJson(writer);
    _writeJsonFooter(writer);
  }

  @Override
  public void update(playn.core.Json.Object jsonObj) {
  }

  protected void _writeJson(playn.core.Json.Writer writer) {
    com.anygine.core.common.client.domain.impl.JsonWritableHelper.writeString(getMessage(), writer, "message");
    com.anygine.core.common.client.domain.impl.JsonWritableHelper.writeEnum(getType(), writer, "type");
  }

  protected void _writeJsonHeader(playn.core.Json.Writer writer, java.lang.String key, com.anygine.core.common.codegen.api.JsonWritableInternal.TypeOfData typeOfData) {
    if (key != null) {
      writer.object(key);
    } else {
      writer.object();
    }
    writer.value("type", getJsonType().name());
    writer.value("typeOfData", typeOfData.name());
    writer.object(typeOfData.name());
  }

  protected void _writeJsonFooter(playn.core.Json.Writer writer) {
    writer.end();
    writer.end();
  }

}
