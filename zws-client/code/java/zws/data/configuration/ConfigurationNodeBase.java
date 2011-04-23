package zws.data.configuration;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 3, 2004, 9:53 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.data.*;
import zws.data.util.MetadataKeyMaker;
import zws.exception.MismatchedKey;
import zws.origin.Origin;
import zws.util.tree.TreeNodeBase;

import java.util.*;

public abstract class ConfigurationNodeBase extends TreeNodeBase implements ConfigurationNode {  
  public ConfigurationNodeBase() {}

  public MetadataBase getMetadataBase() { return metadata.getMetadataBase(); }
  
  protected abstract void placeInConfiguration(Metadata data) throws Exception;
  public abstract Metadata getFirst();
  
  public void selectDefault() { try {select(getFirst().getOrigin()); } catch (Exception e) { e.printStackTrace(); }  } //log error
  
  protected void initializeKeyMaker() { keyMaker.setKeyFields(getNodeLevelKeyFields()); }
  protected void initializeComparator() { }

  public void defineKey(Metadata data) { key=makeKey(data); }
  public void defineKeyFields(String parentLevelKeyFields, String nodeLevelKeyFieldname, String childLevelKeyFieldname) {
    parentKeyFields=parentLevelKeyFields;
    nodeKeyFieldname = nodeLevelKeyFieldname;
    childKeyFieldname = childLevelKeyFieldname;
    initializeKeyMaker();
  }
  
  public boolean contains(Origin o){ if (null==o || null==configurationNodes) return false; return configurationNodes.containsKey(o.toString()); }
  public void select(Origin o) throws Exception {
    if (null==o) { metadata=null; return; }
    if (!contains(o)) throw new Exception("Metadata not found in configuration:" + o);
    metadata=(Metadata)getConfigurationNodes().get(o.toString());
  }

  public String getKey() { return key; }
  public String getNodeLevelKeyFieldname () { return nodeKeyFieldname; }
  public String getNodeLevelKeyValue() { return getKey().substring(getKey().lastIndexOf(Names.DELIMITER)+1); }
  public String getChildLevelKeyFieldname() { return childKeyFieldname; }
  public String getParentLevelKeyFields() { return parentKeyFields; }
  public String getNodeLevelKeyFields() { return parentKeyFields + Names.DELIMITER + nodeKeyFieldname; }
  public String getChildLevelKeyFields() { return getNodeLevelKeyFields() + Names.DELIMITER + childKeyFieldname; }

  public boolean keyMatches(Metadata data) {  return getKey().equals(makeKey(data)); }
  protected String makeKey(Metadata data) { return keyMaker.makeKey(data);}

//  public String getSortKeyFields() { return comparator.getKeyFields(); }
//  protected void setSortKeyFields(String s) { comparator.setKeyFields(s); }
  public boolean getAscendingOrder() { return ascendingOrder; }
  public void setAscendingOrder(boolean b) { ascendingOrder=b; }
  
  public void place(Metadata data) throws MismatchedKey, Exception{
    if(!keyMatchesConfiguration(data)) throw new MismatchedKey(makeKey(data), getKey());
    placeInConfiguration(data);
    getConfigurationNodes().put(data.getOrigin().toString(), data);
  }
  
  public boolean keyMatchesConfiguration(Metadata data) { return keyMatches(data); }
  
  public ConfigurationNode findMatchingChild(Metadata data) {
    if (null==getChildren()) return null;
   Iterator i = getChildren().iterator();
   String dataKey = makeKey(data);
   ConfigurationNode tree;
   while (i.hasNext()) {
     tree=(ConfigurationNode)i.next();
     if (tree.keyMatchesConfiguration(data)) return tree;
   }
   return null;
  }
  public SortedSet getChildren(){ if (null==super.getChildren()) initializeChildren(); return super.getChildren(); }
  
  protected void placeInNewChild(ConfigurationNode child, Metadata data) throws Exception {
   child.setParent(this);
   child.place(data);
   child.setAscendingOrder(getAscendingOrder());
   getChildren().add(child);
  }

  protected abstract Comparator getComparator();
  protected void initializeChildren() { initializeComparator(); setChildren(new TreeSet(getComparator())); }
  
  protected MetadataKeyMaker getKeyMaker() { return keyMaker; }
  
  //Metadata Interface
  public Origin getOrigin() { return getMetadata().getOrigin(); }
  public String getName() { return getMetadata().getName(); } 
  
  public String get(String fieldName) { return getMetadata().get(fieldName); }
  public void set(String fieldName, String value) { getMetadata().set(fieldName, value); }
  public boolean hasFieldName(String s) { return getMetadata().hasFieldName(s); }
  public Collection getFieldNames() { return getMetadata().getFieldNames(); }
  public Collection getFieldValues() { return getMetadata().getFieldValues(); }
  public Map getAttributes() { return getMetadata().getAttributes(); }
  public boolean isLater(Metadata data) { return getMetadata().isLater(data); }
  public boolean isEarlier(Metadata data) { return getMetadata().isEarlier(data); }
  public boolean hasSameOrigin(Metadata data) { return getMetadata().hasSameOrigin(data); }
  public void write(StringBuffer xml) throws Exception { getMetadata().write(xml); }
  public void write(StringBuffer xml, String tagName) throws Exception { getMetadata().write(xml, tagName); }
  public void write(StringBuffer xml, Collection metadataFields) throws Exception { getMetadata().write(xml, metadataFields); }
  public void write(StringBuffer xml, String tagName, Collection metadataFields) throws Exception { getMetadata().write(xml, tagName, metadataFields); }
  public Collection getBinaries() { return getMetadata().getBinaries(); } //binary axis
  public Collection getLinks() { return getMetadata().getLinks(); }     //association axis
  public Collection getSubComponents() { return getMetadata().getSubComponents(); } //sub component axis
  public boolean hasAncestorNamed(String name) { return getMetadata().hasAncestorNamed(name); }
  public Metadata getAncestor() { return getMetadata().getAncestor(); }
  public void setAncestor(Metadata data) { getMetadata().setAncestor(data); }
  public boolean hasSubComponents() { return getMetadata().hasSubComponents(); }
  public boolean hasSubComponent(String name) { return metadata.hasSubComponent(name); }
  public Collection getFamilyInstances() { return getMetadata().getFamilyInstances(); } //sub component axis
  public boolean hasFamilyInstances() { return getMetadata().hasFamilyInstances(); }
  
  public void addBinary(MetadataBinary binary) { getMetadata().addBinary(binary); } 
  public void addLink(Metadata metadata) { getMetadata().addLink(metadata); }
  public void addSubComponent(MetadataSubComponent sub) { getMetadata().addSubComponent(sub); }
  public void addFamilyInstance(MetadataFamilyInstance instance) { getMetadata().addFamilyInstance(instance); }
  public void merge (Metadata input, boolean keepValuesProtected) { getMetadata().merge(input, keepValuesProtected); }
  

  //Provide access to internal data for swaping nodes
  protected void setKey(String s) { key=s; }
  protected Metadata getMetadata() { return metadata; }
  protected void setMetadata(Metadata data) { metadata=data; } //only used for swaping nodes
  protected Map getConfigurationNodes() { if (null==configurationNodes) configurationNodes=new HashMap(); return configurationNodes; }
  protected void setConfigurationNodes(Map m) { configurationNodes=m; }

  protected void nullify() {
    super.nullify();
    key=null;
    parentKeyFields=null;
    nodeKeyFieldname=null;
    childKeyFieldname=null;
    metadata=null; 
    keyMaker = new MetadataKeyMaker();
    //comparator = new SortMetadataByKey(); 
  }

  protected void swap(ConfigurationNodeBase node) {
    super.swap(node);
    String k=key;
    String pKeyFields=parentKeyFields;
    String nKeyFieldname=nodeKeyFieldname;
    String cKeyFieldname=childKeyFieldname;
    Metadata m=metadata;
    Map config=configurationNodes;
    MetadataKeyMaker kMaker= keyMaker;
    boolean sort = ascendingOrder;
    //SortMetadataByKey c = comparator;
    
    key=node.getKey();
    parentKeyFields=node.getParentKeyFields();
    nodeKeyFieldname=node.getNodeLevelKeyFieldname();
    childKeyFieldname=node.getChildLevelKeyFieldname();
    metadata=node.getMetadata();
    configurationNodes=node.getConfigurationNodes();
    keyMaker= node.getKeyMaker();
    ascendingOrder=node.getAscendingOrder();
    //comparator = node.getComparator();
    
    node.setKey(k);
    node.setParentKeyFields(pKeyFields);
    node.setNodeLevelKeyFieldname(nKeyFieldname);
    node.setChildLevelKeyFieldname(cKeyFieldname);
    node.setMetadata(m);
    node.setConfigurationNodes(config);
    node.setKeyMaker(kMaker);
    node.setAscendingOrder(sort);
    //node.setComparator(c);
  }
  
  protected String getParentKeyFields() { return parentKeyFields; }
  protected void setParentKeyFields(String s) { parentKeyFields=s; }
  protected void setNodeLevelKeyFieldname(String s) { nodeKeyFieldname=s; }
  protected void setChildLevelKeyFieldname(String s) { childKeyFieldname=s; }
  protected void setKeyMaker(MetadataKeyMaker m) { keyMaker=m; }
  //protected void setComparator(SortMetadataByKey c) { comparator=c; }

  private String key=null;
  private String parentKeyFields=null;
  private String nodeKeyFieldname=null;
  private String childKeyFieldname=null;
  private Metadata metadata=null;
  private Map configurationNodes=null;
  private boolean ascendingOrder=false;
  private MetadataKeyMaker keyMaker= new MetadataKeyMaker();;
}
