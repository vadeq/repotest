package zws.origin;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 7, 2004, 10:31 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.exception.CanNotMaterialize;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class TC10Origin extends OriginBase 
{
	
  public TC10Origin() { super(); }

  public String getDatasourceType() { return OriginMaker.FROM_TEAMCENTER_10; }

  public String getUniqueSequence() { return getUid(); }
  
  public String getState() 
  {
    StringBuffer b = new StringBuffer();
    if (null!=getReleaseLevel()) b.append(getReleaseLevel()).append(delim);
    else b.append(NA).append(delim);
    if (null!=getLockStatus()) b.append(getLockStatus()).append(delim);
    else b.append(NA).append(delim);
    if(null!=getOwner()) b.append(getOwner());
    else b.append(NA);
    return b.toString();
  }

  public String getUniqueIDDisplay() { return getUid(); }

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
    if (tokens.hasMoreTokens()) if (!getDatasourceType().equalsIgnoreCase(tokens.nextToken())) throw new CanNotMaterialize("Teamcenter Origin", "Repository type is not Teamcenter", originAsString);
    if (tokens.hasMoreTokens()) setDatasourceName(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) setUid(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) setTimeOfCreationInMillis(convertToMilliSecs(tokens.nextToken())); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) setReleaseLevel(tokens.nextToken());
    if (tokens.hasMoreTokens()) setLockStatus(tokens.nextToken());
    if (tokens.hasMoreTokens()) setOwner(tokens.nextToken());
  }
  
  public void loadUniqueSequence(String sequence) throws CanNotMaterialize {
    setUid(sequence);
  }
  
  public void loadState(String state){
    if (null==state) return;
    StringTokenizer tokens = new StringTokenizer(state, delim);
    if (tokens.hasMoreTokens()) setReleaseLevel(tokens.nextToken());
    if (tokens.hasMoreTokens()) setLockStatus(tokens.nextToken());
    if (tokens.hasMoreTokens()) setOwner(tokens.nextToken());
  }

  public String toString() {
    StringBuffer b = new StringBuffer();
    b.append(getDomainName())
    .append(delim).append(getServerName())
    .append(delim).append(getDatasourceType())
    .append(delim).append(getDatasourceName())
    .append(delim).append(getUid())
    .append(delim).append(getTimeOfCreationInMillis());
    if (null==getReleaseLevel()) b.append(delim).append(NA);
    else b.append(delim).append(getReleaseLevel());
    if (null==getLockStatus()) b.append(delim).append(NA);
    else b.append(delim).append(getLockStatus());
    if (null==getOwner()) b.append(delim).append(NA);
    else b.append(delim).append(getOwner());
    return b.toString();
  }
  
  public String getUid() {
	return uid;
  }

  public void setUid(String uid) {
	this.uid = uid;
  }
  
  public long convertToMilliSecs(String s)
  {
    long time = 0;
    try
    {
      time = Long.valueOf(s).longValue();
    }
    catch(NumberFormatException ex)
    {
      try
      {
        Date date = new SimpleDateFormat("dd-MMM-yyyy HH:mm").parse(s);
        time = date.getTime();
      }
      catch(Exception e)
      {
        {} //System.out.println("Error parseing date string");
      }
    }
    return time;
  }
  
  public String toXML() { return null; }

  //unique path variables:
  private String uid=null;

  //State variables:
  private String releaseLevel=null;
  private String lockStatus=null;
  private String owner=null;

  public static String LOCKED="locked";
  public static String UNLOCKED = "unlocked";
  public static String MODIFYING = "being modified";
  public static String delim = Names.ORIGIN_DELIMITER;

}
