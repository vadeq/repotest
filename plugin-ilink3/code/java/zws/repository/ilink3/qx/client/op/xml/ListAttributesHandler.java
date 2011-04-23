package zws.repository.ilink3.qx.client.op.xml;/*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Apr 12, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.datasource.intralink.IntralinkAttribute;
import java.util.Collection;
import java.util.Vector;

import org.xml.sax.Attributes;


public class ListAttributesHandler extends IntralinkResultHandler {

  public void startElement (String uri, String name, String qName, Attributes atts) {
    if ( qName.equalsIgnoreCase("attribute")) { addAttribute(atts); return; }
  }
      
  private void addAttribute(Attributes atts) {
    try { results.add(unmarshallAttribute(atts)); }
    catch (Exception e) { e.printStackTrace(); /*++ log this error*/ }
  }

  //remove this function when getDependencies toolkit returns properly cased attributes for Name, Branch, Revision, Version, Created-On
  protected IntralinkAttribute unmarshallAttribute(Attributes atts) throws Exception {
    IntralinkAttribute attribute = new IntralinkAttribute();
    String key, value;
    for (int idx = 0; idx < atts.getLength(); idx++) {
      key = atts.getQName(idx);
      value = atts.getValue(idx);
      if ("name".equalsIgnoreCase(key)) attribute.setName(value);
      if ("description".equalsIgnoreCase(key)) attribute.setDescription(value);
      if ("type".equalsIgnoreCase(key)) attribute.setType(value);
      if ("is-file-based".equalsIgnoreCase(key)) attribute.setIsFileBased(Boolean.valueOf(value).booleanValue());
      if ("is-versioned".equalsIgnoreCase(key)) attribute.setIsVersioned(Boolean.valueOf(value).booleanValue());
      if ("is-life-cycle".equalsIgnoreCase(key)) attribute.setIsLifeCycle(Boolean.valueOf(value).booleanValue());
      if ("is-indexed".equalsIgnoreCase(key)) attribute.setIsIndexed(Boolean.valueOf(value).booleanValue());
    }
    attribute.setIsUserDefined(true);
    return attribute;
  }

  public Collection getResults(){ return results; }
  private Collection results = new Vector();
}
