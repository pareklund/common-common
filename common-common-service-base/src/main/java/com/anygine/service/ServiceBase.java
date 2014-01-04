package com.anygine.service;


public abstract class ServiceBase implements Service {

  protected final String name;
  protected final ServiceRegistry serviceRegistry;

  public ServiceBase(String name, ServiceRegistry serviceRegistry) {
    this.name = name;
    this.serviceRegistry = serviceRegistry;
  }
  
  @Override
  public void start() {
  }

  @Override
  public void shutdown() {
  }

  @Override
  public String getName() {
    return name;
  }
}
