package zws.exception; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Jul 15, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import java.util.*;

public class zwsException extends Exception {
  public zwsException() {};

  public zwsException(String type) { key=type; }
  public zwsException(String type, String message) { 
    super(message);
    key=type;
  }

  public String getType() { return key; }
  public void setType(String s) { key=s; }
  public void define(String param, String value) {
    if ("type".equalsIgnoreCase(param)) setType(value);
    else if ("key".equalsIgnoreCase(param)) setType(value);
    else parameters.put(param, value); 
  }

  public Map getParameters() { return parameters; }
    
  private String key=null;
  private Map parameters = new HashMap();
  
  private String domain = zws.Server.getDomainName();
  private int node = zws.Server.getNodeNumber();
  private String hostname = zws.Server.getHostName();
}
