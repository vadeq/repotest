package zws.data;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 28, 2004, 11:59 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.Origin;

import java.util.*;

public class MetadataFamilyInstanceBase implements MetadataFamilyInstance {
  public MetadataFamilyInstanceBase() { }
  public MetadataFamilyInstanceBase(Metadata data) { metadata=data; }
  public MetadataBase getMetadataBase() { return metadata.getMetadataBase(); } 
  public void setComponent(Metadata data) { metadata=data; }
  public Metadata getComponent() { return metadata; }
  
  public Origin getOrigin() { return metadata.getOrigin(); }
  public void setOrigin(Origin o) { ((MetadataBase)metadata).setOrigin(o); }
  public String getName() { return metadata.getName(); }
  public void setName(String s) { ((MetadataBase)metadata).setName(s); }
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
    if (null==metadata) xml.append("Missing family table member!");
    else { metadata.write(xml, INSTANCE_TAG_NAME); }
  }
  public void write(StringBuffer xml, String tagName) throws Exception {
    if (null==metadata) xml.append("Missing family table member!");
    else { metadata.write(xml, tagName); }
  }
  public void write(StringBuffer xml, Collection metadataFields) throws Exception { 
    if (null==metadata) xml.append("Missing family table member!");
    else { metadata.write(xml, INSTANCE_TAG_NAME, metadataFields); }
  }
  public void write(StringBuffer xml, String tagName, Collection metadataFields) throws Exception { 
    if (null==metadata) xml.append("Missing family table member!");
    else { metadata.write(xml, tagName, metadataFields); }
  }
  public Collection getBinaries() { return metadata.getBinaries(); }
  public Collection getLinks() { return metadata.getLinks(); }
  public Collection getSubComponents() { return metadata.getSubComponents(); }
  public Collection getFamilyInstances() { return metadata.getFamilyInstances(); }
  public boolean hasSubComponents() { return metadata.hasSubComponents(); }
  public boolean hasSubComponent(String name) { return metadata.hasSubComponent(name); }
  public boolean hasFamilyInstances() { return metadata.hasFamilyInstances(); } 
  public SortedSet getBranches() { return metadata.getBranches(); }
  public void addBinary(MetadataBinary binary) { metadata.addBinary(binary); }
  public void addLink(Metadata data) { metadata.addLink(data); }
  public boolean hasAncestorNamed(String name) { return metadata.hasAncestorNamed(name); }
  public Metadata getAncestor() { return metadata.getAncestor(); }
  public void setAncestor(Metadata data) { metadata.setAncestor(data); }
  public void addSubComponent(MetadataSubComponent sub) { metadata.addSubComponent(sub); }
  public void addFamilyInstance(MetadataFamilyInstance instance) { metadata.addFamilyInstance(instance); }
  public void merge (Metadata input, boolean keepValuesProtected) { metadata.merge(input, keepValuesProtected); }  

  public String toString() { return metadata.toString(); }
  private Metadata metadata;
  
  private static String INSTANCE_TAG_NAME="instance";
}
