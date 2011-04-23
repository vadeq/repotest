/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Mar 28, 2008 11:14:54 AM
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


public class KeepOnlyNewDocs extends StringCollectionResult {

  protected Collection makeStringCollection(Collection c) throws Exception {
    return c;
  }
  protected Collection makeOnEachStringCollection(Collection c) throws Exception {
    return c;
  }

  public void execute() throws Exception {
    Collection filteredSrcParentNames = new Vector();
    Collection srcParentNames = getStringCollection();
    if (null==srcParentNames || srcParentNames.isEmpty()) srcParentNames = new Vector();
    
    Collection c = doOps(); //doOps returns a Collection of Collections of names.
                            //In this case, should be a Collection of ONE collection of names
    Iterator i = c.iterator();
    Object o;
    Collection strColl;

    while(i.hasNext()) {
      o = i.next();
      if (null==o) continue;
      
      if (o instanceof Collection) {
        strColl=(Collection)o;
        strColl=makeOnEachStringCollection(strColl);
        srcParentNames.addAll(strColl);
      }
    }
    srcParentNames = makeStringCollection(srcParentNames);
    
    //get collection of target sub component names and filter based on that
    filteredSrcParentNames = srcParentNames; //if item is not in target, then filtered list is original incoming collection
    Metadata tgtMetadata = null;
    if (null != (tgtMetadata = findItemInTarget())) {
      Collection targetSubComps = getTargetSubComponentNames(tgtMetadata.getOrigin()); 
      
      Iterator tgt = targetSubComps.iterator();
      String targetDocName = null;
      Metadata subcompMetadata = null;
      while (tgt.hasNext()) {
        subcompMetadata = ((MetadataSubComponentBase)tgt.next()).getComponent();
        targetDocName = subcompMetadata.getName();
        String srcDrwName = makeSourceDrwName(targetDocName);
        if (filteredSrcParentNames.contains(srcDrwName.toLowerCase()))
          filteredSrcParentNames.remove(srcDrwName.toLowerCase());
        else if (filteredSrcParentNames.contains( srcDrwName.toUpperCase()))
          filteredSrcParentNames.remove(srcDrwName.toUpperCase());
      }
    }
    
    filteredSrcParentNames = makeStringCollection(filteredSrcParentNames);
    setStringCollectionResult(filteredSrcParentNames);
  } 

  private String makeTargetDocName(String srcDocName) {
    String targetName = null;
    String[] arr = srcDocName.split("\\.");
    if (null==arr[0]) targetName = srcDocName+"DOC";
    else targetName = arr[0]+"DOC";
    return targetName;
  }
  
  private String makeSourceDrwName(String targetDocName) {
    String srcDrwName = ""; 
    int idx = targetDocName.lastIndexOf("DOC");
    if (-1 != idx) srcDrwName = targetDocName.substring(0, idx)+".drw";
   
    return srcDrwName;
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
    Collection subcomps = null;
    Repository targetRepository = getPenPolicy().getTargetRepository();
    RepositoryStructureSource BOMSource = targetRepository.materializeStructureSource();
    subcomps = BOMSource.reportFirstLevelDependencies(getQxCtx(), o, getAuthentication());
    if (null == subcomps)  {subcomps = new Vector();}
    
    return subcomps;
  }
  
  
  public Collection getStringCollection() { return value; }
  public void setStringCollection(Collection c) { value = c; }

  private Collection value = null;

}
