package zws.synchronization;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 31, 2004, 11:12 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.CircularDependency;
import zws.origin.Origin;

import java.util.Calendar;

public class SynchronizationRecordBase implements SynchronizationRecord {
  public SynchronizationRecordBase() {}
  public SynchronizationRecordBase(Origin a, Origin b) throws CircularDependency { setData(a,b); }
  
  public void setData(Origin a, Origin b) throws CircularDependency{
    String keyA = key(a.getDomainName(), a.getServerName(), a.getDatasourceName(), a.getUniqueID());
    String keyB = key(b.getDomainName(), b.getServerName(), b.getDatasourceName(), b.getUniqueID());
    if (keyA.equalsIgnoreCase(keyB)) throw new CircularDependency("Can not synchronize to self: " + keyA);
    if (isMin(keyA, keyB)) {
      origin0 = a;
      originA = b;
    }
    else {
      origin0 = b;
      originA = a;
    }
  }
  private String key(String domainName, String serverName, String datasourceName, String uid) {
    StringBuffer b = new StringBuffer();
    return b.append(domainName.toLowerCase()).append(serverName.toLowerCase()).append(datasourceName.toLowerCase()).append(uid.toLowerCase()).toString();
  }
  
  private boolean isMin(String key, String compare) {
    if (key.compareTo(compare)<0) return true;
    return false;
  }
  
  public Origin getOrigin0() { return origin0; }
  public Origin getOriginA() { return originA; }

  public String getDomainName0() { return origin0.getDomainName();}
  public String getServerName0() { return origin0.getServerName();}
  public String getDatasourceName0() { return origin0.getDatasourceName();}
  public String getDatasourceType0() { return origin0.getDatasourceType();}
  public Calendar getTimeOfCreation0() { return origin0.getTimeOfCreation();}
  public long getTimeOfCreation0InMillis() { return origin0.getTimeOfCreationInMillis();}
  public String getUID0() { return origin0.getUniqueID();}
  public String getName0() { return origin0.getName();}
  public String getUniqueSequence0() { return origin0.getUniqueSequence();}
  public String getFileType0() { return origin0.getFileType();}
  public String getLocation0() { return origin0.getLocation();}
  public String getState0() { return origin0.getState();}
 
  public String getDomainNameA() { return originA.getDomainName();}
  public String getServerNameA() { return originA.getServerName();}
  public String getDatasourceNameA() { return originA.getDatasourceName();}
  public String getDatasourceTypeA() { return originA.getDatasourceType();}
  public Calendar getTimeOfCreationA() { return originA.getTimeOfCreation();}
  public long getTimeOfCreationAInMillis() { return originA.getTimeOfCreationInMillis();}
  public String getUIDA() { return originA.getUniqueID();}
  public String getNameA() { return originA.getName();}
  public String getUniqueSequenceA() { return originA.getUniqueSequence(); }
  public String getFileTypeA() { return originA.getFileType();}
  public String getLocationA() { return originA.getLocation();}
  public String getStateA() { return originA.getState();}

  public String toString() { return "["+origin0+" =~= "+originA+"]"; }

  private Origin origin0=null;
  private Origin originA=null;

}
