package zws.exception;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 25, 2004, 2:00 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class CanNotDo extends Exception {  
  public CanNotDo(String action, String reason) { super("Can not do " + action + " :" + reason); }
}
