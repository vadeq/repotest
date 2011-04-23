package zws.exception; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Nov 7, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

public class AgileException extends Exception {
  public AgileException(String code, String msg) {
    super(msg);
    errorCode=code;
  }
  
  public String toString() {
    return "[" + errorCode +  "] " + getMessage();
  }
  
  private String errorCode=null;
}
