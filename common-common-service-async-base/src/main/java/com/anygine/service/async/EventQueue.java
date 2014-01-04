package com.anygine.service.async;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class EventQueue {
	
	private final Queue<Event> queue;
	
	public EventQueue() {
		queue = new ArrayBlockingQueue<Event>(10);
	}
	
	public void put(Event event) {
		queue.offer(event);
	}
	
	Event peek() {
		return queue.peek();
	}

	public Event get() {
		return queue.poll();
	}
}
