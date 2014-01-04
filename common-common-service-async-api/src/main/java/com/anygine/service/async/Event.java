package com.anygine.service.async;

public interface Event {
	<E extends Event> Class<E> getTypedClass();
}
