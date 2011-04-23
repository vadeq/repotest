/*
 * DesignState - Design Compression Technology 
 * @author: ptoleti 
 * @version: 1.0 
 * Created on May 25, 2007 12:09:48 PM 
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */
package zws.service.pen;

import zws.exception.Duplicate;
//impoer zws.util.Logwriter;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class RedlineElement {
  public RedlineElement(PENDataElement e) {penDataElement = e;}
  public void redlineAdd(String targetSubComponetName) throws Exception {
    if (redlines.containsKey(targetSubComponetName)) throw new Duplicate("Redline add: " + targetSubComponetName);
    redlineAddList.add(targetSubComponetName);
    redlines.put(targetSubComponetName, "redline-add");
    {}//Logwriter.printOnConsole("Redline add: " + targetSubComponetName + " under " + penDataElement.getItemName());
  }
  public void redlineModify(String targetSubComponetName) throws Exception {
    if (redlines.containsKey(targetSubComponetName)) throw new Duplicate("Redline modify: " + targetSubComponetName);
    redlineModifyList.add(targetSubComponetName);
    redlines.put(targetSubComponetName, "redline-modify");
    {}//Logwriter.printOnConsole("Redline modify: " + targetSubComponetName + " under " + penDataElement.getItemName());
  }
  public void redlineDelete(String targetSubComponetName) throws Exception {
    if (redlines.containsKey(targetSubComponetName)) throw new Duplicate("Redline delete: " + targetSubComponetName);
    redlineDeleteList.add(targetSubComponetName);
    redlines.put(targetSubComponetName, "redline-delete");
    {}//Logwriter.printOnConsole("Redline delete: " + targetSubComponetName + " under " + penDataElement.getItemName());
  }
  public ArrayList getRedlineAddList() {return redlineAddList;}
  public ArrayList getRedlineModifyList() {return redlineModifyList;}
  public ArrayList getRedlineDeleteList() {return redlineDeleteList;}
  public HashMap getRedlines() {return redlines;}
  public void setPENDataElement(PENDataElement e) {penDataElement = e;}
  public PENDataElement getPENDataElement() {return penDataElement;}
  public PENData getPENData() {return penDataElement.getPENData();}

  private PENDataElement penDataElement = null;
  private ArrayList redlineAddList = new ArrayList();
  private ArrayList redlineModifyList = new ArrayList();
  private ArrayList redlineDeleteList = new ArrayList();
  private HashMap redlines = new HashMap();
}
