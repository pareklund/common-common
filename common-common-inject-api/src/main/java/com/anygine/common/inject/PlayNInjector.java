package com.anygine.common.inject;

import playn.core.Assets;

public interface PlayNInjector extends Injector {
	Assets getAssetManager();
}
