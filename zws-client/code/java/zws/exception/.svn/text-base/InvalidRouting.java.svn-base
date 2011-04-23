package zws.exception;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 18, 2004, 1:32 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;

public class InvalidRouting extends Exception{
  public InvalidRouting(String domainName, String serverName) {
    super("Tried to route "+domainName+"."+serverName+" to "+Server.getDomainName()+"."+Server.getName());
  }
  
}
