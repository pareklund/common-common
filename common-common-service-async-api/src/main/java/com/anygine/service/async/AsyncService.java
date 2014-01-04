package com.anygine.service.async;

import com.anygine.service.Service;

public interface AsyncService extends Service {
  <E extends Event> EventListener<E> getListener(Class<E> eventClass);
  <E extends Event> void registerListener(
      Class<E> eventClass, EventListener<E> listener);
}
