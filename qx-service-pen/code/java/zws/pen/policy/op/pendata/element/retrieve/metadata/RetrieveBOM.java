package zws.pen.policy.op.pendata.element.retrieve.metadata;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.data.Metadata;
import zws.origin.Origin;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.repository.source.RepositoryStructureSource;
import zws.security.Authentication;
import java.util.Collection;
import java.util.Vector;

/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public class RetrieveBOM extends RetrieveMetadataOpBase {

  //returns collection of metatdate
  public Collection retrieveData(Repository sourceRepository, Origin sourceOrigin, Authentication id) throws Exception {
    RecorderUtil.logActivity(getQxCtx(), "Retrieve BOM",  sourceOrigin.getName());
    Collection c = new Vector();
    RepositoryStructureSource bomSource = sourceRepository.materializeStructureSource();
    Metadata bill = bomSource.reportBOM(getQxCtx(), sourceOrigin, this.getAuthentication());
    if (null==bill) return c;
    c.add(bill);
    return c;
  }
}
