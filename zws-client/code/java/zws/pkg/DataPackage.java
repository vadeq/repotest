package zws.pkg;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 17, 2004, 11:59 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.Named;

import java.io.Serializable;

public interface DataPackage extends Named, Serializable, Cloneable {
  public String getName();
  public String getDomainName();
  public String getServerName();
}
