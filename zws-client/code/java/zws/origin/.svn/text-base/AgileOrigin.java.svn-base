package zws.origin;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 21, 2004, 12:33 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.exception.CanNotMaterialize;

import java.util.List;
import java.util.StringTokenizer;

public class AgileOrigin extends OriginBase {
  public AgileOrigin() { super(); }
  public AgileOrigin(String datasourceType) throws CanNotMaterialize{
    super();
    loadDatasourceType(datasourceType);
  }


  public AgileOrigin(String domainName, String serverName, String datasourceName, String agileObjectID, String agileClassName, String partNumber) {
    super();
    setDatasourceName(datasourceName);
    setID(agileObjectID);
    setAgileClassName(agileClassName);
    setName(partNumber);
    setDomainName(domainName);
    setServerName(serverName);
    //+++ add timeOfCreation based on calendar;
  }


  public AgileOrigin(String datasourceName, String agileObjectID, String agileClassName, String partNumber) {
    super();
    setDatasourceName(datasourceName);
    setID(agileObjectID);
    setAgileClassName(agileClassName);
    setName(partNumber);
    /*this.setDomainName(s);
    this.setServerName(s);*/

    //+++ add timeOfCreation based on calendar;
  }

  public String getID() { return id; }
  public void setID(String s) { id=s; }
  public String getAgileClassName() { return agileClassName; }
  public void setAgileClassName(String s) { agileClassName = s; }


  public String getDatasourceType() {
    String t = FROM_AGILE;
    if (getAgileClassName()!=null) {
      t += dot + getAgileClassName().replace(space, dot);
    }
    return t;
  }

  public String getUniqueSequence() { return id; } //getRevision(); }
  public String getState() { return NA; }
  public String getRevision() { return revision; }
  public void setRevision(String s) { revision=s; }

  //State attributes (+ location)  state variables should be set to NA if not being used

  public List getStateChangeEvents(Origin newOrigin) { return null; }

  public void load(String originAsString) throws CanNotMaterialize {
    StringTokenizer tokens = new StringTokenizer(originAsString, delim);
    if (tokens.hasMoreTokens()) setDomainName(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) setServerName (tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) loadDatasourceType(tokens.nextToken());
    if (tokens.hasMoreTokens()) setDatasourceName(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) setTimeOfCreationInMillis(Long.valueOf(tokens.nextToken()).longValue()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    //if (tokens.hasMoreTokens()) setRevision(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) setID(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) setName(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
  }

  private void loadDatasourceType(String datasourceType) throws CanNotMaterialize {
    if (!datasourceType.startsWith(FROM_AGILE)) throw new CanNotMaterialize("Agile Origin", "Repository type is not Agile", datasourceType);
    int idx = datasourceType.indexOf(dot);
    String className = null;
    if (idx >0) className = datasourceType.substring(idx+1).replace(dot, space);
    setAgileClassName(className);
  }

  public void loadUniqueSequence(String sequence) throws CanNotMaterialize {
    setID(sequence);
    //setRevision(sequence);
  }

  public void loadState(String state){
  }

  public String toString() {
    StringBuffer b = new StringBuffer();
    b.append(getDomainName()).append(delim).
      append(getServerName()).append(delim).
      append(getDatasourceType()).append(delim).
      append(getDatasourceName()).append(delim).
      append(getTimeOfCreationInMillis()).append(delim).
      append(getID()).append(delim).
      append(getName());
    return b.toString();
  }

  public String toXML() { return null; }

  private String agileClassName=null;
  private String id=null;
  private String revision=null;
  //unique path variables:

  //State variables:
  public static String delim = Names.ORIGIN_DELIMITER;
  public static char dot = '.';
  public static char space = ' ';
}
