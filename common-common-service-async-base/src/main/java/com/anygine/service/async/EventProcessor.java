package com.anygine.service.async;

import static com.anygine.common.util.LogUtil.debug;

import com.anygine.common.util.ThreadUtil;

public class EventProcessor implements Runnable {
	
  private final String logName;
	private final EventQueue queue;
	final EventListenerRegistry eventListenerRegistry;
	
	volatile boolean shutdownRequested;
	volatile boolean shutdownComplete;
	
	public EventProcessor(String logName, EventQueue queue) {
	  this.logName = logName;
		this.queue = queue;
		this.eventListenerRegistry = new EventListenerRegistry();
	}

	@Override
	public void run() {
		while (!shutdownRequested || queue.peek() != null) {
			Event event = queue.peek();
			if (event == null) {
//				debug(logName, "No events received. Sleeping for a while...");
				ThreadUtil.sleep(1000);
			} else {
				event = queue.get();
				debug(logName, "Received event: " + event.toString());
				eventListenerRegistry.getListener(
						event.getTypedClass()).onEvent(event);
			}
		}
		debug(logName, "Shutdown completed");
		shutdownComplete = true;
	}
	
}
