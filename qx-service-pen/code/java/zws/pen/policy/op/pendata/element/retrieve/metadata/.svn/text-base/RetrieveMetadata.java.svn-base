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
import zws.repository.source.RepositoryMetadataSource;
import zws.security.Authentication;
import java.util.Collection;
import java.util.Vector;

/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public class RetrieveMetadata extends RetrieveMetadataOpBase {

  //returns collection of metatdate
  public Collection retrieveData(Repository sourceRepository, Origin sourceOrigin, Authentication id) throws Exception {
    RecorderUtil.logActivity(getQxCtx(), sourceOrigin.getName(), "Retrieve Metadata");
    Collection c = new Vector();
    RepositoryMetadataSource metadataSource = sourceRepository.materializeMetadataSource();
    Metadata sourceData = metadataSource.findLatest(getQxCtx(), sourceOrigin.getName(), this.getAuthentication());
    c.add(sourceData);
    return c;
  }
}
