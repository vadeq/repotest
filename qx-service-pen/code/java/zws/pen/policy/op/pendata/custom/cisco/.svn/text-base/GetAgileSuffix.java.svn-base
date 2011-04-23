/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.custom.cisco;

import zws.data.Metadata;
import zws.pen.policy.op.pendata.util.string.StringMaker;

public class GetAgileSuffix extends StringMaker {

  protected String makeString(String s) {
    /*
     *  $1.prt => MEP_$1_P
        $1.gph => MEP_$1_U
        $1.asm => MEP_$1_A
        $1.drw => MEP_$1_D
        $1.frm => MEP_$1_F
     */
    String suffix = "";
    suffix = getSuffix(s);
    if(suffix.length() <1) {
      Metadata data = lookupSrcMetadata(getCurrentItem());
      String name = data.get(this.getAlternateAttribute());
      if(null != name) {
        suffix = this.getSuffix(name);
      }
    }
    return suffix;

  }

  private String getSuffix(String name) {
    if (null==name) return "";
    String x = "";
    if(name.toLowerCase().endsWith(".prt")) {
      x= "_P";
    } else if(name.toLowerCase().endsWith(".gph")) {
      x = "_U";
    }else if(name.toLowerCase().endsWith(".asm")) {
      x = "_A";
    }else if(name.toLowerCase().endsWith(".drw")) {
      x = "_D";
    }else if(name.toLowerCase().endsWith(".frm")) {
      x = "_F";
    }
    return x;
  }

  public String getAlternateAttribute() {
    return alternateAttribute;
  }

  public void setAlternateAttribute(String s) {
    alternateAttribute = s;
  }

  String alternateAttribute = null;
}
