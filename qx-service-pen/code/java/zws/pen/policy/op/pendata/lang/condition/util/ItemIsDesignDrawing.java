/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.condition.util;

import zws.data.Metadata;
import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;
import zws.util.FileNameUtil;

public class ItemIsDesignDrawing extends ConditionOPBase {

  public Boolean evaluateCondition() throws Exception {
    boolean status = false;
    Metadata source = lookupSrcMetadata(getCurrentItem());
    if (null != source) {
      String name = source.getName();
      String fileType = FileNameUtil.getFileNameExtension(name);
      if ("drw".equalsIgnoreCase(fileType)) status = true;
      if ("dwg".equalsIgnoreCase(fileType)) status = true;
    }    
    return new Boolean(status);
  }
}
