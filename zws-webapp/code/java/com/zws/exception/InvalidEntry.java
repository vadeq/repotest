package com.zws.exception;

public class InvalidEntry extends Exception {
  public InvalidEntry(String type) {
    super("Invalid entry of type " + type);
  }
}
