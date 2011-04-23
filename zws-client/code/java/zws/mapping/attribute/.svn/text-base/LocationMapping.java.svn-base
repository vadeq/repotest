package zws.mapping.attribute;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 4, 2004, 1:15 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.InvalidAttributeMapping;
import zws.util.StringPair;

import java.util.*;

// will replace an absolute path if found.
// will substitute a new root location if found
// if multiple root locations are found, will use the longest root location.
public class LocationMapping extends MetadataAttributeMappingBase {

  protected boolean isMapped() {
    {} //System.out.println("source:");
    {} //System.out.println(getSource());
    String location = getSource().get(getFieldName());
    if (null==location) return false;
    if (isRegisteredAsAbsolutePath(location)) return true;
    if (isRegisteredUnderRootDirectory(location)) return true;
    return false;
  }
  
  protected void mapSourceToTarget() throws InvalidAttributeMapping {
    String sourceLocation = getSource().get(getFieldName());
    String targetLocation = (String)absoluteLocationMappings.get(sourceLocation);
    if (null!=targetLocation) getTarget().set(getNewFieldName(), targetLocation);
    else mapAsSubDirectory(sourceLocation);
  }
  
  private void mapAsSubDirectory(String sourceLocation) throws InvalidAttributeMapping { //Matches against the longest root found
    String sourceRootLocation=null;
    String targetRootLocation=null;
    String relativeLocation=null;
    String targetLocation=null;
    
    Iterator i = rootLocationMappings.keySet().iterator();
    String root=null;
    if (!i.hasNext()) {
      if (!copyLocationIfMappingIsNotDefined) throw new InvalidAttributeMapping("No mapping specified for location: "+ getFieldName()+"="+sourceLocation);
      getTarget().set(getNewFieldName(), sourceLocation);
      return;
    }
    do {
      root = (String)i.next();
      if (sourceLocation.toLowerCase().startsWith(root.toLowerCase())) {
        if (null==sourceRootLocation) sourceRootLocation=root;
        else if (root.length()>sourceRootLocation.length()) sourceRootLocation = root;
      }
    }
    while (i.hasNext());
    if (null==sourceRootLocation) {
      if (!copyLocationIfMappingIsNotDefined) throw new InvalidAttributeMapping("No mapping specified for location: "+ getFieldName()+"="+sourceLocation);
      getTarget().set(getNewFieldName(), sourceLocation);
      return;
    }
    targetRootLocation = (String)rootLocationMappings.get(sourceRootLocation);
    relativeLocation = sourceLocation.substring(sourceRootLocation.length());
    relativeLocation = convertPathSeparators(relativeLocation);
    targetLocation = targetRootLocation + relativeLocation;
    getTarget().set(getNewFieldName(), targetLocation);
  }
  
  private String convertPathSeparators(String path) { return path.replace(sourcePathSeparator.charAt(0), targetPathSeparator.charAt(0)); }
  
  private boolean isRegisteredUnderRootDirectory(String location) {
    Iterator i = rootLocationMappings.keySet().iterator();
    String rootLocation;
    while (i.hasNext()) {
        rootLocation = (String)i.next();
        {} //System.out.println("-------------------");
        {} //System.out.println( location + " starts with "  + rootLocation +" = " + location.toLowerCase().startsWith(rootLocation.toLowerCase()));
        if (location.toLowerCase().startsWith(rootLocation.toLowerCase())) return true;
    }
    return false;
  }
  
  private boolean isRegisteredAsAbsolutePath(String location) {
    Iterator i = absoluteLocationMappings.keySet().iterator();
    while (i.hasNext()) if (location.equalsIgnoreCase(((String)i.next()))) return true;
    return false;
  }
  
  
  public void addRootLocationMapping(String fromValue, String toValue) { rootLocationMappings.put(fromValue.trim(), toValue.trim()); }
  public void addRootLocationMapping(StringPair pair) { rootLocationMappings.put(pair.getString0().trim(), pair.getString1().trim()); }

  public void addAbsoluteLocationMapping(String fromValue, String toValue) { absoluteLocationMappings.put(fromValue.trim(), toValue.trim()); }
  public void addAbsoluteLocationMapping(StringPair pair) { absoluteLocationMappings.put(pair.getString0().trim(), pair.getString1().trim()); }

  public String getSourcePathSeparator() { return sourcePathSeparator; }
  public void setSourcePathSeparator(String s) { sourcePathSeparator=s; }
  public String getTargetPathSeparator() { return targetPathSeparator; }
  public void setTargetPathSeparator(String s) { targetPathSeparator=s; }
  
  public boolean getCopyLocationIfMappingIsNotDefined() { return copyLocationIfMappingIsNotDefined; }
  public void setCopyLocationIfMappingIsNotDefined(boolean b) { copyLocationIfMappingIsNotDefined=b; }
  
  private String sourcePathSeparator = "/";
  private String targetPathSeparator = "/";
  
  private Map rootLocationMappings = new HashMap();
  private Map absoluteLocationMappings = new HashMap();
  
 private boolean copyLocationIfMappingIsNotDefined=false;
}
