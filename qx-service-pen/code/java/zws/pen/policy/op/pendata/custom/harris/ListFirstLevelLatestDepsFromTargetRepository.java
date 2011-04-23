/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Jul 15, 2008 9:28:53 AM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.custom.harris;

import zws.data.Metadata;
import zws.data.MetadataSubComponentBase;
import zws.origin.Origin;
import zws.pen.policy.op.pendata.util.string.StringCollectionResult;
import zws.repository.Repository;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.source.RepositoryStructureSource;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;


public class ListFirstLevelLatestDepsFromTargetRepository extends StringCollectionResult {

  private static final long serialVersionUID = -7718526223801650543L;

  public void execute() throws Exception {
    Collection latestTargetFirstLevelDepNames = new Vector();

    Metadata tgtMetadata = null;
    if (null != (tgtMetadata = findItemInTarget())) {
      Collection latestTargetSubComps = getTargetSubComponentNames(tgtMetadata.getOrigin()); 
      
      Iterator tgt = latestTargetSubComps.iterator();
      String subcompName = null;
      while (tgt.hasNext()) {
        subcompName = ((MetadataSubComponentBase)tgt.next()).getComponent().getName();
        latestTargetFirstLevelDepNames.add(subcompName);
      }
    }

    setStringCollectionResult(latestTargetFirstLevelDepNames);
  } 

  
  private Metadata findItemInTarget() throws Exception {
    String itemTxName = this.getPenData().lookupTxMetaData(getCurrentItem()).getName();
    Repository targetRepository = getPenPolicy().getTargetRepository();
    RepositoryMetadataSource metadataSource = targetRepository.materializeMetadataSource();
    return metadataSource.findLatest(getQxCtx(), itemTxName, getAuthentication());
  }
  
  /*
   * This method will only return items if and only if the target element is RELEASED.
   * IF the item is on a pending ECO, the agile SDK will not return the elements
   * on its BOM
   */  
  private Collection getTargetSubComponentNames(Origin o) throws Exception {   
    Collection latestFirstLevelSubcomps = null;
    Repository targetRepository = getPenPolicy().getTargetRepository();
    RepositoryStructureSource BOMSource = targetRepository.materializeStructureSource();
    latestFirstLevelSubcomps = BOMSource.reportFirstLevelLatestDependencies(getQxCtx(), o, getAuthentication());
    if (null == latestFirstLevelSubcomps)  {latestFirstLevelSubcomps = new Vector();}
    
    return latestFirstLevelSubcomps;
  }
  
  
  public Collection getStringCollection() { return value; }
  public void setStringCollection(Collection c) { value = c; }

  private Collection value = null;

}
