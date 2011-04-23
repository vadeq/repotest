package zws.hi.intralink.workspace; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Dec 12, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.IntralinkClient;
import zws.data.workspace.IlinkWorkspaceItem;
import zws.util.AdapterBase;
import zws.util.Pair;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class IlinkWorkspaceItemAdapter extends AdapterBase{

  public void adapt(Object o) { adapt((IlinkWorkspaceItem)o); }
  public void adapt(IlinkWorkspaceItem i ) { item=i; }
  
  public void configure(IntralinkClient c) {
    client = c;
    serverName = client.getSelectedServer();
    datasourceName= client.getSelectedRepository();
  }

  public String getName() { return item.getName(); }
  public String getBranch() { return item.getBranch(); }
  public String getRevision() { return item.getRevision(); }
  public String getVersion() { return item.getVersion(); }
  public String getRelease() { return item.getRelease(); }
  public String getFolder() { return item.getFolder(); }
  public String getCreatedOn() { return item.getCreatedOn(); }
  public String getCreatedBy() { return item.getCreatedBy(); }

  public Collection getUserAttributes() throws Exception {
    Collection atts = new Vector();
    Collection c = client.getUserDefinedAttributes();
    Iterator i = c.iterator();
    Pair p;
    String att, value;
    while (i.hasNext()) {
      Object o = i.next();
      att = (String)o;
      value = item.get(att);
      if (null==value) value = "-";
      p = new Pair(att, value);
      atts.add(p);
    }
    return atts;
  }

  //private IntralinkAccess ilinkClient = IntralinkAccess.getAccess();

  //private Map sessionParametters=null;
  private String serverName=null;
  private String datasourceName=null;
  //private String workspaceName=null;
  private IlinkWorkspaceItem item;
  
  private IntralinkClient client=null;
}
