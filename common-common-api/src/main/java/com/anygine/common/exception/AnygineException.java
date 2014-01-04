package com.anygine.common.exception;

import javax.security.auth.login.FailedLoginException;

public class AnygineException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = -7728747253684881714L;

  public static enum ExceptionType { 
    FailedLoginException, UniqueConstraintViolationException, NativeJavaException;
  }
  
  protected final ExceptionType type;
  protected final String message;

/*  
  // Forced to use delegation since we already inherit from Exception
  private final JsonWritableBase jsonWritable = new JsonWritableBase() {
    
    @Override
    public JsonType getJsonType() {
      return JsonType.Exception;
    }
    
    @Override
    protected void _writeJson(Writer writer) {
      writeString(message, writer, "message");
      writeEnum(type, writer, "type");
      if (type == ExceptionType.NativeJavaException) {
        writeString(getCause().toString(), writer, "cause");
      }
    }

    @Override
    public void update(Object jsonObj) {
      type = updateEnum(type, jsonObj, "type");
      // TODO: A bit ugly but returned message ref is actually writable
      update(message, jsonObj, "message");
      // TODO: Possibly handle cause (Instantiate native exception) 
    }
  };
*/
  
/*  
  public AnygineException(Json.Object jsonObj) {
    super(jsonObj.getString("message"));
    this.message = jsonObj.getString("message");
    this.type = ExceptionType.valueOf(jsonObj.getString("type"));
    // TODO: How to/if display native exceptions after serialization...?
  }
*/
  
  public AnygineException(Throwable cause) {
    super(cause);
    message = cause.getMessage();
    if (cause instanceof FailedLoginException) {
      type = ExceptionType.FailedLoginException;
    } /* else if (cause instanceof UniqueConstraintViolationException) {
      type = ExceptionType.UniqueConstraintViolationException;
    } */ else {
      type = ExceptionType.NativeJavaException;
    }
  }

  public AnygineException(String message) {
    super(message);
    this.message = message;
    this.type = ExceptionType.NativeJavaException;
  }

  public AnygineException(String message, ExceptionType type) {
    super(message);
    this.message = message;
    this.type = type;
  }

  protected AnygineException(Throwable cause, ExceptionType type) {
    super(cause);
    message = cause.getMessage();
    this.type = type;
  }

  public ExceptionType getType() {
    return type;
  }
  
  @Override
  public String getMessage() {
    return message;
  }
  
/*  
  @Override
  public JsonType getJsonType() {
    return jsonWritable.getJsonType();
  }
  
  @Override
  public void writeJson(String key, Json.Writer writer) {
    jsonWritable.writeJson(key, writer);
  }

  @Override
  public void update(Object jsonObj) {
    jsonWritable.update(jsonObj);
  }
  */
}
