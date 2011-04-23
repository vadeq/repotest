package zws.origin;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 7, 2004, 10:03 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.application.Names;
import zws.exception.CanNotMaterialize;
import zws.exception.InvalidComparison;
import zws.util.FileNameUtil;
import zws.util.RoutedDataBase;

import java.io.File;
import java.io.InputStream;
import java.util.*;

// Origin - tracks:
// - Routing information: domain, server, datasource
// - Unique Identifier to find the component in the datasource: branch, revision, version, name OR location/name
// - Location
// - State: Release levels, lock status, location may also be included if it is not required for unique identification
public abstract class OriginBase extends RoutedDataBase implements Origin, Cloneable {
  public OriginBase() { 
    setDomainName(Server.getDomainName());
    setServerName(Server.getName());
  }
  public Origin copy() {
    try { return (Origin)clone(); }
    catch (CloneNotSupportedException e) { e.printStackTrace(); return null; } //clone is supported.
  }

  public abstract void load(String originAsString) throws CanNotMaterialize;
  public final void loadUniqueID(String uid) throws CanNotMaterialize {
    int idx = uid.lastIndexOf(delim);
    String seq = uid.substring(0, idx);
    setName(uid.substring(idx+1));
    loadUniqueSequence(seq);
  }
  public abstract void loadUniqueSequence(String sequence) throws CanNotMaterialize;
  public abstract void loadState(String state);
  
  //unique will identify a component "path" within a datasource (don't include domain, server, datasource type or datasource name or name)
  //public String getName() { return name; }
  public void setName(String s) { if(NA.equalsIgnoreCase(s)) super.setName(null); else super.setName(s); }
  //public String getDomainName(){ return domainName; }
  public void setDomainName(String s){ if(NA.equalsIgnoreCase(s)) super.setDomainName(null); else super.setDomainName(s); }
  //public String getServerName(){ return serverName; }
  public void setServerName(String s){ if(NA.equalsIgnoreCase(s)) super.setServerName(null); else super.setServerName(s); }
  public String getRepositoryName() { return getDatasourceName(); }
  public void setDatasourceName(String s) { if(NA.equalsIgnoreCase(s)) super.setDatasourceName(null); else super.setDatasourceName(s); }
  public void setRepositoryName(String s) { setDatasourceName(s); }
  
  public abstract String getDatasourceType();
  
  public Calendar getTimeOfCreation() {
    Calendar c = new GregorianCalendar();
    c.setTimeInMillis(timeOfCreation);
    return c;
  }
  public void setTimeOfCreation(Calendar t) { timeOfCreation= t.getTimeInMillis(); }
  public long getTimeOfCreationInMillis() { return timeOfCreation; }
  public void setTimeOfCreationInMillis(long time) { timeOfCreation = time; }
  
  public abstract String getUniqueSequence(); //unique sequence should never include the name

  public String getLocation() { return location; }
  public void setLocation(String s) {  if(NA.equalsIgnoreCase(s)) location=null; else location=s; }
  public abstract String getState();    //state of data is derived information
  
  public String getFileType() { return FileNameUtil.getFileNameExtension(getName()); }
  
  public abstract List getStateChangeEvents(Origin newOrigin) throws InvalidComparison;
  public final String getUniqueID() { return getUniqueSequence() + delim + getName(); }
  public String getUniqueIDDisplay() { return getName() + " " + getUniqueSequence(); }

  public abstract String toString();
  public abstract String toXML();

  public InputStream getBinary(){ return null; } //use downloader client
  public File exportBinary(File directory) { return null; }


  public boolean equals(Object o) {
    return isExactlyTheSame((Origin)o);
  }
  public boolean isRenamed(Origin o) {
    if (null==o) return true;
    if (getDomainName().equals(o.getDomainName()) &&
        getServerName().equals(o.getServerName()) &&
        getDatasourceType().equals(o.getDatasourceType()) &&
        getDatasourceName().equals(o.getDatasourceName()) &&
        getUniqueSequence().equals(o.getUniqueSequence()) &&
        !getName().equals(o.getName()) )return true;
    return false;
  }
  public boolean isTheSameAs(Origin o) {
    if (null==o) return false;
    return (o.getDomainName().equals(getDomainName()) && o.getServerName().equals(getServerName()) && o.getDatasourceType().equals(getDatasourceType())&& o.getDatasourceName().equals(getDatasourceName()) && o.getUniqueID().equals(getUniqueID()));
  }
  public boolean isExactlyTheSame(Origin o) {
    if (null==o) return false;
    return (o.getDomainName().equals(getDomainName()) && o.getServerName().equals(getServerName()) && o.getDatasourceType().equals(getDatasourceType())&& o.getDatasourceName().equals(getDatasourceName()) && o.getTimeOfCreationInMillis() == timeOfCreation && o.getUniqueSequence().equals(getUniqueSequence()) && o.getName().equals(getName()));
  }
  public boolean isFromSameDomain(Origin o) {
    if (null==o) return false;
    return (o.getDomainName().equals(getDomainName()));
  }
  public boolean isFromSameServer(Origin o) {
    if (null==o) return false;
    return (o.getDomainName().equals(getDomainName()) && o.getServerName().equals(getServerName()));
  }  
  public boolean isFromThisServer(Origin o) {
    if (null==o) return false;
    return (o.getDomainName().equals(Server.getDomainName()) && o.getServerName().equals(Server.getName()));
  }  
  public boolean isFromSameDatasource(Origin o) {
    if (null==o) return false;
    return (o.getDomainName().equals(getDomainName()) && o.getServerName().equals(getServerName()) && o.getDatasourceType().equals(getDatasourceType())&& o.getDatasourceName().equals(getDatasourceName()));
  }
  public boolean isFromSameDatasourceType(Origin o) {
    if (null==o) return false;
    return (o.getDatasourceType().equals(getDatasourceType()));
  }
  public boolean hasSameUniqueID(Origin o) {
    if (null==o) return false;
    return (o.getUniqueID().equals(getUniqueID()));
  }
  public boolean hasSameName(Origin o) {
    if (null==o) return false;
    return (o.getName().equals(getName()));
  }
  public boolean isEarlier(Origin o) {
    if (null==o) return false;
    return timeOfCreation< o.getTimeOfCreationInMillis(); 
  }
  public boolean isLater(Origin o) {
    if (null==o) return false;
    return timeOfCreation > o.getTimeOfCreationInMillis(); 
  }
 
  private String datasourceType=null;
//  private String domainName=null;
//  private String serverName=null;
//  private String datasourceName=null;
//  private String name=null;
  private String fileType=null;
  private long timeOfCreation=8;
  private String location=NA;

  public static String NA=Names.NA;
  public static String delim = Names.ORIGIN_DELIMITER;
}

