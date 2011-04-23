/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.condition.util;

import zws.data.Metadata;
import zws.exception.zwsException;
import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompareSourceAttributeOp extends ConditionOPBase {

  public Boolean evaluateCondition() throws Exception{
    if(null == attributeName && null == attributeValue && minLength<0 && maxLength<0) {
      throw new zwsException("invalid usage for CompareSourceAttributeOp");
    }
    boolean result = true;
    Metadata sourceData = this.getPenData().lookupSrcMetaData(this.getCurrentItem());
    String srcAttributeValue = sourceData.get(this.getAttributeName());

    if(null == srcAttributeValue) srcAttributeValue ="";
    // check for valid chars
    if(null != getRegularExpression()) {
      Pattern pattern = Pattern.compile(getRegularExpression());
      Matcher matcher = pattern.matcher(srcAttributeValue);
      result = result && matcher.matches();
    }

    // if there is no value supplied to compare, skip compare logic
    if(null != attributeValue) {
      if(isIgnoreCase()) {
        result = result && srcAttributeValue.equalsIgnoreCase(attributeValue);
      } else {
        result = result && srcAttributeValue.equals(attributeValue);
      }
    }
    if(minLength >0) {
      result = result && (minLength <= srcAttributeValue.length());
    }
    if(maxLength >0) {
      result = result && (maxLength >= srcAttributeValue.length() );
    }
    return new Boolean(result);
  }
  public String getAttributeName() {return attributeName;}
  public void setAttributeName(String name) { attributeName = name;}
  public String getAttributeValue() {return attributeValue;}
  public void setAttributeValue(String value) {attributeValue = value;}

  public boolean isIgnoreCase() {return ignoreCase;}
  public void setIgnoreCase(boolean b) { ignoreCase = b;}
  public int getMaxLength() {return maxLength;}
  public void setMaxLength(int l) { maxLength = l;}
  public int getMinLength() {return minLength;}
  public void setMinLength(int l) {minLength = l;}
  public String getInvalidCharacters() {  return invalidCharacters;  }
  public void setInvalidCharacters(String s) {   invalidCharacters = s; }
  public String getValidCharacters() {   return validCharacters;  }
  public void setValidCharacters(String s) {   validCharacters = s;  }
  
  public String getRegularExpression() {
    regEx = null;
    if(null!=getValidCharacters() && !"".equals(getValidCharacters().trim())) regEx ="[" + escapeRegExChars(getValidCharacters()) + "]";
    if(null!=getInvalidCharacters() && !"".equals(getInvalidCharacters().trim())) regEx +="[^" + escapeRegExChars(getInvalidCharacters()) + "]";
    if (null!=regEx) regEx = regEx + "*";
    return regEx;
  }

  private String escapeRegExChars(String s) {
    return s; //+++todo escape any chars for regex  
  }

  private String attributeName = null;
  private String attributeValue = null;
  private boolean ignoreCase = true;
  private int minLength= -1;
  private int maxLength= -1;
  private String regEx = null;   // example [a-zA-Z0-9!@#$%^&*()_+-={}\\[\\]|\\\\][^;:?.<>~]*
  private String validCharacters= null;
  private String invalidCharacters = null;
}
