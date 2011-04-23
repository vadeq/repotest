/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Oct 31, 2007 4:26:08 PM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.declaration;

import zws.util.Named;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sequence implements Named {
  
  public String getName() { return getSequenceName(); }
  public String getSequenceName() { return sequenceName; }
  public void setSequenceName(String s) { sequenceName=s; }
  
  public void setValues(String s) {
    if (null==s) return;
    StringTokenizer tokens = new StringTokenizer(s, getDelimiter());
    String value;
    while(tokens.hasMoreTokens()) {
      value = tokens.nextToken();
      values.add(value.trim());
    }
  }

  public int indexOf(String value) {
    int idx = -1;
    if (null==value) return -1;

    String v;
    for (int i = 0; idx<0 && i < values.size(); i++) {
      v = (String)values.get(i);
      if (value.equals(v)) idx= i;
    }
    return idx;
  }
  
  public String getDelimiter() { return delimiter; }
  public void setDelimiter(String s) { delimiter=s; }

  private String delimiter = ",";
  private String sequenceName;
  private ArrayList values = new ArrayList();
}
