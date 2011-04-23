/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.condition.util;

import zws.data.Metadata;
import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;
import zws.repository.Repository;
import zws.repository.source.RepositoryMetadataSource;

public class ItemIsInTarget extends ConditionOPBase {

  public Boolean evaluateCondition() throws Exception {
    boolean r = true;
    Repository targetRepository = getPenPolicy().getTargetRepository();
    RepositoryMetadataSource metadataSource = targetRepository.materializeMetadataSource();
    Metadata targetData = metadataSource.findLatest(getQxCtx(), getCurrentItem(), getAuthentication());
    if (null==targetData) r = false;
    return new Boolean(r);
  }
 }
