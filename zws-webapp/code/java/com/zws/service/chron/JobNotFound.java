package com.zws.service.chron;
public class JobNotFound extends Exception {
  public JobNotFound() {super("Chron job not found");}
  public JobNotFound(String name) {super("Chron job not found: name=" + name);}
}