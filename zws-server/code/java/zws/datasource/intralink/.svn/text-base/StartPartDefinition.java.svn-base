package zws.datasource.intralink; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Sep 3, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.util.MetadataUtil;
import zws.application.Names;
import java.util.*;



//    type, proe-parameters, start-part-grammar. newpartlocation

public class StartPartDefinition {
  public String getType() { return type; }
  public void setType(String s) { type=s; }
  
  public String getStartPartNameingConvention() { return startPartNamingConvention; }
  public void setStartPartNamingConvention(String s) { startPartNamingConvention=s; }
  
  public String getNewPartLocation() { return newPartLocation; }
  public void setNewPartLocation(String s) { newPartLocation=s; }
  public String getStartPartName(Metadata data) throws Exception {
   String startPart= MetadataUtil.parseNamingGrammar(data, startPartNamingConvention);
   return startPart;
  }
  public Map getProParameters(Metadata data) {
    Iterator i = proParameters.iterator();
    String parameter=null;
    String value=null;
    Map parameters = new HashMap();
    while (i.hasNext()) {
      parameter = i.next().toString();
      value = data.get(parameter.toLowerCase());
      parameters.put(parameter, value);
    }
    return parameters; 
  }
  public Collection getProParameters() { return proParameters; }
  public void setProParameters(String s) {
    StringTokenizer tokens = new StringTokenizer(s, Names.DELIMITER);
    if (tokens.hasMoreTokens()) {
      while(tokens.hasMoreTokens()) proParameters.add(tokens.nextToken().trim());
    }
    else proParameters.add(s.trim());
  }
  private String type=null;
  private String startPartNamingConvention= null;
  private String newPartLocation = null;
  private Collection proParameters = new Vector();
}
