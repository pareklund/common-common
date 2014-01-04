package com.anygine.core.common.client.annotation;


public @interface FieldRef {
  String field();
  String attribute() default "";

}
