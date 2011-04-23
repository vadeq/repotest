package zws.pen.policy.op.pendata.element.transform.metadata;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.data.Metadata;
import zws.exception.zwsException;
import zws.recorder.util.RecorderUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public abstract class AttributeSetterOPBase extends TransformMetadataOpBase {

  private static final String ELIPSES = "...";
  
  protected void setAttribute(Metadata data, String attName, String attValue) throws Exception {
    if (null== attName || "".equals(attName.trim())) throw new Exception ("No Attribute name specified!");
    if(getMaxLength() > 0) {
      if (attValue.length()>getMaxLength()) {
        attValue = attValue.substring(0, getMaxLength()-ELIPSES.length()) + ELIPSES;
      }
    }
    // check for valid chars
    if(null != getRegularExpression()) {
      Pattern pattern = Pattern.compile(getRegularExpression());
      Matcher matcher = pattern.matcher(attValue);
      if(!matcher.matches()){
        throw new zwsException("Invalid character found in the attribute value");
      }
    }
    if (null!= attValue && !"".equals(attValue.trim()))
      data.set(attName, attValue);
    if(null != data.getName())
      RecorderUtil.logActivity(getQxCtx(), "set attribute ",  attName + "="+ attValue + "(" +  data.getName() + ")");
    else
      RecorderUtil.logActivity(getQxCtx(), "set attribute ",  attName + "="+ attValue);
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
  public void setMinLength(int l) { minLength = l;}

  public String getInvalidCharacters() { return invalidCharacters;  }
  public void setInvalidCharacters(String s) { invalidCharacters = s; }
  public String getValidCharacters() { return validCharacters;  }
  public void setValidCharacters(String s) {  validCharacters = s;  }

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
