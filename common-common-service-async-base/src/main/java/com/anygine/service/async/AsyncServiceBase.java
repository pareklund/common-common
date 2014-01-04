package com.anygine.service.async;

import static com.anygine.common.util.LogUtil.debug;

import com.anygine.common.util.ThreadUtil;
import com.anygine.service.ServiceBase;
import com.anygine.service.ServiceRegistry;

public abstract class AsyncServiceBase extends ServiceBase implements AsyncService {

	protected final EventQueue eventQueue;
	protected EventProcessor eventProcessor;
	
	private transient boolean started;
	protected transient int numOutstandingRequests;
	
	public AsyncServiceBase(String name, ServiceRegistry serviceRegistry) {
	  super(name, serviceRegistry);
		eventQueue = new EventQueue();
		eventProcessor = new EventProcessor(name, eventQueue);
		numOutstandingRequests = 0;
	}

	@Override
	public synchronized void start() {
	  if (!started) { 
	    new Thread(eventProcessor).start();
	    started = true;
	  }
	}
	
	@Override
	public synchronized void shutdown() {
		debug(name, "Shutdown requested");
		if (started) {
  		eventProcessor.shutdownRequested = true;
  		while (numOutstandingRequests != 0 || !eventProcessor.shutdownComplete) {
  			ThreadUtil.sleep(1000);
  		}
  		started = false;
		}
	}
	
	@Override
	public <E extends Event> EventListener<E> getListener(Class<E> eventClass) {
		return eventProcessor.eventListenerRegistry.getListener(eventClass);
	}
	
	@Override
	public <E extends Event> void registerListener(
			Class<E> eventClass, EventListener<E> listener) {
		eventProcessor.eventListenerRegistry.registerListener(eventClass, listener);
	}
}
