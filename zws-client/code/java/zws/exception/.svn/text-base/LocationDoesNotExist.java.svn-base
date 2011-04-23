package zws.exception;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 1, 2004, 4:11 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;

public class LocationDoesNotExist extends Exception{
  public LocationDoesNotExist(String msg) { super(msg); }
  public LocationDoesNotExist(String node, String datasourceType, String datasourceName, String location) {
    super(node+Names.DELIMITER+datasourceType+Names.DELIMITER+datasourceName+Names.DELIMITER+location);
  }
}
