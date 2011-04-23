package com.zws.service.config;

public class ConfigurationNotFound extends Exception {
  public ConfigurationNotFound(String msg) { super(msg); }
  public ConfigurationNotFound(String resource, String name) {
    super(resource + " named " + name + " not found");
  }
}
