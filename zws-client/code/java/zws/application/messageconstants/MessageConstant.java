/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Mar 7, 2008 8:10:35 AM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.application.messageconstants;


import java.util.Iterator;
import java.util.Map;


public class MessageConstant {
  
  private static String TEMPLATE_PARAM_NAME_PREFIX = "$"; 
  private static String UNEXPECTED_PARAMS_MSG_PREFIX = "WARNING: The following parameters were not expected for message id";

  
  public MessageConstant() {
    super();
    id = null;
    message = null;
    description = null;
  }  
  
  public MessageConstant(String key, String msg, String desc) {
    super();
    id = key.trim();
    message = msg;
    description = desc;
  }

   
  public String resolve() throws Exception { return getMessage(); }

  public String resolve(Map msgParams) throws Exception {    
    //for each incoming actual parameter name, replace all occurances of
    //the actual parameter name preceeded by '$' with the value
    //corresponding to the actual parameter name   
    
    String actualParamValue = null;  
    String actualParamName = null;

    StringBuffer unexpectedParameters = new StringBuffer();
    String unResolvedMsg = getMessage();
    Iterator iter = msgParams.keySet().iterator();
    String resolvedMsg=unResolvedMsg;
    while (iter.hasNext()) {
      actualParamName = (String)iter.next();
      actualParamValue = (String)msgParams.get(actualParamName);
      
      if (-1 == getMessage().indexOf(TEMPLATE_PARAM_NAME_PREFIX+actualParamName)) 
        unexpectedParameters.append(actualParamName).append(" ");
      
      //template parameters begin with '$', so escape with "\\"
      unResolvedMsg = resolveParam(unResolvedMsg, ("\\"+TEMPLATE_PARAM_NAME_PREFIX+actualParamName), actualParamValue);
    }
    resolvedMsg = unResolvedMsg;
    
    if (unexpectedParameters.toString().length() != 0)
      resolvedMsg = noteUnexpectedParams(resolvedMsg, unexpectedParameters.toString());
      
    return resolvedMsg;
  }
  
  /*
   * resolve one parameter
   */
  private String resolveParam(String msg, String paramName, String paramValue) {
    String preparedValue = prepareGivenValue(paramValue);
    String resolvedStr = msg.replaceAll(paramName, preparedValue);
    return resolvedStr;
    
  }
  
  
  private String noteUnexpectedParams(String originalMsg, String note) {
    StringBuffer updatedMsg = new StringBuffer(originalMsg);
    updatedMsg.append(" [");
    updatedMsg.append(UNEXPECTED_PARAMS_MSG_PREFIX).append(" ").append(getId()).append(": ");
    updatedMsg.append(note);
    updatedMsg.append("]");
    
    return updatedMsg.toString();
  }
  
  
  private static boolean charIsSpecialCharacter(char c) {
    if ('\\'==c) return true;
    if ('$' ==c) return true;
    return false;
  }
  
  private static String prepareGivenValue(String s) {
    StringBuffer value=new StringBuffer();
    if (null==s || ""==s.trim()) return "";
    
    //incoming string does not need to be prepared if it does not contain special characters
    if (!(s.contains("$") || s.contains("\\"))) return s;
      
    char c;
    for(int idx=0; idx< s.length(); idx++) {
      c = s.charAt(idx);
      if (charIsSpecialCharacter(c)) {
        value.append("\\");
      }
      value.append(c);
    }
    return value.toString();
  }
  
  
  public String getId() {return id;}
  public void setId(String s) {id = s;}
  public String getMessage() {return message;}
  public void setMessage(String msg) {message = msg;}
  public String getDescription() {return description;}
  public void setDescription(String desc) {description = desc;}
  
  private String id=null;
  private String message=null;
  private String description=null;
}
