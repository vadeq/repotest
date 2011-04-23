package zws.pen.policy.op.pendata.element.retrieve.metadata;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.data.Metadata;
import zws.data.MetadataSubComponentBase;
import zws.origin.Origin;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.source.RepositoryStructureSource;
import zws.security.Authentication;
//impoer zws.util.Logwriter;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public class RetrieveFirstLevelLatestDependencies extends RetrieveMetadataOpBase {

  //returns collection of metatdate
  public Collection retrieveData(Repository sourceRepository, Origin sourceOrigin, Authentication id) throws Exception {
    RecorderUtil.logActivity(getQxCtx(), sourceOrigin.getName(), "Retrieve first level latest Dependencies");
    Collection c = new Vector();
    RepositoryMetadataSource metadataSource = sourceRepository.materializeMetadataSource();
    Metadata sourceData = metadataSource.findLatest(getQxCtx(), sourceOrigin.getName(), this.getAuthentication());
 
    RepositoryStructureSource bomSource = sourceRepository.materializeStructureSource();
    Collection deps = bomSource.reportFirstLevelLatestDependencies(getQxCtx(), sourceOrigin, getAuthentication());
    if (null==deps) return c;
    Iterator i = deps.iterator();
    Metadata dep;
    while (i.hasNext()) {
      dep = (Metadata)i.next();
      MetadataSubComponentBase sub = new MetadataSubComponentBase(dep);
      sourceData.addSubComponent(sub);
    }
    c.add(sourceData);    
    return c;
  }
}
