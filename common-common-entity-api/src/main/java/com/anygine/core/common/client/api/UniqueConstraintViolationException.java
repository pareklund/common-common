package com.anygine.core.common.client.api;

import com.anygine.common.exception.AnygineException;
import com.anygine.core.common.client.annotation.Embeddable;

@Embeddable
public class UniqueConstraintViolationException extends AnygineException {

  private static final long serialVersionUID = 1L;

  public UniqueConstraintViolationException(String message) {
    super(message, ExceptionType.UniqueConstraintViolationException);
  }

  public UniqueConstraintViolationException(Throwable cause) {
    super(cause, ExceptionType.UniqueConstraintViolationException);
  }
}
