package zws.data; 
import zws.util.Pair;

import java.util.*;

/*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Jan 5, 2006
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

public class IlinkMetadata extends MetadataBase {
  public String getBranch() { return get(BRANCH); }
  public String getRevision() { return get(REVISION); }
  public String getVersion() { return get(VERSION); }
  public String getRelease() { return get(RELEASE_LEVEL); }
  public String getFolder() { return get(LOCATION); }
  public String getCreatedOn() { return get(TIME_OF_CREATION); }
  public String getCreatedBy() { return get(CREATED_BY); }

  public Collection getUserAttributes() {
	  Pair p;
	  Collection atts = new Vector();
	  String key, value;
	  Iterator i = getAttributes().keySet().iterator();
	  while (i.hasNext()) {
	   key = (String)i.next();
	   if (isSystemAttribute(key)) continue;
	   value = get(key);
	   p = new Pair(key, value);
	   atts.add(p);
	  }
	  return atts;
	}
	
	private boolean isSystemAttribute(String key) {
	  if (key.equalsIgnoreCase(NAME)) return true;
	  if (key.equalsIgnoreCase(BRANCH)) return true;
	  if (key.equalsIgnoreCase(REVISION)) return true;
	  if (key.equalsIgnoreCase(VERSION)) return true;
	  if (key.equalsIgnoreCase(RELEASE_LEVEL)) return true;
	  if (key.equalsIgnoreCase(CREATED_BY)) return true;
	  if (key.equalsIgnoreCase(CREATED_ON)) return true;
	  if (key.equalsIgnoreCase(LOCATION)) return true;
	  return false;
	}
    
  
}
