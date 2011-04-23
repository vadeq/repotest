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
import zws.repository.source.RepositoryStructureSource;
import zws.security.Authentication;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public class RetrieveBOMWithDependencies extends RetrieveMetadataOpBase {

  //returns collection of metatdate
  public Collection retrieveData(Repository sourceRepository, Origin sourceOrigin, Authentication id) throws Exception {
    RecorderUtil.logActivity(getQxCtx(), "Retrieve BOM with Dependencies",  sourceOrigin.getName());
    Collection c = new Vector();
    //RepositoryMetadataSource metadataSource = sourceRepository.materializeMetadataSource();
    //Metadata sourceData = metadataSource.findLatest(getQxCtx(), sourceOrigin.getName(), this.getAuthentication());
    RepositoryStructureSource bomSource = sourceRepository.materializeStructureSource();
    Metadata bill = bomSource.reportBOM(getQxCtx(), sourceOrigin, getAuthentication());
    populateDependencies(bomSource, bill);
    c.add(bill);
    return c;
  }
  //private void populateDependencies(RepositoryStructureSource bomSource, Metadata bill) throws Exception {
    private void populateDependencies(RepositoryStructureSource bomSource, Metadata bill) throws Exception {
      Collection deps = bomSource.reportDependencies(getQxCtx(), bill.getOrigin(), getAuthentication());
      if (null !=deps && !deps.isEmpty()) {
        Iterator i = deps.iterator();
        Metadata dep = null;
        MetadataSubComponentBase subDep = null;
        while (i.hasNext()) {
          dep = (Metadata)i.next();
          subDep = new MetadataSubComponentBase(dep);
          if(!bill.hasSubComponent(subDep.getName())) bill.addSubComponent(subDep);
        }
      }
      if(bill.hasSubComponents()) {
        Collection subs = bill.getSubComponents();
        if(null != subs && !subs.isEmpty()) {
          Iterator itr = subs.iterator();
          Metadata subData = null;
          while (itr.hasNext()) {
            subData = (Metadata)itr.next();
            populateDependencies(bomSource, subData);
          }
        }
      }
    }
}
