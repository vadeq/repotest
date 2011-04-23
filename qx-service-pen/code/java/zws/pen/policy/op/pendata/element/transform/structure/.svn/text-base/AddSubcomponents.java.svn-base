/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved*/
package zws.pen.policy.op.pendata.element.transform.structure;

import zws.data.Metadata;
import zws.recorder.util.RecorderUtil;
import zws.service.pen.TxDataElement;

import java.util.Collection;
import java.util.Iterator;

public class AddSubcomponents extends TransformStructureOpBase{

  public void transformStructure(Metadata sourceData, TxDataElement txDataElement) throws Exception {
    if (null==getOps()) return;
    Collection subcomponents= doOps();
    if (null==subcomponents) return;
    Iterator i = subcomponents.iterator();
    Object o;
    Iterator x;
    String kid;
    while (i.hasNext()) {
      o = i.next();
      if (o instanceof Collection) {
        x = ((Collection)o).iterator();
        while (x.hasNext()) {
          kid = (String)x.next();
          addSubcomponent(txDataElement, kid);
          RecorderUtil.logActivity(getQxCtx(), "add sub-component",  txDataElement.getTxData() + "=" + kid);
        }
      }
      else if (null!=o) {
        kid = (String)o;
        addSubcomponent(txDataElement, kid);
        RecorderUtil.logActivity(getQxCtx(), "add sub-component",  txDataElement.getTxData() + "=" + kid);        
      }
    }
    setResult(subcomponents);
  }

  private void addSubcomponent(TxDataElement txDataElement, String kid) throws Exception {
    if(!txDataElement.containsSubcomponent(kid)) txDataElement.addSubcomponent(kid, 1, null);
  }
    
}


