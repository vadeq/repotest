package zws.origin; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;

import java.io.Serializable;
import java.util.*;

/** An Origin is used to uniquely identify a component in a distributed enterprise.
 * A component is uniquely identified by its server name, datasource name and a unique
 * identifier - depending on the type of datasource.
 * <p>
 * For example if the component is a file on a file system, it can be uniquely
 * identfied for a given server and datasource by its path and filename. If a
 * component is stored in Pro/Intralink, it can be uniqely identified for a given
 * server and datasource by its name, revision, version and branch.
 */
public class Origin_old implements Serializable {
  public Origin_old(){}

  public String getDomainName() { return null; }
  public String getUniqueSequence() { return null; } //unique sequence should never include the name
  public long getTimeOfCreationInMillis() { return 0; }
  public List getStateChangeEvents(Origin o) {return null;}
  public boolean isFromSameDatasourceType(Origin o) { return true; }

  /** Creates a new origin using the specified string to set server name data source
   * name and unique id
   * @param value string specifying server name datsource name and unique id.
   */  
  public Origin_old(String value) {
    name = value.substring(value.lastIndexOf('=')+1);
    setValue(value.substring(0, value.lastIndexOf(Names.ORIGIN_DELIMITER))); 
  }
  
  /** Constructs an Origin
   * @param server name of server
   * @param datasource name of datasource
   * @param uid unique identifier
   */  
  public Origin_old(String server, String datasource, String sourceType, long creationDate, String uid) {
      serverName=server;
      datasourceName=datasource;
      datasourceType = sourceType;
      dateCreated = creationDate;
      uniqueID=uid;
  }
  public String getServerName(){ return serverName; }
  public void setServerName(String s){ serverName=s; }
  public String getDatasourceName() { return datasourceName; }
  public void setDatasourceName(String s) { datasourceName=s; }
  public String getDatasourceType() { return datasourceType; }
  public void setDatasourceType(String s) { datasourceType=s; }
  public Date getDateCreated() { return new Date(dateCreated); }
  public Calendar getCreationTime() { 
    Calendar c = new GregorianCalendar();
    c.setTimeInMillis(dateCreated);
    return c;
  }
  public void setDateCreated(Date d) { dateCreated = d.getTime(); }
  public void setDateCreated(long l) { dateCreated = l; }
  public void setDateCreated(Calendar c) { dateCreated = c.getTime().getTime(); }
  public String getUniqueID() { return uniqueID; }
  public void setUniqueID(String uid) { uniqueID=uid; }
  public String getName() { return name; }
  public void setName(String s) { name=s; }
  private String getValue() { return serverName+ Names.ORIGIN_DELIMITER + datasourceName + Names.ORIGIN_DELIMITER + datasourceType + Names.ORIGIN_DELIMITER + dateCreated + Names.ORIGIN_DELIMITER + uniqueID; }
  private void setValue(String s) { 
    StringTokenizer tok = new StringTokenizer(s, Names.ORIGIN_DELIMITER);
    if (tok.hasMoreTokens()){
      serverName = tok.nextToken();
      if (tok.hasMoreTokens()){
        datasourceName = tok.nextToken();
        if (tok.hasMoreTokens()){
          datasourceType = tok.nextToken();
          if (tok.hasMoreTokens()){
            dateCreated = Long.valueOf(tok.nextToken()).longValue();
            if (tok.hasMoreTokens()){
             uniqueID = tok.nextToken();
             while (tok.hasMoreTokens()) uniqueID += Names.ORIGIN_DELIMITER + tok.nextToken();
            }
          }
        }
      }
    }
  }
  
  public boolean isSame(Origin o) {
    if (null==o) return false;
    return (o.getServerName().equals(serverName) && o.getDatasourceName().equals(datasourceName) && o.getUniqueID().equals(uniqueID));
  }
/*  public boolean isEarlier(Origin o) {
    if (null==o) return false;
    return getDateCreated().before(o.getDateCreated()); 
  }
  public boolean isLater(Origin o) {
    if (null==o) return true;
    return getDateCreated().after(o.getDateCreated());
  }*/
  public String toString() { 
    String s =  getValue()+Names.ORIGIN_DELIMITER+"name="+getName();  
    return s;
  }
  private String datasourceName;
  private String serverName;
  private String uniqueID;
  private String datasourceType;
  private String name;
  private long dateCreated;
  
  public static String SQL = "sql";
  public static String ILINK = "ilink";
  public static String PACKAGE = "package";
  public static String FILESYSTEM = "disk";
  public static String ORACLE8i = "Oracle-8i";
}
