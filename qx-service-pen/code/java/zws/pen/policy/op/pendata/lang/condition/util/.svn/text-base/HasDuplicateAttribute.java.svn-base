/* DesignState - Design Compression Technology
 * @author: Rodney
 * @version: 1.0
 * Created on Apr 8, 2008 12:14:43 PM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.lang.condition.util;

import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;
import zws.data.Metadata;
import zws.service.pen.PENData;
import zws.service.pen.PENDataElement;

import java.util.*;

import java.util.Iterator;

public class HasDuplicateAttribute extends ConditionOPBase {

  private String element = "src";
  private String attribute = null;
  
  public Boolean evaluateCondition() throws Exception {
    /*
     * TODO: Add code to not consider items that have status 
     * flags set to true for filtered-out or ignore 
     */
    PENData pen = getPenData();
    PENDataElement penElement;
    PENDataElement current;
    
    if (element == null || !(element.equals("tx") ||
         element.equals("src") || element.equals("target"))) {
      throw new Exception(getClass().getName() + " requires element type of tx, src, or target");
    }
      
    String value=null, dataValue=null;
    Iterator i = pen.getReferenceTableCopy().keySet().iterator();
    Metadata data;
    String item;
    current = pen.lookupPENDataElement(getCurrentItem());
    data = getMetaData(element, current);
    value = data.get(attribute);
    if (null==value) value="";
    
    boolean r = false;
    while (i.hasNext()) {
      item = (String) i.next();
      if (item.equals(getCurrentItem())) continue;
      
      penElement = pen.lookupPENDataElement(item);
      dataValue=null;
      data = getMetaData(element, penElement);
      if (data!=null ) dataValue= data.get(attribute);
      if (null==dataValue) dataValue="";
      if (value.equals(dataValue)) r = true;
    }    
    return new Boolean(r);
  }

  private Metadata getMetaData(String type, PENDataElement element) {
    
    Metadata data = null;
    
    if (type.equals("src")) {
      data = element.getSourceDataElement().getSourceData();
    } else if (type.equals("tx")) {
      data = element.getTxDataElement().getTxData();
    } else if (type.equals("target")) {
      data = element.getTargetDataElement().getTargetData();
    }
    
    return data;
  }
  
  public void setElement(String value)    { 
    if (value != null) element = value.toLowerCase();
  }
  public String getElement()              { return element; }  
  public String getAttribute()              { return attribute; }  
  public void setAttribute(String value)  { attribute = value; }
}
