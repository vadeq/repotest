package zws.pen.policy.op.pendata.element.publish;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/

import zws.data.Metadata;
import zws.origin.Origin;
import zws.pen.policy.op.pendata.PENDataProcessor;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.target.RepositoryMetadataTarget;
import zws.service.pen.SourceDataElement;
import zws.service.pen.StatusElement;
//import zws.util.{}//Logwriter;
import java.util.ArrayList;

public class CreateItems extends PENDataProcessor{
  public void process() throws Exception {
    {}//Logwriter.printOnConsole(this,"process","start");
    StatusElement statusElement = lookupStatusElement(getCurrentItem());
    boolean createStatus = TRUE.equalsIgnoreCase(statusElement.getItemStatus(ACTION_ITEM_NEEDS_TO_BE_CREATED));
    if(createStatus) {
      ArrayList binaryList = null;
      Repository targetRepository = getPenPolicy().getTargetRepository();
      RepositoryMetadataTarget target = targetRepository.materializeMetadataTarget();
      RepositoryMetadataSource source= targetRepository.materializeMetadataSource();
      Metadata transformedData = getPenData().lookupTxMetaData(this.getCurrentItem());
      Origin o = null;
      SourceDataElement srcDataElement = lookupSourceDataElement(getCurrentItem());
      binaryList = srcDataElement.getBinaryCollection();
      /*
      if(null != binaryList && binaryList.size()>0) { ///???need to put this in a separate op..
        File binary = (File)binaryList.get(0);
        {}//Logwriter.printOnConsole("attaching binary " + binary.getName());
        o = target.create(getQxCtx(), transformedData, binary, getAuthentication());
        try { 
          targetData = source.find(getQxCtx(), o, getAuthentication());
          if (null==targetData) throw new zwsException("Can not create item "+transformedData.getName()+" in target repository " + target.getName());
          lookupTargetDataElement(getCurrentItem()).setTargetData(targetData);
        }
        catch (Exception e) { throw new zwsException("Can not create item "+transformedData.getName()+" in target repository " + target.getName() + " " + e.getMessage()); }

      } else {
      */
      o = target.create(getQxCtx(), transformedData, getAuthentication());
      RecorderUtil.logActivity(getQxCtx(), "Item created",  o.getName());
      statusElement.setItemStatus(DATA_TARGET_ORIGIN, o.toString());
    }
 }
}

