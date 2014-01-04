package com.anygine.service;

import java.util.HashMap;
import java.util.Map;

import com.anygine.common.Actor;
import com.anygine.service.PlayerClientAPI;
import com.anygine.service.ServiceAPI;
import com.anygine.service.ServiceConfigAPI;

public class ServiceRegistry {

	private Map<Class<? extends ServiceAPI>, ServiceAPI> services;
	private Map<Class<? extends ServiceConfigAPI>, ServiceConfigAPI> adminServices;
	private Map<Actor, PlayerClientAPI> playerClients;
	
	public ServiceRegistry() {
		services = new HashMap<Class<? extends ServiceAPI>, ServiceAPI>();
		adminServices = new HashMap<Class<? extends ServiceConfigAPI>, ServiceConfigAPI>();
		playerClients = new HashMap<Actor, PlayerClientAPI>();
	}
	
	@SuppressWarnings("unchecked")
	public <S extends ServiceAPI> S getService(Class<S> serviceClass) {
		return (S) services.get(serviceClass);
	}
	
	public <S extends ServiceAPI> void registerService(
			Class<S> serviceClass, S service) {
		services.put(serviceClass, service);
	}

	@SuppressWarnings("unchecked")
	public <AS extends ServiceConfigAPI> AS getAdminService(Class<AS> adminServiceClass) {
		return (AS) adminServices.get(adminServiceClass);
	}
	
	public <AS extends ServiceConfigAPI> void registerAdminService(
			Class<AS> adminServiceClass, AS adminService) {
		adminServices.put(adminServiceClass, adminService);
	}

  @SuppressWarnings("unchecked")
  public <PC extends PlayerClientAPI> PC getPlayerClient(
      Actor actor, Class<PC> playerClientClass) {
    return (PC) playerClients.get(actor);
  }
  
  public <PC extends PlayerClientAPI> void registerPlayerClient(
      Actor actor, PC playerClient) {
    playerClients.put(actor, playerClient);
  }


}
