package com.anygine.core.common.client.domain.impl;

import playn.core.Json;

public interface EntityHolder<T> {

  void write(EntityWriter entityWriter, String key);
  EntityHolder<T> entityHolderCopy(Json.Object updateSpec);
}
