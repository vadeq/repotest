package zws.pkg;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 18, 2004, 12:06 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class DataPackageBase implements DataPackage {
  public String getName() { return name; }
  public void setName(String s) { name=s; }
  public String getDomainName() { return domainName; }
  public void setDomainName(String s) { domainName=s; }
  public String getServerName() { return serverName; }
  public void setServerName(String s) { serverName=s; }
    
  private String domainName=null;
  private String serverName=null;
  private String name=null;
}
