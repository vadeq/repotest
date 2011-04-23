 /* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.condition.comparison;

import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;

import java.util.HashMap;
import java.util.Map;

public class StringContainsOnlyValidChars extends ConditionOPBase {

  public Boolean evaluateCondition() throws Exception{
    String valids =getValidCharacters();
    if (null==valids || "".equals(valids)) return new Boolean(true);

    String x = getString();
    if (null==x) x = "";
     
    x = x + concatenateDoOps();
    if (null==x || "".equals(x)) return new Boolean(true);
    
    Map validMap = new HashMap();
    for (int i=0; i < valids.length(); i++) validMap.put(""+valids.charAt(i), ""+valids.charAt(i)); 
    
    boolean isValid = true;
    int i=0;
    String c;
    while (i < x.length() && isValid) {
      c = "" + x.charAt(i);
      if (!validMap.containsKey(c)) isValid=false;
    }
    return new Boolean(isValid);
  }

  public String getValidCharacters() { return validCharacters; }
  public void setValidCharacters(String s) { validCharacters = s; }

  public String getString() { return value; }
  public void setString(String s) { value = s; }

  
  private String validCharacters = null;
  private String value = null;
}

