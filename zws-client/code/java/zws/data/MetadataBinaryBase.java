package zws.data;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 3, 2004, 1:31 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.Origin;

import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Map;
import java.util.SortedSet;

public class MetadataBinaryBase  extends MetadataBase implements MetadataBinary  {

  public MetadataBinaryBase(MetadataBase data){ metadata=data; }
  public String toString() {
    try {
      StringBuffer xml = new StringBuffer();
      write(xml, TAG_NAME);
      return xml.toString();
    }
    catch (Exception e) { e.printStackTrace(); return e.getMessage();}
  }

  public MetadataBase getMetadataBase() { return metadata.getMetadataBase(); }
  public void setOrigin(Origin o) { ((MetadataBase)metadata).setOrigin(o); }
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
  public void write(StringBuffer xml, String tagName) throws Exception {
    if (null==metadata) xml.append(" data is null");
    else { metadata.write(xml, tagName); }
  }
  public void write(StringBuffer xml, Collection metadataFields) throws Exception {
    if (null==metadata) xml.append(" data is null");
    else { metadata.write(xml, TAG_NAME, metadataFields); }
  }
  public void write(StringBuffer xml, String tagName, Collection metadataFields) throws Exception {
    if (null==metadata) xml.append(" data is null");
    else { metadata.write(xml, tagName, metadataFields); }
  }
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
  public void addLink(Metadata data) { metadata.addLink(data); }
  public void addSubComponent(MetadataSubComponent sub) { metadata.addSubComponent(sub); }
  public void addFamilyInstance(MetadataFamilyInstance instance) { metadata.addFamilyInstance(instance); }
  public void merge (Metadata input, boolean keepValuesProtected) { metadata.merge(input, keepValuesProtected); }

  public MetadataBinary convert(String toType, String location) {
    // TODO Auto-generated method stub
    return null;
  }
  public InputStream download() {
    // TODO Auto-generated method stub
    return null;
  }
  public MetadataBinary generate() {
    // TODO Auto-generated method stub
    return null;
  }

  public String getBaseName() {
    // TODO Auto-generated method stub
    return null;
  }

  public String getExtention() {
    // TODO Auto-generated method stub
    return null;
  }

  public String getFileType() {
    // TODO Auto-generated method stub
    return null;
  }

  public String getFilename() {
    // TODO Auto-generated method stub
    return null;
  }

  public Origin getSource() {
    // TODO Auto-generated method stub
    return null;
  }

  public URL getURL() {
    // TODO Auto-generated method stub
    return null;
  }

  public boolean isAvailable() {
    // TODO Auto-generated method stub
    return false;
  }

  public boolean isDerived() {
    // TODO Auto-generated method stub
    return false;
  }




  private Metadata metadata=null;
  private static String TAG_NAME="binary-data";

}
