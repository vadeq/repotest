package zws.exception;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 26, 2004, 1:46 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class WaitedTooLong extends Exception {
  public WaitedTooLong() { super(); }
  public WaitedTooLong(String operation) { super(operation); }
  public WaitedTooLong(String operation, long time) { super("Waited longer than " + time + ": " + operation); timeout=time; }
  
  public long getTimeout() { return timeout; }
  private long timeout=0;
}
