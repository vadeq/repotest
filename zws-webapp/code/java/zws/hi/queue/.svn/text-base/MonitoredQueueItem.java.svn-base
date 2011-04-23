/* DesignState - Design Compression Technology
 * @author: Rodney
 * @version: 1.0
 * Created on Mar 5, 2008 3:03:15 PM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.hi.queue;

public class MonitoredQueueItem {
  String priority, id, summary, instruction;

  public String getPriority()             { return priority; }
  public void setPriority(String value)   { priority = value; }

  public String getId()             { return id; }
  public void setId(String value)   { id = value; }

  public String getSummary()              { return summary; }
  public void setSummary(String value)    { summary = value; }

  public String getInstruction()              { return instruction; }
  public void setInstruction(String value)    { instruction = value; }
  
  public String toString() {

    StringBuffer str = new StringBuffer();
    str.append("[MonitoredQueueItem: Priority=").append(getPriority()).append(", ");
    str.append("id=").append(getId()).append(", ");
    str.append("Instruction=").append(getInstruction()).append(", ");
    str.append("Summary=").append(getSummary()).append("]");
    return str.toString();
  }

}
