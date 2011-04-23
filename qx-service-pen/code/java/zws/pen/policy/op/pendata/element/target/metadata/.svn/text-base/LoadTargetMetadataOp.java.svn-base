package zws.pen.policy.op.pendata.element.target.metadata;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.data.Metadata;
import zws.pen.policy.op.pendata.PENDataProcessor;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.repository.source.RepositoryMetadataSource;
//impoer zws.util.{}//Logwriter;


/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public class LoadTargetMetadataOp extends PENDataProcessor {

  public void process() throws Exception {
    String name = getCurrentItem();
    Repository targetRepository = getPenPolicy().getTargetRepository();
    RepositoryMetadataSource metadataSource = targetRepository.materializeMetadataSource();
    Metadata txData = lookupTxMetadata(name);
		Metadata targetData = metadataSource.findLatest(getQxCtx(), txData.getName(), getAuthentication());    
    {}//Logwriter.printOnConsole("loading target " + targetData);
    lookupTargetDataElement(name).setTargetData(targetData);
    if(null != targetData) RecorderUtil.logActivity(getQxCtx(), "Target data loaded ",  targetData.getName());

    //StatusElement statusElement = lookupStatusElement(name);
    /* target_origin should only be set when creating the item in the target..
    if (null != statusElement && null != targetData) {
      {}//Logwriter.printOnConsole("loading target origin " + targetData.getOrigin());
      statusElement.setItemStatus(DATA_TARGET_ORIGIN,targetData.getOrigin().toString());
    }
    */
   
  }
}
