/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.condition.util;

import zws.data.Metadata;
import zws.origin.Origin;
import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;
import zws.util.FileNameUtil;

import java.util.Collection;

public class ItemIsDesignModel extends ConditionOPBase {

  public Boolean evaluateCondition() throws Exception {
    boolean status = false;
    Metadata source = lookupSrcMetadata(getCurrentItem());
    if (null != source) {
      String name = source.getName();
      String fileType = FileNameUtil.getFileNameExtension(name);
      if ("asm".equalsIgnoreCase(fileType)) status = true;
      if ("prt".equalsIgnoreCase(fileType)) status = true;
    }    
    return new Boolean(status);
  }
}
