/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.condition.comparison;

public class StringEndsWith extends CompareStringsOpBase {

  public Boolean compare(String lValue, String rValue, boolean ignoreCase) throws Exception{
     boolean result = false;
     
     if(null == lValue && null == rValue) { result = true;}

     else if(null !=lValue) {
       if(null==rValue) result = false;
       else if (lValue.length() < rValue.length()) result = false;
       else {
         String value = lValue;
         String endsWith = rValue;
         if (ignoreCase) {
           value = value.toUpperCase();
           endsWith = endsWith.toUpperCase();
         }
         result = lValue.endsWith(rValue);
       }
     }
     return new Boolean(result);
  }
  
  public String getSuffix() { return getValue(); }
  public void setSuffix(String s) { setValue(s); }
}

