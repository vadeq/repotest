/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.util.string;

public class TruncateString extends StringMaker {
  
  protected String makeString(String s) {
		String s2 = null;
		int idx1 = s.indexOf(getValue());
		if(-1 != idx1) {
			s2 = s.substring(0, idx1);			
		}
		return s2;
  }

  public String  getValue() { return value; }
  public void setValue(String  s) { value=s; }
  
  private String  value = null;
}
