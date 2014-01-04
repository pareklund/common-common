package com.anygine.service;

public abstract class APIMockBase {

  private final String name;
  
  public APIMockBase(String name) {
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
}
