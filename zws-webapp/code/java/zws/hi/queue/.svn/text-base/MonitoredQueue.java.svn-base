/* DesignState - Design Compression Technology
 * @author: Rodney
 * @version: 1.0
 * Created on Mar 5, 2008 1:18:01 PM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.hi.queue;

import java.util.*;

public class MonitoredQueue {
  private ArrayList items = new ArrayList();
  private String name, state;

  public String getName()             { return name; }
  public void setName(String value)   { name = value; }

  public String getState()            { return state; }
  public void setState(String value)  { state = value; }

  public void addQueueItem(MonitoredQueueItem value)  { items.add(value); }
  public List getQueueItems()         { return items; }

  public String toString() {

    StringBuffer str = new StringBuffer();

    str.append("[MonitoredQueue: Name=").append(getName()).append(", ");
    str.append("State=").append(getState()).append("]").append("\n");

    for(int i=0; i<items.size(); i++) {
      str.append("\t").append(items.get(i).toString()).append("\n");
    }

    return str.toString();
  }
}


