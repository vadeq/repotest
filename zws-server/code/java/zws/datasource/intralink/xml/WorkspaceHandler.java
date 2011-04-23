package zws.datasource.intralink.xml;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 6, 2004, 8:00 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.DatasourceAccess;
import zws.attribute.zwsBoolean;
import zws.data.*;
import zws.log.failure.Failure;

import java.util.*;

import org.xml.sax.Attributes;

public class WorkspaceHandler extends IntralinkResultHandler {
  public void startElement (String uri, String name, String qName, Attributes atts) { 
    if ( qName.equalsIgnoreCase("metadata")) { pushComponent(atts); return; }
    if ( qName.equalsIgnoreCase("sub-component")) { pushSubComponent(atts); return; }
    if ( qName.equalsIgnoreCase("instance")) { pushInstance(atts); return; }
    try {
      if ( qName.equalsIgnoreCase("failed-to-authenticate")) { getStorable().log(new Failure("err.invalid.authentication")); return; }
    }
    catch (Exception e) { e.printStackTrace(); }
  }

  public void endElement (String uri, String name, String qName) {
    if ( !(qName.equalsIgnoreCase("metadata") || qName.equalsIgnoreCase("sub-component") || qName.equalsIgnoreCase("instance"))) return; 
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

  private void pushSubComponent(Attributes atts) {
	  try {
	    Metadata data = unmarshallComponent(atts);
	    MetadataSubComponentBase sub = new MetadataSubComponentBase(data);
	    stack.push(sub);
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

  /*
  protected Metadata unmarshallComponent(Attributes atts) throws Exception {
    MetadataBase data = new MetadataBase();
    String key, value;
    for (int idx = 0; idx < atts.getLength(); idx++){
      key = atts.getQName(idx);
      value = atts.getValue(idx);
      if (key.equalsIgnoreCase("name")) key = getDatasource().NAME;
      else if (key.equalsIgnoreCase("branch")) key = getDatasource().BRANCH;
      else if (key.equalsIgnoreCase("revision")) key = getDatasource().REVISION;
      else if (key.equalsIgnoreCase("version")) key = getDatasource().VERSION;
      else if (key.equalsIgnoreCase("release-level")) key = getDatasource().RELEASE_LEVEL;
      else if (key.equalsIgnoreCase("folder")) key = getDatasource().FOLDER;
      else if (key.equalsIgnoreCase("created-on")) key = getDatasource().CREATED_ON;
      else if (key.equalsIgnoreCase("created-by")) key = getDatasource().CREATED_BY;
      data.set(key,value);
    }
    String timeOfCreation=data.get(getDatasource().CREATED_ON);
    if (timeOfCreation==null || "".equals(timeOfCreation)) data.set(getDatasource().CREATED_ON, "1/1/1970 12:0:0");
    String s = Server.getDomainName()+delim+Server.getName()+delim+getDatasource().getType()+delim+getDatasource().getName()+delim+getDatasource().parseDate(data.get(getDatasource().CREATED_ON))+delim+data.get(data.BRANCH)+delim+data.get(data.REVISION)+delim+data.get(data.VERSION)+delim+data.get(data.NAME);
    IntralinkOrigin o =(IntralinkOrigin)OriginMaker.materialize(s);
    data.setOrigin(o);
    return data;
  }
*/
  /*
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
*/
  public Collection getResults() { return components; }
  
  private Collection components = new Vector();
  private Stack stack = new Stack();
  private static String delim = "|";
}
