package zws.data.transformer.metadata; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.util.*;

import java.io.Serializable;
import java.util.*;

public class MetadataRemappingSpec extends TransformerBase implements Namespaced, Serializable {
  public void adapt(Metadata data) { sourceData = data; }

  public Object transform() {
    String fieldName=null, valueSpec=null;
    MetadataBase data = new MetadataBase();
    data.setOrigin(sourceData.getOrigin());
    Iterator i = fieldNameMapping.keySet().iterator();
    while (i.hasNext()){
      fieldName = (String)i.next();
      valueSpec = (String)fieldNameMapping.get(fieldName);
      if (null==valueSpec) valueSpec="";
      else{
        StringTokenizer tokens = new StringTokenizer(valueSpec, Names.DELIMITER);
        data.set(fieldName, parseValueSpec(tokens));
      }
    }
    return data;
  }
  
  public void add(KeyValue mapping) {
//    String key = mapping.getKey();
//    String value = (String)mapping.getValue();
    fieldNameMapping.put(mapping.getKey(), (String)mapping.getValue()); 
//    if (0>value.indexOf(Names.POPERTY_LITERAL_START) && 0>value.indexOf(Names.PROPERTY_REF_START))
//      fieldNameReverseMapping.put((String)mapping.getValue(), mapping.getKey()); 
  }

  public Map getMappings() { return fieldNameMapping; }
  public String getMapping(String metadataFieldName) { 
    String mappedField = (String)fieldNameMapping.get(metadataFieldName); 
    if (null==mappedField) return null;
//    if (0>mappedField.indexOf(Names.POPERTY_LITERAL_START) && 0>mappedField.indexOf(Names.PROPERTY_REF_START)) return null;
    return mappedField;
  }
  //public String getReverseMapping(String mappedFieldName) { return (String)fieldNameReverseMapping.get(mappedFieldName); }
  public String parseValueSpec(StringTokenizer tokens) {
    String value = "";
    String key=null, token=null, metadataValue=null;
    while (tokens.hasMoreTokens()){
      token=tokens.nextToken();
      key=token.trim();
      if(key.startsWith(Names.POPERTY_LITERAL_START) && key.endsWith(Names.POPERTY_LITERAL_END)) value += key.substring(1,key.length()-1);
      else if (key.startsWith(Names.PROPERTY_REF_START)&& key.endsWith(Names.PROPERTY_REF_END)){
        key = key.substring(1,key.length()-1).trim();
        value+= sourceData.get((String)fieldNameMapping.get(key));
      }
      else {
        metadataValue = sourceData.get(key);
        if (null==metadataValue) metadataValue="";
        value += metadataValue;
      }
    }
    return value;
  }
  
  //Interface Delegation
  public void adapt(Object o) { adapt((Metadata)o); }

  public String getName() { return name; }
  public void setName(String s) { name=s; }
  public String getDescription() { return description; }
  public void setDescription(String s) { description=s; }
  public Namespace getNamespace() { return namespace; }
  public void setNamespace(String s) { namespace = new Namespace(s); }
  
  private Metadata sourceData;
  private Map fieldNameMapping = new HashMap();
//  private Map fieldNameReverseMapping = new HashMap();
  private String name=null, description=null;
  private Namespace namespace=null;
}
