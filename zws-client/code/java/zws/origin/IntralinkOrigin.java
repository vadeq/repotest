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

public class IntralinkOrigin extends OriginBase {
  public IntralinkOrigin() { super(); }
  
  public String getDatasourceType() { return OriginMaker.FROM_ILINK; }
  
  public String getUniqueSequence() { return getBranch() + delim + getRevision() + delim + getVersion(); }
  public String getState() { 
    StringBuffer b = new StringBuffer();
    if (null!=getLocation()) b.append(getLocation()).append(delim);
    else b.append(NA).append(delim);
    if (null!=getReleaseLevel()) b.append(getReleaseLevel()).append(delim);
    else b.append(NA).append(delim);
    if (null!=getLockStatus()) b.append(getLockStatus()).append(delim);
    else b.append(NA).append(delim);
    if(null!=getOwner()) b.append(getOwner());
    else b.append(NA);
    return b.toString();
  }
  
  public String getBranch() { return branch; }
  public void setBranch(String s) { branch=s; }
  public String getRevision() { return revision; }
  public void setRevision(String s) { revision=s; }
  public int getVersion() { return version; }
  public void setVersion(int i) { version=i; }

  public String getUniqueIDDisplay() { return getName() + " [" +getBranch()+" "+getRevision()+"."+ getVersion()+"]"; }
  
  //State attributes (+ location)  state variables should be set to NA if not being used
  public String getReleaseLevel() { return releaseLevel; }
  public void setReleaseLevel(String s) { if(NA.equalsIgnoreCase(s)) releaseLevel=null; else releaseLevel=s; }
  public String getOwner() { return owner; }
  public void setOwner(String s) { if(NA.equalsIgnoreCase(s)) owner=null; else owner=s; }
  public String getLockStatus() { return lockStatus; }
  public void setLockStatus(String s) { if(NA.equalsIgnoreCase(s)) lockStatus=null; else lockStatus=s; } 
  public void lock() { lockStatus = LOCKED; }
  public void unlock() { lockStatus = UNLOCKED; }
  public void modify() { lockStatus = MODIFYING; }

  public List getStateChangeEvents(Origin newOrigin) { return null; }

  public void load(String originAsString) throws CanNotMaterialize {
    StringTokenizer tokens = new StringTokenizer(originAsString, delim);
    if (tokens.hasMoreTokens()) setDomainName(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) setServerName (tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) if (!getDatasourceType().equalsIgnoreCase(tokens.nextToken())) throw new CanNotMaterialize("Intralink Origin", "Repository type is not Intralink", originAsString);
    if (tokens.hasMoreTokens()) setDatasourceName(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) setTimeOfCreationInMillis(Long.valueOf(tokens.nextToken()).longValue()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) setBranch(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) setRevision(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) setVersion(Integer.valueOf(tokens.nextToken()).intValue()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) setName(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) setLocation(tokens.nextToken());
    if (tokens.hasMoreTokens()) setReleaseLevel(tokens.nextToken());
    if (tokens.hasMoreTokens()) setLockStatus(tokens.nextToken());
    if (tokens.hasMoreTokens()) setOwner(tokens.nextToken());
  }
  public void loadUniqueSequence(String sequence) throws CanNotMaterialize {
    StringTokenizer tokens = new StringTokenizer(sequence, delim);
    if (tokens.hasMoreTokens()) setBranch(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, sequence);
    if (tokens.hasMoreTokens()) setRevision(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, sequence);
    if (tokens.hasMoreTokens()) {
      String ver = tokens.nextToken();
      if (NA.equalsIgnoreCase(ver)) setVersion(-1);
      else  setVersion(Integer.valueOf(ver).intValue());
    }
    else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, sequence);
  }    
  public void loadState(String state){
    if (null==state) return;
    StringTokenizer tokens = new StringTokenizer(state, delim);
    if (tokens.hasMoreTokens()) setLocation(tokens.nextToken());
    if (tokens.hasMoreTokens()) setReleaseLevel(tokens.nextToken());
    if (tokens.hasMoreTokens()) setLockStatus(tokens.nextToken());
    if (tokens.hasMoreTokens()) setOwner(tokens.nextToken());
  }    

  public String toString() {
    StringBuffer b = new StringBuffer();
    b.append(getDomainName()).append(delim).append(getServerName()).append(delim).append(getDatasourceType()).append(delim).append(getDatasourceName()).append(delim).append(getTimeOfCreationInMillis()).append(delim).append(getBranch()).append(delim).append(getRevision()).append(delim).append(getVersion()).append(delim).append(getName());
    if (null==getLocation()) b.append(delim).append(NA);
    else b.append(delim).append(getLocation());
    if (null==getReleaseLevel()) b.append(delim).append(NA);
    else b.append(delim).append(getReleaseLevel());
    if (null==getLockStatus()) b.append(delim).append(NA);
    else b.append(delim).append(getLockStatus());
    if (null==getOwner()) b.append(delim).append(NA);
    else b.append(delim).append(getOwner());
    return b.toString();
  }

  public String toXML() { return null; }

  //unique path variables:
  private String branch=null;
  private String revision=null;
  private int version=-1;
  
  //State variables:
  private String releaseLevel=null;
  private String lockStatus=null;
  private String owner=null;

  public static String LOCKED="locked";
  public static String UNLOCKED = "unlocked";
  public static String MODIFYING = "being modified";
  public static String delim = Names.ORIGIN_DELIMITER;
}
