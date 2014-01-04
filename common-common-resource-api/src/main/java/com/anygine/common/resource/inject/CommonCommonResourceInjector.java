package com.anygine.common.resource.inject;

import com.anygine.common.inject.Injector;
import com.anygine.common.resource.ResourceInfoMap;

public interface CommonCommonResourceInjector extends Injector {
  ResourceInfoMap getResourceInfoMap();
}
