package zws.datasource.intralink.xml; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.data.MetadataFamilyInstanceBase;
import zws.log.failure.Failure;

import java.util.*;

import org.xml.sax.Attributes;

public class ListWorkspaceHandler extends IntralinkResultHandler {
  public void startElement (String uri, String name, String qName, Attributes atts) { 
    if ( qName.equalsIgnoreCase("metadata")) { loadComponent(atts); return; }
    if ( qName.equalsIgnoreCase("family-table")) { 
      genericName = atts.getValue("Generic");
      instanceRefs = new Vector();
      return;
    }   
    if ( qName.equalsIgnoreCase("instance")) {
      instanceRefs.add(atts.getValue("Name"));
      return; 
    }   
    try {
      if ( qName.equalsIgnoreCase("failed-to-authenticate")) { getStorable().log(new Failure("err.invalid.authentication")); return; }
    }
    catch (Exception e) { e.printStackTrace(); }
  }
  
  public void endElement (String uri, String name, String qName) { 
    if ( qName.equalsIgnoreCase("family-table")) { 
      familyTables.put(genericName, instanceRefs);
      instanceRefs = null;
      return; 
    }    
  }
  
  public void endDocument() {
    Iterator i = familyTables.keySet().iterator();
    String generic;
    String instance;
    Collection instances;
    Metadata parent, child;
    MetadataFamilyInstanceBase member;
    while (i.hasNext()){
      generic = (String)i.next();
      instances = (Collection)familyTables.get(generic);
      if (components.containsKey(generic)) {
        parent = (Metadata)components.remove(generic);
        genericComponents.put(generic, parent);
      }
      else if (genericComponents.containsKey(generic)) parent = (Metadata)genericComponents.get(generic);
      else if (instanceComponents.containsKey(generic)) {
        parent = (Metadata)instanceComponents.get(generic);
        genericComponents.put(generic, parent);
      }
      else throw new RuntimeException("Parsing ListWorkspace- Could not find generic: " + generic);
      Iterator j = instances.iterator();
      while (j.hasNext()) {
        instance = (String)j.next();
        if (components.containsKey(instance)) child = (Metadata)components.remove(instance);
        else if (genericComponents.containsKey(instance)) child = (Metadata)genericComponents.get(instance);
        else if (instanceComponents.containsKey(instance)) child = (Metadata)instanceComponents.get(instance);
        else throw new RuntimeException("Parsing ListWorkspace- Can not place instance: " + instance + " into generic: " + generic);
        member = new MetadataFamilyInstanceBase(child);
        instanceComponents.put(instance, child);
        parent.addFamilyInstance(member);
      }
      try {getStorable().store(parent);}
      catch (Exception e) { e.printStackTrace(); /*++ log this error*/ }
    }
    i = components.values().iterator();
    while (i.hasNext()) {
      try {getStorable().store(i.next());}
      catch (Exception e) { e.printStackTrace(); /*++ log this error*/ }
    }
  }

  private void loadComponent(Attributes atts) {
    try {
      Metadata data = unmarshallComponent(atts);
      components.put(data.getName(), data);
    }
    catch (Exception e) { e.printStackTrace(); /*++ log this error*/ }
  }

  private String genericName=null;
  private Collection instanceRefs=null;
  
  private Map components = new HashMap();
  private Map familyTables = new HashMap();
  private Map genericComponents = new HashMap();
  private Map instanceComponents = new HashMap();
}
