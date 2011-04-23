package com.zws.domo.document;
import com.zws.application.Constants;
import com.zws.domo.Domo;
import com.zws.functor.finder.Finder;
import com.zws.util.KeyValue;

import java.util.*;

public class Document extends Domo implements DocumentInterface{
  public Document() { super(); }

  public Document getDocument() { return this; }

  public Metadata getMetadata() { return metadata; }
  
  public String getRevision(){
    if (null== historyComparator) return null;
    return get(historyComparator.getRevisionMetadata());
  }
  public String getVersion(){
    if (null== historyComparator) return null;
    return get(historyComparator.getVersionMetadata());
  }
  public String getOrigin() { return get(Constants.METADATA_ORIGIN); }
  public void setOrigin(String s) { set(Constants.METADATA_ORIGIN,s); }

  public String getName() { return get(Constants.METADATA_NAME); }
  public void setName(String s) { set(Constants.METADATA_NAME,s); }

  public String getPath() { return get(Constants.METADATA_PATH); }
  public void setPath(String s) { set(Constants.METADATA_PATH,s); }

  //public Metadata getMetadata() { return metaData; }
  public String get(String metadataField) { return metadata.get(metadataField); }
  public Object getDescriptor(String metadataField) { return metadata.getDescriptor(metadataField); }
  public void set(String metadataField, String value) { metadata.set(metadataField, value); }
  public void set(String metadataField, String value, Object descriptor) { 
  	metadata.set(metadataField, value, descriptor); 
  }
  
  public KeyValue lookupKeyValue(String metadataField) { return metadata.lookupKeyValue(metadataField); }
  public Finder getFinder(){ return finder; }
  public void setFinder(Finder f) { finder=f; finder.bind(this);}

  public Collection getAttributes(){ return metadata.getAttributes(); }
  public Set getAttributeNames() { return metadata.getAttributeNames(); }

  public boolean matches(String criteria) {
    if (0 <= getName().toLowerCase().indexOf(criteria.toLowerCase())) return true;
    return metadata.matches(criteria);
  }


  public String toXML(){
    String xml="<document";
//    xml+=getMetadata().toXML();
    xml += " origin=\""+getOrigin()+" name=\""+getName()+"\" Revision=\""+get("Revision")+"\" Version =\""+get("Version")+"\"";
    Iterator i=null;
    if ( (null==children || children.isEmpty()) && (null==getHistory() || getHistory().isEmpty()) ) xml +="/>\n";
    else {
      xml+=">\n";
      if (null!=getChildren()) {
        i = getChildren().iterator();
        xml += ((Reference)i.next()).toXML();
      }
      if (null!=getHistory()) {
        i = getHistory().iterator();
        while (i.hasNext()) xml += "  "+((Document)i.next()).toXML();
      }
      xml+= "</document>\n";
    }

    return xml;
  }

  //setHistoryComparator(..) before adding to history
  public void setHistoryComparator(HistoryComparator c) { historyComparator = c; history = new TreeSet(c); }
  public void addToHistory(DocumentInterface doc) { history.add(doc.getDocument()); }
  public Collection getHistory() { return history; }

  public void add(Reference r) { if (null==children) children=new Vector(); children.add(r); }
  public Collection getChildren() { return children; }
  public void setChildren(Collection c) { children=c; }

  private Metadata metadata = new Metadata();
  private SortedSet history = null;
  private HistoryComparator historyComparator=null;
  public Collection children = null;

//  private String origin;
//  private String serviceName = null;
//  private String dataSourceName = null;
//  private String finderSpec = null;

  private Finder finder = null;
}