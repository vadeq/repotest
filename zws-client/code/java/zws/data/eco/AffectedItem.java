package zws.data.eco; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Nov 4, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.data.MetadataBase;


import java.util.Collection;
import java.util.Iterator;

public class AffectedItem extends MetadataBase {
  public AffectedItem() {}
  public AffectedItem(Metadata data) {
    setOrigin(data.getOrigin());
    Collection c = data.getFieldNames();
    if(null==c) return;
    Iterator i = c.iterator();
    String field, value;
    while(i.hasNext()) {
      field = (String)i.next();
      value=data.get(field);
      set(field,value);
    }
  }

  public String getItemNumber() {return get(ITEM_NUMBER);}
  public void setItemNumber(String s) {set(ITEM_NUMBER,s);}

  public String getDescription() {return get(DESCRIPTION);}
  public void setDescription(String s) {set(DESCRIPTION,s);}
  public String getOldRev() {return get(OLD_REV);}
  public void setOldRev(String s) {set(OLD_REV,s);}
  public String getNewRev() {return get(NEW_REV);}
  public void setNewRev(String s) {set(NEW_REV,s);}
  public String getOldLifeCyclePhase() {return get(OLD_LIFE_CYCLE_PHASE);}
  public void setOldLifeCyclePhase(String s) {set(OLD_LIFE_CYCLE_PHASE,s);}
  public String getNewLifeCyclePhase() {return get(NEW_LIFE_CYCLE_PHASE);}
  public void setNewLifeCyclePhase(String s) {set(NEW_LIFE_CYCLE_PHASE,s);}

  public String getXMLTagName() { return XML_TAG_NAME; }


  private static String ITEM_NUMBER="item-number";
  private static String DESCRIPTION="description";
  private static String REV="rev";
  private static String OLD_REV="old-rev";
  private static String NEW_REV="new-rev";
  private static String LIFE_CYCLE_PHASE="life-cycle-phase";
  private static String OLD_LIFE_CYCLE_PHASE="old-life-cycle-phase";
  private static String NEW_LIFE_CYCLE_PHASE="new-life-cycle-phase";

  private static String XML_TAG_NAME = "affected-item";

  public static void main(String[] args) {

    AffectedItem i = new AffectedItem();
    i.setItemNumber("123");
    {} //System.out.println(i);
  }
}
