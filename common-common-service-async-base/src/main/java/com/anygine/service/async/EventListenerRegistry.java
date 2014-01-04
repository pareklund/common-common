package com.anygine.service.async;

import java.util.HashMap;
import java.util.Map;


public class EventListenerRegistry {
	
	private Map<Class<? extends Event>, EventListener<? extends Event>> listeners;
	
	public EventListenerRegistry() {
		listeners = 
			new HashMap<Class<? extends Event>, EventListener<? extends Event>>();
	}
	
	@SuppressWarnings("unchecked")
	public <E extends Event> EventListener<E> getListener(Class<E> eventClass) {
	  EventListener<? extends Event> eventListener = listeners.get(eventClass);

	  if (eventListener == null) {
	    Class<? extends Event> classInHierarchy = eventClass;
	    while (!classInHierarchy.getSuperclass().equals(EventBase.class)) {
	      eventListener = listeners.get(classInHierarchy.getSuperclass());
	      if (eventListener != null) {
	        break;
	      }
	      classInHierarchy = (Class<? extends Event>) classInHierarchy.getSuperclass();
	    }

	    if (eventListener == null) {
  	    classInHierarchy = eventClass;
  	    boolean found = false;
        while (!classInHierarchy.equals(EventBase.class)) {
          Class<?>[] interfaces = classInHierarchy.getInterfaces();
          for (Class<?> interfejs : interfaces) {
            if (Event.class.isAssignableFrom(interfejs)) {
              eventListener = listeners.get(interfejs);
              if (eventListener != null) {
                found = true;
                break;
              }
            }
          }
          if (found) {
            break;
          }
          classInHierarchy = (Class<? extends Event>) classInHierarchy.getSuperclass();
        }
	    }
	  }
		return (EventListener<E>) eventListener;
	}
	
	public <E extends Event> void registerListener(
			Class<E> eventClass, EventListener<E> listener) {
		listeners.put(eventClass, listener);
	}
}
