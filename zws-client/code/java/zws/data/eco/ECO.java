package zws.data.eco; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Oct 30, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

import zws.data.MetadataBase;


import java.io.Serializable;
import java.util.*;

public class ECO extends MetadataBase implements Serializable {

  public ECO(String ecoNumber, String ecoDescription, String ecoStatus, String ecoWorkflow, String ecoNextStatus) {
    setNumber(ecoNumber);
    setDescription(ecoDescription);
    setStatus(ecoStatus);
    setNextStatus(ecoNextStatus);
    setWorkflow(ecoWorkflow);
  }
  public ECO(String ecoNumber, String type, String ecoDescription,
                  String ecoStatus, String ecoWorkflow, String ecoNextStatus) {
    setNumber(ecoNumber);
    setDescription(ecoDescription);
    setStatus(ecoStatus);
    setNextStatus(ecoNextStatus);
    setWorkflow(ecoWorkflow);
    setType(type);
  }

  /*public boolean isEmpty() {
     return (0==getNumberOfAffectedItems() && "".equals(description) && "".equals(status) && "".equals(workflow) && "".equals(nextStatus));
  }*/
  public String getNumber() { return get("number"); }
  public String getDescription() { return get("description"); }
  public String getStatus() { return get("status"); }
  public String getNextStatus() { return get("nextStatus"); }
  public String getWorkflow() { return get("workflow"); }
  public String getType() { return get("type"); }

  public void setNumber(String s) { set("number",s); }
  public void setDescription(String s) { set("description",s); }
  public void setStatus(String s) { set("status",s); }
  public void setNextStatus(String s) { set("nextStatus",s); }
  public void setWorkflow(String s) { set("workflow",s); }
  public void setType(String s) { set("type",s); }


  public void add(AffectedItem item) { affectedItems.add(item); affectedItemsMap.put(item.getItemNumber(), item); }
  public Map getAffectedItemsMap() { return affectedItemsMap; }
  public Collection getAffectedItems() { return affectedItems; }
  public int getNumberOfAffectedItems() { return affectedItems.size(); }

  public String getXMLTagName() { return "eco"; }


  private Map affectedItemsMap = new HashMap();
  private Collection affectedItems = new Vector();
}
