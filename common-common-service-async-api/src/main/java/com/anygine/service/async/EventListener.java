package com.anygine.service.async;


public interface EventListener<E extends Event> {
	void onEvent(E event);
}
