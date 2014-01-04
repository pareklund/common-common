package com.anygine.core.common.client.annotation;

public @interface Field {
  String name();
  boolean unique() default false;
}
