package zws.data;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 3, 2004, 5:53 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.Origin;

import java.util.*;

public class MetadataSubComponentBase implements MetadataSubComponent {
  public MetadataSubComponentBase() { }
  public MetadataSubComponentBase(Metadata data) { metadata=data; }
  public MetadataBase getMetadataBase() { return metadata.getMetadataBase(); }
  public void setOrigin(Origin o) { ((MetadataBase)metadata).setOrigin(o); }
  public int getQuantity() {
    
    String q = get(QUANTITY);
    
    if (null!=q && q!="") {
      try {
        quantity = Integer.parseInt(q);
      } catch (NumberFormatException e) {
        quantity = 1;
      }
    }
    return quantity; 
  }
  
  public void setQuantity(int i) { quantity=i;  if (null!=metadata) metadata.set(QUANTITY, ""+quantity); }
  public void setComponent(Metadata data) { metadata=data; metadata.set(QUANTITY, ""+quantity); }
  public Metadata getComponent() { return metadata; }
  
  public Origin getOrigin() { return metadata.getOrigin(); }
  public String getName() { return metadata.getName(); }
  public String get(String fieldName) { return metadata.get(fieldName); }
  public void set(String fieldName, String value) { metadata.set(fieldName, value); }
  public boolean hasFieldName(String s) { return metadata.hasFieldName(s); }
  public Collection getFieldNames() { return metadata.getFieldNames(); }
  public Collection getFieldValues() { return metadata.getFieldValues(); }
  public Map getAttributes() { return metadata.getAttributes(); }
  public boolean isLater(Metadata data) { return metadata.isLater(data); }
  public boolean isEarlier(Metadata data) { return metadata.isEarlier(data); }
  public boolean hasSameOrigin(Metadata data) { return metadata.hasSameOrigin(data); }
  public void write(StringBuffer xml) throws Exception {
    if (null==metadata) xml.append("Quantity=" + quantity + " data is null");
    else { metadata.set(QUANTITY, ""+getQuantity()); metadata.write(xml, TAG_NAME); }
  }
  public void write(StringBuffer xml, String tagName) throws Exception {
    if (null==metadata) xml.append("Quantity=" + quantity + " data is null");
    else { metadata.write(xml, tagName); }
  }
  public void write(StringBuffer xml, Collection metadataFields) throws Exception { 
    if (null==metadata) xml.append("Quantity=" + quantity + " data is null");
    else { metadata.write(xml, TAG_NAME, metadataFields); }
  }
  public void write(StringBuffer xml, String tagName, Collection metadataFields) throws Exception { 
    if (null==metadata) xml.append("Quantity=" + quantity + " data is null");
    else { metadata.write(xml, tagName, metadataFields); }
  }
  public Collection getBinaries() { return metadata.getBinaries(); }
  public Collection getLinks() { return metadata.getLinks(); }
  public Collection getSubComponents() { return metadata.getSubComponents(); }
  public Collection getFamilyInstances() { return metadata.getFamilyInstances(); }
  public boolean hasAncestorNamed(String name) { return metadata.hasAncestorNamed(name); }
  public Metadata getAncestor() { return metadata.getAncestor(); }
  public void setAncestor(Metadata data) { metadata.setAncestor(data); }
  public boolean hasSubComponents() { return metadata.hasSubComponents(); }
  public boolean hasSubComponent(String name) { return metadata.hasSubComponent(name); }
  public boolean hasFamilyInstances() { return metadata.hasFamilyInstances(); } 
  public SortedSet getBranches() { return metadata.getBranches(); }
  public void addBinary(MetadataBinary binary) { metadata.addBinary(binary); }
  public void addLink(Metadata data) { metadata.addLink(data); }
  public void addSubComponent(MetadataSubComponent sub) { metadata.addSubComponent(sub); }
  public void addFamilyInstance(MetadataFamilyInstance instance) { metadata.addFamilyInstance(instance); }
  public void merge (Metadata input, boolean keepValuesProtected) { metadata.merge(input, keepValuesProtected); }
  
  public String toString() {
    metadata.set(QUANTITY, ""+quantity); 
    try {
      StringBuffer xml = new StringBuffer();
      write(xml, TAG_NAME);
      return xml.toString();
    }
    catch (Exception e) { e.printStackTrace(); return e.getMessage();}
  }
  
  private int quantity=1;
  private Metadata metadata=null;
  
  private static String TAG_NAME="sub-component";
}
