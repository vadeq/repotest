 /* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.condition.comparison;

import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;

import java.util.HashMap;
import java.util.Map;

public class StringDoesNotContainInvalidChars extends ConditionOPBase {

  public Boolean evaluateCondition() throws Exception{
    String invalids = getInvalidCharacters();

    if (null==invalids || "".equals(invalids)) return new Boolean(true);

    String x = concatenateDoOps();
    //if (null==x) x = "";
     
    //x = x + concatenateDoOps();
    if (null==x || "".equals(x)) return new Boolean(true);
    
    Map invalidMap = new HashMap();
    for (int i=0; i < invalids.length(); i++) invalidMap.put(""+invalids.charAt(i), ""+invalids.charAt(i)); 
    
    boolean isOK= true;
    String c;
    
    for (int i=0; i<x.length() && isOK; i++) {
      c = "" + x.charAt(i);
      if (invalidMap.containsKey(c)) isOK=false;
    }
    return new Boolean(isOK);
  }

  public String getInvalidCharacters() { return invalidCharacters; }
  public void setInvalidCharacters(String s) { invalidCharacters = s; }

  public String getString() { return value; }
  public void setString(String s) { value = s; }

  
  private String invalidCharacters = null;
  private String value = null;
}

