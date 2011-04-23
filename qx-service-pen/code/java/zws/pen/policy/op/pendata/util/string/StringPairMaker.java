/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Aug 18, 2007 4:59:04 PM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.util.string;

import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.util.StringPair;

/*
<property attribute="" value="" />

<property attribute="" >
  <string-maker string="some dynamic string maker value"/>
</property>

<property>
  <logical-name>
    <string-maker string="some dynamic string maker attribute name"/>
  </logical-name>    
  <string-maker string="some dynamic string maker value"/>
</property>

*/

public class StringPairMaker extends PENDataOpBase {

  public void execute() throws Exception {
    String s0 = getString0();
    String s1= getString1();
    
    if (null==s0) s0 = lookupLogicalName();
    if (null==s1) s1="";
    s1 += concatenateDoOps();
        
    StringPair pair = new StringPair(s0, s1);
    setResult(pair);
  }

  public String getString0() { return string0; }
  public void setString0(String s) { string0=s; }
  public String getString1() { return string1; }
  public void setString1(String s) { string1 = s; }
  
  private String string0=null;
  private String string1=null; 
}
