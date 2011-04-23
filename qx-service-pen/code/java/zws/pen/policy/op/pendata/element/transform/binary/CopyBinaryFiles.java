/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Nov 21, 2007 9:27:07 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.element.transform.binary;

import zws.service.pen.PENDataElement;
import zws.service.pen.SourceDataElement;
import zws.service.pen.TxDataElement;

import java.util.Collection;


public class CopyBinaryFiles extends TransformBinaryOpBase {


  /**
   * Move the binaries from the subcomponents into the parent
   */
  public void transformBinary(PENDataElement penElement) throws Exception {
    SourceDataElement srcDataElement = this.lookupSourceDataElement(getCurrentItem());
    TxDataElement txDataElement = this.lookupTxDataElement(getCurrentItem());
    Collection binaries = srcDataElement.getBinaryCollection();
    if(null== binaries || binaries.size() <1) return;
    txDataElement.getBinaryFiles().addAll(binaries);
    }
}
