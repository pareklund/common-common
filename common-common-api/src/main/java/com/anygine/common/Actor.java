package com.anygine.common;

public abstract class Actor {

	protected final long id;
	
	public Actor(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "{ class: " + getClass().getSimpleName() + ", id: " + id + " }";
	}
}
