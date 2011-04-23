package zws.data.configuration;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 3, 2004, 10:25 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.*;
import zws.origin.Origin;
import zws.util.tree.TreeNodeBase;

import java.util.*;

public class MetadataNodeBase extends TreeNodeBase implements MetadataNode {
  //Metadata Interface
  public SortedSet getBranches() { return ((Metadata)getRootNode()).getBranches(); }
  public MetadataBase getMetadataBase() { return metadata.getMetadataBase(); }
  public MetadataNodeBase(Metadata data) { metadata=data; }
  public Object getData() { return metadata; }
  public void setData(Object o) { metadata=(Metadata)o; }
  public Metadata getMetadata() { return metadata; } 
  public void setMetadata(Metadata data) { metadata=data; }
  
  public Origin getOrigin() { return metadata.getOrigin(); }
  public String getName() { return metadata.getName(); } 
  public String get(String fieldName) { return metadata.get(fieldName); }
  public void set(String fieldName, String value) { metadata.set(fieldName, value); }
  public boolean hasFieldName(String s) { return getMetadata().hasFieldName(s); }
  public Collection getFieldNames() { return metadata.getFieldNames(); }
  public Collection getFieldValues() { return metadata.getFieldValues(); }
  public Map getAttributes() { return metadata.getAttributes(); }
  public boolean isLater(Metadata data) { return metadata.isLater(data); }
  public boolean isEarlier(Metadata data) { return metadata.isEarlier(data); }
  public boolean hasSameOrigin(Metadata data) { return metadata.hasSameOrigin(data); }
  public void write(StringBuffer xml) throws Exception { metadata.write(xml); }
  public void write(StringBuffer xml, String tagName) throws Exception { metadata.write(xml, tagName); }
  public void write(StringBuffer xml, Collection metadataFields) throws Exception { metadata.write(xml, metadataFields); }
  public void write(StringBuffer xml, String tagName, Collection metadataFields) throws Exception { metadata.write(xml, tagName, metadataFields); }
  public Collection getBinaries() { return metadata.getBinaries(); } //binary axis
  public Collection getLinks() { return metadata.getLinks(); }     //association axis
  public Collection getSubComponents() { return metadata.getSubComponents(); } //sub component axis
  public boolean hasAncestorNamed(String name) { return metadata.hasAncestorNamed(name); }
  public Metadata getAncestor() { return metadata.getAncestor(); }
  public void setAncestor(Metadata data) { metadata.setAncestor(data); }
  public boolean hasSubComponents() { return metadata.hasSubComponents(); }
  public boolean hasSubComponent(String name) { return metadata.hasSubComponent(name); }
  public Collection getFamilyInstances() { return metadata.getFamilyInstances(); } //sub component axis
  public boolean hasFamilyInstances() { return metadata.hasFamilyInstances(); }
  
  public void addBinary(MetadataBinary binary) { metadata.addBinary(binary); } 
  public void addLink(Metadata metadata) { metadata.addLink(metadata); }
  public void addSubComponent(MetadataSubComponent sub) { metadata.addSubComponent(sub); }
  public void addFamilyInstance(MetadataFamilyInstance instance) { getMetadata().addFamilyInstance(instance); }

  public void merge (Metadata input, boolean keepValuesProtected) { getMetadata().merge(input, keepValuesProtected); }
  
  private Metadata metadata;
}
