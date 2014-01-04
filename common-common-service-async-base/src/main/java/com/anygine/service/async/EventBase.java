package com.anygine.service.async;

public abstract class EventBase implements Event {

	@SuppressWarnings("unchecked")
	@Override
	public <E extends Event> Class<E> getTypedClass() {
		return (Class<E>) this.getClass();
	}
}
