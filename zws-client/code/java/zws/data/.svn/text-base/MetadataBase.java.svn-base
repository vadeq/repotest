package zws.data; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.Origin;
import zws.xml.util.XMLString;

import java.util.*;

/** A set of name, value pairs for a given origin. */
public class MetadataBase implements Metadata {
  public MetadataBase() {}
  public MetadataBase(Metadata source) { merge(source); }
  public MetadataBase getMetadataBase() { return this; }
  public SortedSet getBranches() { return new TreeSet(); }
  /** Returns the origin
   * @return return the origin
   */
  public Origin getOrigin() { return origin; }

  /** Sets the origin
   * @param serverName name of server
   * @param datasourceName name of datasource
   * @param uniqueID unique identifier
   */
  /*
  public void setOrigin(String domainName, String serverName, String datasourceType, String datasourceName, String idTokens) {
    setOrigin(OriginMaker.materialize(domainName, serverName, datasourceType, datasourceName, 0, idTokens));
//      getOrigin().setServerName(serverName);
//      getOrigin().setDatasourceName(datasourceName);
//      getOrigin().setUniqueID(uniqueID);
  }
*/
  /** Sets the Origin
   * @param o the Origin
   */
  public void setOrigin(Origin o) { origin=o; if (null!=o)setName(o.getName()); }

  /** Returns the name
   * @return name of component
   */
  public String getName() { return name; }

  /** Sets the name
   * @param s Name of component
   */
  public void setName(String s) { name=s; }

  /** Returns the value of a field
   * @param fieldName Name of field
   * @return Value specified by fieldName
   */
  public String get(String fieldName) { //++optimize for string performance
    if (null==fieldName || EMPTY_STRING.equals(fieldName))
      throw new NullPointerException("No field name specified when geting value of metadata field.");
    String field = fieldName.trim();
    if (NAME.equalsIgnoreCase(field)) return getName();
    return (String)getAttributes().get(field);
  }


  public boolean hasFieldName(String s) { return getAttributes().containsKey(s); }

  /** Sets the value of a field
   * @param fieldName Name of field
   * @param value Value to set the field to
   */
  public void set(String fieldName, String value) { //++optimize for string performance
    if (null==fieldName || EMPTY_STRING.equals(fieldName))
      throw new NullPointerException("No field name specified when setting value of metadata field.");
    String field = fieldName.trim();
    if (NAME.equalsIgnoreCase(field)) setName(value);
    else {
      if (!getAttributes().containsKey(field)) fieldNames.add(field);
      getAttributes().put(field, value);
    }
  }

  /** Adds the set of fields contained in data
   * @param data The Metadata to retrieve data from
   */
  public void merge(Metadata input) { merge(input, false); }
  public void merge(Metadata input, boolean keepValuesProtected) {
    String field;
    Iterator i = input.getFieldNames().iterator();
    while (i.hasNext()) {
      field = (String) i.next();
      if (!hasFieldName(field) || !keepValuesProtected) set(field, input.get(field));
    }
    if (input.hasSubComponents()) {
      i = input.getSubComponents().iterator();
      while (i.hasNext()) addSubComponent((MetadataSubComponent)i.next());
    }

  }

  /** Adds the set of fields contained in data. Field names are prefixed before
   * adding.
   * @param prefix prefix that should be added to each field
   * @param data Metadata to retrieve data from
   */
  public void merge(String prefix, Metadata data) {
    String field;
    Iterator i = data.getFieldNames().iterator();
    while (i.hasNext()) { //+++select and merge subcomponents as well
      field = (String) i.next();
      set(prefix + "." + field, data.get(field));
    }
  }

  /** Returns the collection of fields
   * @return Collection of field names (Strings)
   */
  public Collection getFieldNames() {
    if(fieldNames == null){
      Map flds = getAttributes();
      fieldNames = flds.keySet();
    }
    return fieldNames;
  }

  /** Returns a collection of all the field values
   * @return Collection of field values (Strings)
   */
  public Collection getFieldValues() {
    Collection values = new Vector();
    if (null==attributes) return values;
    Iterator p=fieldNames.iterator();
    while (p.hasNext())
      values.add(attributes.get((String)p.next()));
    return values;
  }

  public Map getAttributes() {
    if (null==attributes) {
      attributes = new HashMap();
      fieldNames = new Vector();
    }
    return attributes;
  }

  public Collection getSubComponents() { return subComponents; } //for lazyloading, also keep collection of childrenOrigins, expand childrenOrigins as needed
  public void setSubcomponents(Collection c) { subComponents=c; }
  public Collection getFamilyInstances() { return familyInstances; } //for lazyloading, also keep collection of childrenOrigins, expand childrenOrigins as needed
  public void setFamilyInstances(Collection c) { familyInstances=c; }
  public Collection getBinaries() { return binaries; }
  public void setBinaries(Collection c) { binaries=c; }
  public Collection getLinks() { return links; }
  public void setLinks(Collection c) { links=c; }

  public void addBinary(MetadataBinary binary) { if(null==binaries) binaries=new Vector(); binaries.add(binary); }
  public void addLink(Metadata data) { if(null==links) links=new Vector(); links.add(data); }
  public void addSubComponent(MetadataSubComponent sub) {
    {} //System.out.println(sub.getName());
    if(null==subComponents) {
      subComponents=new Vector();
    }
    if (!hasAncestorNamed(sub.getName())) {
      sub.setAncestor(this);
      subComponents.add(sub);
    }
    else {}{} //System.out.println("Adding subComponent creates a cycle:" + sub);
  }
  public void addFamilyInstance(MetadataFamilyInstance instance) { if(null==familyInstances) familyInstances=new Vector(); familyInstances.add(instance); }

  public boolean isLater(Metadata data) {
    if (null==data) return true;
    return getOrigin().isLater(data.getOrigin());
  }
  public boolean isEarlier(Metadata data) {
    if (null==data) return false;
    return getOrigin().isEarlier(data.getOrigin());
  }
  public boolean hasSameOrigin(Metadata data) {
    if (null==data) return false;
    return getOrigin().isTheSameAs(data.getOrigin());
  }

  public String getXMLTagName() { return XML_TAG_NAME; }

  /** Returns the metadata as an XML string
   * @return metadata represented in an XML string
   */
  public String toString(){
    try {
      StringBuffer xml = new StringBuffer();
      write(xml, getXMLTagName());
      return xml.toString();
    }
    catch (Exception e) { e.printStackTrace(); return e.getMessage();}
  }
  /** Writes this object to the StringBuffer in an XML format
   * @param xml buffer to write to
   * @throws Exception if there is an error writing to the buffer
   */
  public void write(StringBuffer xml) throws Exception { write(xml, fieldNames); }
  public void write(StringBuffer xml, String tagName) throws Exception { write(xml, tagName, fieldNames); }

  /** Writes the specified set of fields to the string buffer.
   * @param xml buffer to write to
   * @param metadataFields collection of the fields to write out. If the field does not exist, an empty
   * string is written as the value.
   * @throws Exception if there is an error writing to the buffer.
   */
  public void write(StringBuffer xml, Collection metadataFields) throws Exception {
    write(xml, XML_TAG_NAME, metadataFields);
  }
  public void write(StringBuffer xml, String tagName, Collection metadataFields) throws Exception {
    String value=null;
    xml.append(XML_START_TAG_START).append(tagName).append(SPACE);
    if(null!=origin) {
      value = getOrigin().toString();
      xml.append(ORIGIN).append(EQUALS).append(QUOTE);
      XMLString.writeValue(value, xml);
      xml.append(QUOTE).append(SPACE);
    }
    if (null!=name) {
      xml.append(NAME).append(EQUALS).append(QUOTE);
      XMLString.writeValue(name, xml);
      xml.append(QUOTE);
    }
    if (null!=metadataFields && null!=attributes) {
      Iterator a = metadataFields.iterator();
      String fieldName=null;
      value=null;
      while(a.hasNext()) {
          fieldName=(String)a.next();
          value = (String)attributes.get(fieldName);
          if (null==value) value="";
          xml.append(SPACE).append(XMLString.asXMLName(fieldName)).append(EQUALS).append(QUOTE);
          XMLString.writeValue(value, xml);
          xml.append(QUOTE);
      }
    }
    if (null==subComponents && null==familyInstances && null==history && null==links && null==binaries && null==branchTree)
      xml.append(XML_UNARY_START_TAG_END).append(NEW_LINE);
    else {
      xml.append(XML_BINARY_START_TAG_END).append(NEW_LINE);
      writeCollection(xml, subComponents, "sub-components", metadataFields);
      writeCollection(xml, familyInstances, "instances", metadataFields);
      writeCollection(xml, history, "history", metadataFields);
      writeCollection(xml, links, "links", metadataFields);
      writeCollection(xml, binaries, "binaries", metadataFields);
      writeCollection(xml, branchTree, "branches", metadataFields);
      xml.append(XML_BINARY_END_TAG_START).append(tagName).append(XML_BINARY_END_TAG_END).append(NEW_LINE);
    }
  }
  public boolean hasAncestorNamed(String name) {
    if (null==name || "".equals(name.trim())) return false;
    if (name.equalsIgnoreCase(getName())) return true;
    if (null==ancestor) return false;
    return ancestor.hasAncestorNamed(name);
  }
  public boolean hasSubComponents() { return (0!=countSubComponents(subComponents)); }
  public boolean hasSubComponent(String name) {
    if (!hasSubComponents()) return false;
    Iterator i = subComponents.iterator();
    Metadata sub = null;
    String subName=null;
    while(i.hasNext()) {
      sub= (MetadataSubComponent)i.next();
      subName = sub.getName();
      if (subName.equalsIgnoreCase(name)) return true;
    }
    return false;
  }
  private int countSubComponents(Collection c) {
    if (null==c) return 0;
    int count=0;
    Iterator i = c.iterator();
    while (i.hasNext()) count += ((MetadataSubComponent)i.next()).getQuantity();
    return count;
  }

  public boolean hasFamilyInstances() { return (0!=countFamilyInstances(familyInstances)); }
  private int countFamilyInstances(Collection c) {
    if (null==c) return 0;
    return c.size();
  }

  private void writeCollection(StringBuffer xml, Collection c, String tagName, Collection metadataFields) throws Exception {
    if (null==c) return; //{ xml.append("<"+tagname+" count=\"0\"/>"+NEW_LINE); return; }
    int size = c.size();
    if (tagName.equals("sub-components")) size = countSubComponents(c);
    
    xml.append(SPACE).append(XML_START_TAG_START).append(tagName).append(SPACE).append(COUNT).append(EQUALS).append(QUOTE).append(size).append(QUOTE).append(XML_BINARY_END_TAG_END).append(NEW_LINE);
    Iterator i = c.iterator();
    Metadata data;
    while (i.hasNext()) {
      xml.append("  ");
      data = (Metadata)i.next();
      data.write(xml,metadataFields);
    }
    xml.append(SPACE).append(XML_BINARY_END_TAG_START).append(tagName).append(XML_BINARY_END_TAG_END).append(NEW_LINE);
  }


  public Metadata getAncestor() { return ancestor; }
  public void setAncestor(Metadata data) { ancestor=data; }

  private Origin origin=null;
  private String name=null;
  private Map attributes = null;
  private Collection fieldNames = null;
  private Metadata ancestor=null;
  private Collection subComponents = null;
  private Map componentHash = new HashMap();
  private Collection familyInstances = null;
  private TreeSet branchTree=null;
  private TreeSet history = null;
  private Collection binaries=null;
  private Collection links=null;

  private static String EMPTY_STRING ="";
  private static String XML_TAG_NAME="metadata"; //++configure in properties file
  private static String XML_START_TAG_START ="<";
  private static String XML_UNARY_START_TAG_END = "/>";
  private static String XML_BINARY_START_TAG_END =">";
  private static String XML_BINARY_END_TAG_START = "</";
  private static String XML_BINARY_END_TAG_END = ">";

  private static String EQUALS = "=";
  private static String QUOTE = "\"";
  private static String SPACE = " ";
  private static String NEW_LINE = System.getProperty("line.separator");
//  private static String NAME="name";
}