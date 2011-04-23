package zws.exception;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 1, 2004, 4:30 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.application.Names;

import java.io.File;

public class NotADirectory extends Exception {
  public NotADirectory(String location) { super(location); }
  public NotADirectory(File dir) { super(dir.getAbsolutePath()); }
  public NotADirectory(String datasourceName, String location) {
    super(Server.getDomainName() +Names.DELIMITER+Server.getName()+Names.DELIMITER+datasourceName+Names.DELIMITER+location);
  }
}
