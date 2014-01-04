package com.anygine.common.entity.inject;

import com.anygine.common.inject.Injector;
import com.anygine.core.common.client.api.EntityFactory;
import com.anygine.core.common.client.api.EntityService;
import com.anygine.core.common.client.api.JsonWritableFactory;

public interface CommonCommonEntityInjector extends Injector {
  JsonWritableFactory getJsonWritableFactory();
  EntityFactory getEntityFactory();
  EntityService getEntityService();
}
