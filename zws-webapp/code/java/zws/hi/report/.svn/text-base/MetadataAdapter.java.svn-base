package zws.hi.report;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 25, 2003, 10:50 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.IntralinkAccess;
import zws.bill.intralink.BillOfMaterials;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.data.MetadataBinary;
import zws.data.MetadataFamilyInstance;
import zws.data.MetadataSubComponent;
import zws.security.Authentication;
import zws.origin.Origin;
import zws.util.AdapterPrototype;
import zws.util.JavaObjectUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;


public class MetadataAdapter implements Metadata, AdapterPrototype, Cloneable {

  public SortedSet getBranches() { return metadata.getBranches(); }
  public MetadataBase getMetadataBase() { return metadata.getMetadataBase(); }

  public void adapt(Object o) throws Exception { adapt((Metadata)o); }

  public void adapt(Metadata data){
    metadata=data;
    name=data.getName();
    origin=data.getOrigin();
    if (null==fields) return;
    String value;
    values = new String[fields.length];
    for(int i = 0; i<=fields.length-1; i++) {
     // {} //System.out.println("field...."+fields[i]+"\n");
      if(null != fields[i]) {
      value = data.get(fields[i]);
      if (null==value) value="";
      values[i]=value;
      }
      {} //System.out.println("value....."+values[i]+"\n");
    }
      {} //System.out.println(this);
  }

  public Metadata getMetadata() { return metadata; }
  public String getBranch() { return metadata.get(BRANCH); }
  public String getRevision() { return metadata.get(REVISION); }
  public String getVersion() { return metadata.get(VERSION); }
  public String getRelease() { return metadata.get(RELEASE); }
  public String getDatasourceName() { return metadata.get(DATASOURCE);}
  public String getAuthor() { return metadata.get(AUTHOR); }
  public String getTime() { return metadata.get(TIME); }
  public String getFolder() { return metadata.get(FOLDER); }
  public String getDescription() { return metadata.get(DESCRIPTION); }

  public void setMetadataFields(String[] metadataFields) { fields = metadataFields; }

  public Origin getOrigin(){ return origin; }
  public String getName() { return name; }
  public String get(String fieldName) {
    for (int i=0; i < fields.length-1; i++)
      if (fieldName.equals(fields[i]))
        return values[i];
      if ("name".equals(fieldName))
        return getName();
     return null;
  }


/*
  private Collection getHistoryConfiguration(Metadata m) {
    Collection configuration = new Vector();
    MetadataTreeNodeBase revisionTree = new MetadataTreeNodeBase(m);
    configuration.add(revisionTree);
    String key = makeHistoryKey(m);
    String nextKey = key;
    Metadata data=m;
    Metadata nextData=null;
    if (null==m.getHistory()) return configuration;
    Iterator i = data.getHistory().iterator();
    while (i.hasNext()) {
      nextData = (Metadata)i.next();
      nextKey = makeHistoryKey(nextData);
      if (key.equals(nextKey)) revisionTree.add(nextData);
      else {
        key = nextKey;
        revisionTree = new MetadataTreeNodeBase(nextData);
        configuration.add(revisionTree);
      }
    }
    return configuration;
  }

  private String makeHistoryKey(Metadata data) {
    return data.getName()+"|"+data.get(BRANCH)+"|"+data.get(REVISION);
  }
  private String makeBranchKey(Metadata data) { return data.getName()+"|"+data.get(BRANCH); }
*/
  private void throwUnsupportedOperation(String operation) { throw new UnsupportedOperationException("Adapter is read only: ["+getClass().getName()+"."+operation+"]"); }
  public void set(String fieldName, String value) { throwUnsupportedOperation("set(String, String)"); }


  public boolean hasFieldName(String field) { return metadata.hasFieldName(field); }
  public Collection getFieldNames() { throwUnsupportedOperation("getFieldNames()"); return null; }
  public Collection getFieldValues() { throwUnsupportedOperation("getFieldValues()"); return null; }
  public Collection getChildren() { throwUnsupportedOperation("getChildren()"); return null; }
  public TreeSet getHistory() { throwUnsupportedOperation("getHistory()"); return null; }
  public TreeSet getBranchTree() { throwUnsupportedOperation("getBranchTree()"); return null; }
  public Collection getLinks() { throwUnsupportedOperation("getLinks()"); return null; }
  public Collection getBinaries() { throwUnsupportedOperation("getBinaries()"); return null; }
  public Collection getSubComponents() { throwUnsupportedOperation("getSubcomponents()"); return null; }

  public Map getAttributes() { return new HashMap(getMetadata().getAttributes()); }

  public boolean hasAncestorNamed(String name) { return metadata.hasAncestorNamed(name); }
  public Metadata getAncestor() { return metadata.getAncestor(); }
  public void setAncestor(Metadata data) { metadata.setAncestor(data); }
  public boolean hasSubComponents() { return getMetadata().hasSubComponents(); }
  public boolean hasSubComponent(String name) { return getMetadata().hasSubComponent(name); }
  public boolean isLater(Metadata data)  { return metadata.isLater(data); }
  public boolean isEarlier(Metadata data) {return metadata.isEarlier(data); }
  public boolean hasSameOrigin(Metadata data) { return metadata.hasSameOrigin(data); }

  public void addBinary(MetadataBinary binary) { throwUnsupportedOperation("addBinary()");  }
  public void addLink(Metadata metadata) { throwUnsupportedOperation("addLink()");  }
  public void addSubComponent(MetadataSubComponent sub) { throwUnsupportedOperation("addSubcomponents()");  }
  public boolean hasFamilyInstances() { return getMetadata().hasFamilyInstances(); }
  public Collection getFamilyInstances() { return getMetadata().getFamilyInstances(); }
  public void addFamilyInstance(MetadataFamilyInstance sub) { throwUnsupportedOperation("addFamilyInstance()");  }
  public void merge(Metadata data, boolean b) { throwUnsupportedOperation("merge()");  }



  public void write(StringBuffer xml) { throwUnsupportedOperation("write()"); }
  public void write(StringBuffer xml, String tagName) { throwUnsupportedOperation("write()"); }
  public void write(StringBuffer xml, Collection metadataFields) { throwUnsupportedOperation("write(StringBuffer, Collection)"); }
  public void write(StringBuffer xml, String tagname, Collection metadataFields) { throwUnsupportedOperation("write(StringBuffer, Collection)"); }



  public String[] getFields() { return fields; }
  public String[] getValues() { return values; }
  public Object copy() { return deepCopy(); }
  //public Object deepCopy() { throw new UnsupportedOperation("Try using shallowCopy instead of deepCopy"); }
  public Object shallowCopy(){
    try{ return super.clone(); }
    catch (CloneNotSupportedException e) {e.printStackTrace(); return null; }
  }

  public Object deepCopy() {
	  try { return JavaObjectUtil.copy(this); }
	  catch(Exception e) {
	    e.printStackTrace();
	    return null;
	  }
	}

  public boolean supportsDeepCopy() {return true; }
  public String toString() {
    if (null==metadata) return "";
    return metadata.toString();
  }


  public BillOfMaterials getBillOfMaterials(Authentication id){
      //cache the as stored bill - it wont change
    if (null==bill) loadBill(id);
    return bill;
  }
  public BillOfMaterials getLatestBillOfMaterials(Authentication id){
      //(never cache the latest bill - cause it can change
      loadLatestBill(id);
      return latestBill;
  }


  private BillOfMaterials bill = null;
  private BillOfMaterials latestBill = null;

  public void loadBill(Authentication id) {
   try {
    IntralinkAccess ilink = IntralinkAccess.getAccess();
    bill = ilink.reportBill(getOrigin(), id);
   }
   catch (Throwable e) { e.printStackTrace(); }
  }

  public void loadLatestBill(Authentication id) {
   try {
    IntralinkAccess ilink = IntralinkAccess.getAccess();
    latestBill = ilink.reportLatestBill(getOrigin(), id);
   }
   catch (Throwable e) { e.printStackTrace(); }
  }

  public void inactivate() {}

  protected String name;
  protected Origin origin;
  protected String[] fields;
  protected String[] values;
  //private String[] visibleFields; //+++todo
  protected Metadata metadata;

  private static String BRANCH="Branch";
  private static String REVISION="Rev";
  private static String VERSION="Ver";
  private static String DATASOURCE="zws-datasource-search-agent-name";
  private static String RELEASE="Release-Level";
  private static String AUTHOR="Created-By";
  private static String TIME="Created-On";
  private static String FOLDER="folder";
  private static String DESCRIPTION="description";
}
