package zws.origin;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 7, 2004, 11:34 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.exception.CanNotMaterialize;

import java.io.File;
import java.util.List;
import java.util.StringTokenizer;

public class FileSystemOrigin extends OriginBase {
  public FileSystemOrigin() { super(); }
  public FileSystemOrigin(String datasourceName) { super(); setDatasourceName(datasourceName); }
  
  public String getDatasourceType() { return OriginMaker.FROM_DISK; }

  public String getUniqueSequence() { return String.valueOf(getTimeOfCreationInMillis()) + delim + getLocation();}
  public String getState() { return String.valueOf(getFileLength()) + delim + getIsReadOnly() + delim + getIsHidden(); }

  //State attributes (+ location)
  public long getFileLength() { return fileLength; }
  public void setFileLength(long  l) { fileLength=l; }
  public boolean getIsReadOnly() { return isReadOnly; }
  public void setIsReadOnly(boolean b) { isReadOnly=b; }
  public boolean getIsHidden() { return isHidden; }
  public void setIsHidden(boolean b) { isHidden=b; }
  
  public List getStateChangeEvents(Origin newOrigin) { return null; }

  public void load(String originAsString) throws CanNotMaterialize {
    StringTokenizer tokens = new StringTokenizer(originAsString, delim);
    String token;
    if (tokens.hasMoreTokens()) setDomainName(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) setServerName (tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) if (!getDatasourceType().equalsIgnoreCase(tokens.nextToken())) throw new CanNotMaterialize("Intralink Origin", "Repository type is not "+getDatasourceType(), originAsString);
    if (tokens.hasMoreTokens()) setDatasourceName(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) setTimeOfCreationInMillis(Long.valueOf(tokens.nextToken()).longValue()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) setLocation(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) setName(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, originAsString);
    if (tokens.hasMoreTokens()) {
      token = tokens.nextToken();
      if(!NA.equalsIgnoreCase(token)) setFileLength(Long.valueOf(token).longValue()); 
    }
    if (tokens.hasMoreTokens()) {
      token = tokens.nextToken();
      if(!NA.equalsIgnoreCase(token)) setIsReadOnly(Boolean.valueOf(token).booleanValue());
    }
    if (tokens.hasMoreTokens()) {
      token = tokens.nextToken();
      if(!NA.equalsIgnoreCase(token)) setIsHidden(Boolean.valueOf(token).booleanValue());
    }
  }
  
  public void loadUniqueSequence(String sequence) throws CanNotMaterialize {
    StringTokenizer tokens = new StringTokenizer(sequence, delim);
    if (tokens.hasMoreTokens()) setTimeOfCreationInMillis(Long.valueOf(tokens.nextToken()).longValue()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, sequence);
    if (tokens.hasMoreTokens()) setLocation(tokens.nextToken()); else throw new CanNotMaterialize("Origin", "Not enough tokens delimited by "+delim, sequence);
  }

  public void loadState(String state){
    StringTokenizer tokens = new StringTokenizer(state, delim);
    String token;
    //extra data
    if (tokens.hasMoreTokens()) {
      token = tokens.nextToken();
      if(!NA.equalsIgnoreCase(token)) setFileLength(Long.valueOf(token).longValue()); 
    }
    if (tokens.hasMoreTokens()) {
      token = tokens.nextToken();
      if(!NA.equalsIgnoreCase(token)) setIsReadOnly(Boolean.valueOf(token).booleanValue());
    }
    if (tokens.hasMoreTokens()) {
      token = tokens.nextToken();
      if(!NA.equalsIgnoreCase(token)) setIsHidden(Boolean.valueOf(token).booleanValue());
    }
  }
  
  public void load(String datasourceName, File root, File f) throws CanNotMaterialize {
    if (f.isDirectory()) throw new CanNotMaterialize("File System Origin", "Directories can not be materialized", f.getAbsolutePath());
    String location = f.getParentFile().getAbsolutePath().toLowerCase();
    if (location.equalsIgnoreCase(root.getAbsolutePath())) location = Names.PATH_SEPARATOR;
    else if (location.startsWith(root.getAbsolutePath().toLowerCase())) location = location.substring(root.getAbsolutePath().length());

    setDatasourceName(datasourceName);
    setName(f.getName());
    setLocation(location);
    setTimeOfCreationInMillis(f.lastModified());
    if (f.exists()) {
      setFileLength(f.length());
      setIsHidden(f.isHidden());
      setIsReadOnly(f.canWrite() && !f.canRead());
    }
  }  
  
  
  public String toString() {
    StringBuffer b = new StringBuffer();
    b.append(getDomainName()).append(delim).append(getServerName()).append(delim).append(getDatasourceType()).append(delim).append(getDatasourceName()).append(delim).append(getTimeOfCreationInMillis()).append(delim).append(getLocation()).append(delim).append(getName()).append(delim).append(getFileLength()).append(delim).append(getIsReadOnly()).append(delim).append(getIsHidden());
    return b.toString();
  }

  public String toXML() { return null; }


  //State(us) variables:
  private boolean isReadOnly=false;
  private boolean isHidden=false;
  private long fileLength=0;
}
