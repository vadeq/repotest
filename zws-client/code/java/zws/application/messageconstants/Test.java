/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Mar 11, 2008 12:49:26 PM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.application.messageconstants;

import java.util.HashMap;

public class Test {

  public static void main(String[] args) throws Exception {

    String msgTemplate = "[$name$part-number) This part of the message is the portion that users can replace. {$case-number}";

    String messageId = "test-msg";
    MessageConstant msgConsts = new MessageConstant(messageId, msgTemplate, "this is a test!");
    MessageConstants.add(msgConsts);    
    HashMap actualParams = new HashMap();
    MessageConstant msgConst = MessageConstants.lookup(msgConsts.getId());
    
    //GOOD case:
    System.out.println("GOOD Case:");    
    actualParams.put("name", "THIS-IS-NAME");
    actualParams.put("part-number", "THIS IS PART NUMBER");
    actualParams.put("case-number", "GOOD-TEST-CASE");
    String resolvedMsg = msgConst.resolve(actualParams);
    System.out.println("Resolved Message: " + resolvedMsg);
    System.out.println("test 1 passes");

    //GOOD case-B
    System.out.println("GOOD Case-B: - calling resolve on MessageConstants");
    resolvedMsg = MessageConstants.resolve(messageId, actualParams);
    System.out.println("Resolved Message: " + resolvedMsg);
    System.out.println("GOOD Case-B passes");
    
    //OK case 2: not enough parameters
    System.out.println("OK case 2: not enough parameters"); 
    actualParams = new HashMap();    
    actualParams.put("name", "THIS-IS-NAME");
    resolvedMsg = msgConst.resolve(actualParams);
    System.out.println("Resolved Message: " + resolvedMsg);
    System.out.println("test 2 passes");
 
    try {
    System.out.println("BAD Case:");    
    actualParams.put("name2", "THIS-IS-NAME");
    resolvedMsg = msgConst.resolve(actualParams);
    System.out.println("Resolved Message: " + resolvedMsg);
    System.out.println("test 3 fails");
    }
    catch(Exception expected) {
      System.out.println("test 3 passes - " +  expected.getMessage());
    }
  }

}
