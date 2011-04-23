package zws.origin;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 7, 2004, 10:31 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.exception.CanNotMaterialize;

import java.util.List;
import java.util.StringTokenizer;

public class WorkspaceOrigin extends OriginBase {
  public WorkspaceOrigin() { super(); }
  
  public String getDatasourceType() { return OriginMaker.FROM_WORKSPACE; }
  
  public String getUniqueSequence() { return getWorkspace(); }
  public String getState() { return null; }

  public String getUniqueIDDisplay() { return getWorkspace() + delim + getName(); }
  
  //State attributes (+ location)  state variables should be set to NA if not being used

  public List getStateChangeEvents(Origin newOrigin) { return null; }

  public void load(String originAsString) throws CanNotMaterialize {
    StringTokenizer tokens = new StringTokenizer(originAsString, delim);
    if (tokens.hasMoreTokens()) setDomainName(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) setServerName (tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) if (!getDatasourceType().equalsIgnoreCase(tokens.nextToken())) throw new CanNotMaterialize("Workspace Origin", "Repository type is not a Workspace", originAsString);
    if (tokens.hasMoreTokens()) setDatasourceName(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) setUsername(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) setTimeOfCreationInMillis(Long.valueOf(tokens.nextToken()).longValue()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) setWorkspace(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) setName(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
  }
  public void loadUniqueSequence(String sequence) throws CanNotMaterialize {
    StringTokenizer tokens = new StringTokenizer(sequence, delim);
    if (tokens.hasMoreTokens()) setWorkspace(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, sequence);
    if (tokens.hasMoreTokens()) setName(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, sequence);
  }    
  public void loadState(String state){ }    

  public String getWorkspace() { return workspace; }
  public void setWorkspace(String s) { workspace=s; }
  public String getUsername() { return username; }
  public void setUsername(String s) { username=s; }
  	
  public String toString() {
    StringBuffer b = new StringBuffer();
    b.append(getDomainName()).append(delim).append(getServerName()).append(delim).append(getDatasourceType()).append(delim).append(getDatasourceName()).append(delim).append(getUsername()).append(delim).append(getTimeOfCreationInMillis()).append(delim).append(getWorkspace()).append(delim).append(getName());
    return b.toString();
  }

  public String toXML() { return null; }

  //unique path variables:
  private String datasourceType;
  private String workspace=null;
  private String username=null;
  
  //State variables:

  public static String delim = Names.ORIGIN_DELIMITER;
}
