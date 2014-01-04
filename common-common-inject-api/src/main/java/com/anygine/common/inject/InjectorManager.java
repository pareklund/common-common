package com.anygine.common.inject;

import java.util.HashMap;
import java.util.Map;

public class InjectorManager {

  private final static InjectorManager INSTANCE = new InjectorManager();
  
  private final Map<Class<? extends Injector>, Injector> injectors;

  private InjectorManager() {
    injectors = new HashMap<>();    
  }
  
  public static InjectorManager getInstance() {
    return INSTANCE; 
  }
  
	@SuppressWarnings("unchecked")
  public <T extends Injector> T getInjector(Class<T> injectorClass) {
		return (T) injectors.get(injectorClass);
	}

	public <T extends Injector> void setInjector(Class<T> injectorClass, T injector) {
	  injectors.put(injectorClass, injector);
	}
}
