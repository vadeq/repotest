package zws.repository.teamcenter.proxy.xml;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on October 11, 2004, 1:00 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.data.MetadataBase;
import zws.data.MetadataSubComponent;
import zws.data.MetadataSubComponentBase;
import zws.origin.IntralinkOrigin;
import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.repository.teamcenter.TC10Constants;
//import zws.util.{}//Logwriter;

import java.util.Collection;
import java.util.Vector;

import org.xml.sax.Attributes;

public class DependenciesHandler extends TC10ResultHandler {

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
      if (data.NAME.equalsIgnoreCase(key)) key = data.NAME;
      if (data.REVISION.equalsIgnoreCase(key)) key = data.REVISION;
      if (data.VERSION.equalsIgnoreCase(key)) key = data.VERSION;
      if (data.BRANCH.equalsIgnoreCase(key)) key = data.BRANCH;
      if (data.CREATED_ON.equalsIgnoreCase(key)) key = data.CREATED_ON;
      data.set(key, value);
    }
    //String s = Server.getDomainName()+delim+Server.getName()+delim+getRepositoryType()+delim+getRepositoryName()+delim+getTC10Repository().parseDate(data.get(Ilink3Constants.CREATED_ON))+delim+data.get(data.BRANCH)+delim+data.get(data.REVISION)+delim+data.get(data.VERSION)+delim+data.get(data.NAME);
    String s = getTC10Repository().getDomainName()+delim+getTC10Repository().getServerName()+delim+getTC10Repository().getType()+delim+getRepositoryName()+delim+getTC10Repository().parseDate(data.get(TC10Constants.CREATED_ON))+delim+data.get(data.BRANCH)+delim+data.get(data.REVISION)+delim+data.get(data.VERSION)+delim+data.get(data.NAME);
    IntralinkOrigin o =(IntralinkOrigin)OriginMaker.materialize(s);
    data.setOrigin(o);
    MetadataSubComponent sub = new MetadataSubComponentBase(data);
    return sub;
  }

  public Collection getResults(){ return results; }
  private Collection results = new Vector();

  private static String delim = Origin.delim;

}
