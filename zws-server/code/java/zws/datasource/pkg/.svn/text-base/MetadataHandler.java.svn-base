package zws.datasource.pkg; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.*;
import zws.origin.Origin;
import zws.origin.OriginMaker;

import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

//+++ merge this with intralink WorkspaceHandler
public class MetadataHandler extends DefaultHandler {

  public void startElement (String uri, String name, String qName, Attributes atts) { 
    if ( qName.equalsIgnoreCase("metadata")) { pushComponent(atts); return; }
    if (readRootComponentsOnly) { return; } //skip instances and sub components
    if ( qName.equalsIgnoreCase("instance")) { pushInstance(atts); return; }
    if ( qName.equalsIgnoreCase("sub-component")) { pushSubComponent(atts); return; }
    //+++ handle exceptions
  }
  
  public void endElement (String uri, String name, String qName) {
    if ( !(qName.equalsIgnoreCase("metadata") || qName.equalsIgnoreCase("instance") || qName.equalsIgnoreCase("sub-component"))) return; 
    if ( !qName.equalsIgnoreCase("metadata") && readRootComponentsOnly) { return; } //skip instances and sub components
    Metadata parent=null;
    Metadata m = (Metadata)stack.pop();
    if (!stack.isEmpty()) parent = (Metadata)stack.peek();
    if (null==parent) components.add(m);
    else if (m instanceof zws.data.MetadataFamilyInstance) parent.addFamilyInstance((MetadataFamilyInstance)m);
    else if (m instanceof zws.data.MetadataSubComponent) parent.addSubComponent((MetadataSubComponent)m);
  }

  private void pushComponent(Attributes atts) {
    try {
      Metadata data = unmarshallComponent(atts);
      stack.push(data);
    }
    catch (Exception e) { e.printStackTrace(); /*++ log this error*/ }
  }

  private void pushInstance(Attributes atts) {
    try {
      Metadata data = unmarshallComponent(atts);
      MetadataFamilyInstanceBase member = new MetadataFamilyInstanceBase(data);
      stack.push(member);
    }
    catch (Exception e) { e.printStackTrace(); /*++ log this error*/ }
  }

  private void pushSubComponent(Attributes atts) {
    try {
      Metadata data = unmarshallComponent(atts);
      MetadataSubComponentBase sub= new MetadataSubComponentBase(data);
      int q=1;
      if (null!=data.get(MetadataSubComponent.QUANTITY)) q=Integer.valueOf(data.get(MetadataSubComponent.QUANTITY)).intValue();
      sub.setQuantity(q);
      stack.push(sub);
    }
    catch (Exception e) { e.printStackTrace(); /*++ log this error*/ }
  }

  protected Metadata unmarshallComponent(Attributes atts) throws Exception {
    MetadataBase data = new MetadataBase();
    String key, value;
    for (int idx = 0; idx < atts.getLength(); idx++){
      key = atts.getQName(idx);
      value = atts.getValue(idx);
      if (key.equalsIgnoreCase("origin")) {
        Origin o = OriginMaker.materialize(value);
        data.setOrigin(o);
      }
      else {
        /*
        if (key.equalsIgnoreCase("name")) key = "Name";
        else if (key.equalsIgnoreCase("branch")) key = "Branch";
        else if (key.equalsIgnoreCase("revision")) key = "Revision";
        else if (key.equalsIgnoreCase("version")) key = "Version";
        else if (key.equalsIgnoreCase("release-level")) key = "Release-Level";
        else if (key.equalsIgnoreCase("folder")) key = "Folder";
        else if (key.equalsIgnoreCase("created-on")) {
          key = "Created-On";
          if (value.indexOf(".")>-1) value = convertDottedDate(value);
        }
        else if (key.equalsIgnoreCase("created-by")) key = "Created-By";
         */
        data.set(key,value);
      }
    }
    return data;
  }

  private String convertDottedDate(String dottedDate) {
    StringTokenizer tokens = new StringTokenizer(dottedDate, ".");
    int Y,M,D,h,m,s;
    Y = Integer.valueOf(tokens.nextToken()).intValue();
    M = Integer.valueOf(tokens.nextToken()).intValue();
    D = Integer.valueOf(tokens.nextToken()).intValue();
    h = Integer.valueOf(tokens.nextToken()).intValue();
    m = Integer.valueOf(tokens.nextToken()).intValue();
    s = Integer.valueOf(tokens.nextToken()).intValue();
    return "" + Y + "/" + M + "/" + D + " " + h + ":" + m + ":" +s;
  }

  public Collection getResults() { return components; }
  
  public boolean getReadRootComponentsOnly() { return readRootComponentsOnly; }
  public void setReadRootComponentsOnly(boolean b) { readRootComponentsOnly=b; }
  
  private Collection components = new Vector();
  private Stack stack = new Stack();
  private static String delim = "|";
  private boolean readRootComponentsOnly=false;
} 
