package zws.datasource.intralink.xml;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on October 11, 2004, 1:00 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.data.*;
import zws.datasource.intralink.IntralinkSource;
import zws.origin.*;
//import zws.util.{}//Logwriter;

import java.util.Collection;
import java.util.Vector;

import org.xml.sax.Attributes;

public class GetDependenciesHandler extends IntralinkResultHandler {

  public void startElement (String uri, String name, String qName, Attributes atts) {
    if ( qName.equalsIgnoreCase("sub-component")) { addSubComponent(atts); return; }
    if ( qName.equalsIgnoreCase("failed-to-authenticate")) { {}//Logwriter.printOnConsole("err.invalid.authentication"); 
      return; }
  }
  
  private void addSubComponent(Attributes atts) {
    try { results.add(unmarshallSubComponent(atts)); }
    catch (Exception e) { e.printStackTrace(); /*++ log this error*/ }
  }

  //remove this function when getDependencies toolkit returns properly cased attributes for Name, Branch, Revision, Version, Created-On
  protected MetadataSubComponent unmarshallSubComponent(Attributes atts) throws Exception {
    MetadataBase data = new MetadataBase();
    String key, value;
    for (int idx = 0; idx < atts.getLength(); idx++) {
      key = atts.getQName(idx);
      value = atts.getValue(idx);
      if (MetadataBase.NAME.equalsIgnoreCase(key)) key = MetadataBase.NAME;
      if (MetadataBase.REVISION.equalsIgnoreCase(key)) key = MetadataBase.REVISION;
      if (MetadataBase.VERSION.equalsIgnoreCase(key)) key = MetadataBase.VERSION;
      if (MetadataBase.BRANCH.equalsIgnoreCase(key)) key = MetadataBase.BRANCH;
      if (MetadataBase.CREATED_ON.equalsIgnoreCase(key)) key = MetadataBase.CREATED_ON;
      data.set(key, value);
    }
    String s = Server.getDomainName()+delim+Server.getName()+delim+getDatasource().getType()+delim+getDatasource().getName()+delim+getDatasource().parseDate(data.get(IntralinkSource.CREATED_ON))+delim+data.get(data.BRANCH)+delim+data.get(data.REVISION)+delim+data.get(data.VERSION)+delim+data.get(data.NAME);
    IntralinkOrigin o =(IntralinkOrigin)OriginMaker.materialize(s);
    data.setOrigin(o);
    MetadataSubComponent sub = new MetadataSubComponentBase(data);
    return sub;
  }

  public Collection getResults(){ return results; }
  private Collection results = new Vector();
  
  private static String delim = Origin.delim;
  
}
