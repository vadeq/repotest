package zws.data.intralink;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 29, 2004, 5:48 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Constants;
import zws.data.Metadata;

import java.io.Serializable;
import java.util.*;

public class RTPForm implements Serializable {
  public Collection getComponents() { return components; }
  public void add(Metadata component) { components.add(component); }

  public String getServerName() { return serverName; }
  public void setServerName(String s) { serverName=s; }
  public String getDatasourceName() { return datasourceName; }
  public void setDatasourceName(String s) { datasourceName=s; }
  public String getName() { return name; }
  public void setName(String s) { name=s; }
  public String getFolder() { return folder; }
  public void setFolder(String s) { folder=s; }
  public String getDescription() { return description; }
  public void setDescription(String s) { description=s; }
  public String getPromoteTo() { return promoteTo; }
  public void setPromoteTo(String s) { promoteTo=s; }
  public Date getDateCreated() { return new Date(dateCreated); }
  public void setDateCreated(Date d) { dateCreated = d.getTime(); }
  public void setDateCreated(long l) { dateCreated = l; }

  public String toString() { return toXML(); }
  public String toXML() {
    String s = "<intralink-rtp-form Name=\""+name+"\" promote-to=\""+promoteTo+"\" Folder=\""+folder+"\" date=\""+dateCreated+"\" description=\""+description+"\" >" + Constants.NEW_LINE;
    Iterator i = components.iterator();
    while (i.hasNext()) s += "  " + i.next() + Constants.NEW_LINE;
    s += "</intralink-rtp-form>" + Constants.NEW_LINE;
    return s;
  }
  private String serverName=null;
  private String datasourceName=null;
  private String name=null;
  private String folder=null;
  private String description=null;
  private String promoteTo=null;
  private long dateCreated=0;
  private Collection components = new Vector();
}
