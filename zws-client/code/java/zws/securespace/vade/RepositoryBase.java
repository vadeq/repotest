package zws.securespace.vade;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 21, 2004, 11:37 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.Namespace;

public abstract class RepositoryBase extends VadeSpaceBase implements Repository {
  public boolean passwordProtected() { return true; }
  protected abstract Namespace defineNamespace();
  
  public final String getServerName() { return serverName; }
  public final void setServerName(String s) { serverName=s; resetNamespace(); }
  
  public String getDatasourceName() { return getName(); }
  public void setDatasourceName(String s) { setName(s); }
  
  private String serverName=null; 
}
