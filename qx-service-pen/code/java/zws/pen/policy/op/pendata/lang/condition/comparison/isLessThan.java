/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.condition.comparison;

public class isLessThan extends CompareStringsOpBase {

  public Boolean compare(String lValue, String rValue, boolean ignoreCase) throws Exception{
     boolean result = false;
 
     if(null == lValue && null == rValue) { result = true;}
     
     else if(null !=lValue) {
       if(null==rValue) result = false;
       else if (ignoreCase) result = (0 > lValue.toLowerCase().compareTo(rValue.toLowerCase()));
       else result = (0 > lValue.compareTo(rValue));;
     }
     return new Boolean(result);
  }
  
  public Boolean compare(Integer lValue, Integer rValue) throws Exception {
    boolean result = false;    
    if(null == lValue && null == rValue) { result = true;}
    else if(null !=lValue) {
      if(null==rValue) result = false;
      else result= lValue.intValue() < rValue.intValue();
    }
    return new Boolean(result);    
  }
}
