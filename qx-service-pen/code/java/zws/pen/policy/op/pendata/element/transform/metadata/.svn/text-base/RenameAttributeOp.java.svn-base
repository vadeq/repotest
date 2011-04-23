package zws.pen.policy.op.pendata.element.transform.metadata;

/*
 * DesignState - Design Compression Technology @author: ptoleti @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM Copywrite (c) 2007 Zero Wait-State Inc.
 * All rights reserved
 */

import zws.data.Metadata;
import zws.recorder.util.RecorderUtil;

public class RenameAttributeOp extends AttributeSetterOPBase {

  public void transformSourceMetadata(Metadata sourceData, Metadata txData) throws Exception {
    String attValue = sourceData.get(getSourceAttributeName());
    if(null == attValue && null != defaultValue) {
      attValue = defaultValue;
    }
    setAttribute(txData, getXferAttributeName(), attValue);
    RecorderUtil.logActivity(getQxCtx(), "rename attribute" ,  "old:" +getSourceAttributeName() + "new:"+ getXferAttributeName());
  }

  public String getSourceAttributeName() {    return sourceAttributeName;  }
  public void setSourceAttributeName(String name) {    sourceAttributeName = name;  }
  public String getXferAttributeName() {    return xferAttributeName;  }
  public void setXferAttributeName(String name) {    xferAttributeName= name;  }
  public String getDefaultValue() {return defaultValue;}
  public void setDefaultValue(String s) {defaultValue = s;}

  private String sourceAttributeName = null;
  private String xferAttributeName = null;
  private String defaultValue = null;
}
