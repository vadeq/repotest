package zws.exception;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 11, 2004, 6:05 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.application.Names;

import java.io.File;


public class NotAFile extends Exception {
  public NotAFile(String location, String name) { super(location + Names.PATH_SEPARATOR + name); }
  public NotAFile(File dir) { super(dir.getAbsolutePath()); }
  public NotAFile(String datasourceName, String location, String name) {
    super(Server.getDomainName() +Names.DELIMITER+Server.getName()+Names.DELIMITER+datasourceName+Names.DELIMITER+location+Names.DELIMITER+name);
  }
}
