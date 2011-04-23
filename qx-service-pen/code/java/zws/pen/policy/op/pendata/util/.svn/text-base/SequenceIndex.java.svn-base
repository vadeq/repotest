/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Oct 31, 2007 5:26:35 PM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.util;

import zws.pen.policy.PENPolicy;
import zws.pen.policy.declaration.Sequence;
import zws.pen.policy.op.pendata.PENDataOpBase;

public class SequenceIndex extends PENDataOpBase {
  
  public void execute() throws Exception {
    Sequence sequence = PENPolicy.lookupSequence(getSequenceName());
    if (null==sequence) {
      setResult(new Integer(-1));
      return;      
    }
    
    String v = materializeValue();
    
    int index = sequence.indexOf(v);
    setResult(new Integer(index));
    return;      
  }
  
  private String materializeValue() throws Exception {
    String v = getValue();
    if (null==v) v = "";
    v += concatenateDoOps();
    return v;
  }

  public String getValue() { return value; }
  public void setValue(String s)  { value =s; }
  
  public String getSequenceName() { return sequenceName; }
  public void setSequenceName(String s) { sequenceName =s; }

  private String value=null;
  private String sequenceName=null;
}
