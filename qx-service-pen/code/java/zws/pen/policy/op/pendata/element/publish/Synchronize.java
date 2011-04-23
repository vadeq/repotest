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
import zws.origin.OriginMaker;
import zws.pen.policy.op.pendata.PENDataProcessor;
import zws.recorder.util.RecorderUtil;
import zws.service.pen.StatusElement;
import zws.service.synchronization.qx.SynchronizationClient;
import zws.service.synchronization.qx.SynchronizationService;
//impoer zws.util.{}//Logwriter;

public class Synchronize extends PENDataProcessor {
  public void process() throws Exception {
    StatusElement statusElement = lookupStatusElement(getCurrentItem());
    boolean alreadySynchronized = OPT_SYNCHRONIZED.equalsIgnoreCase(statusElement.getItemStatus(STAT_SYNC_STATUS));
    boolean needToSynchronize = TRUE.equalsIgnoreCase(statusElement.getItemStatus(ACTION_ITEM_NEEDS_TO_BE_SYNCHRONIZED));
    if (alreadySynchronized ) {
      {}//Logwriter.printOnConsole("Items are already synchronized...");
      return;
    }
    if (!needToSynchronize) {
      {}//Logwriter.printOnConsole("Items does not need to be synchronized...");
      return;
    }

    //++check that target origin exists!!
    //throw error if it doesnt
    SynchronizationService r = SynchronizationClient.getClient();
    Metadata srcData = getPenData().lookupSrcMetaData(getCurrentItem());
    {}//Logwriter.printOnConsole("srcData " + srcData);

    Origin sourceOrigin = srcData.getOrigin();
    {}//Logwriter.printOnConsole("src Origin " + sourceOrigin);

    String strTargetOrigin = lookupStatusElement(getCurrentItem()).getItemStatus(DATA_TARGET_ORIGIN);
    {}//Logwriter.printOnConsole("strTargetOrigin " + strTargetOrigin);
    if(null != sourceOrigin && sourceOrigin.toString().length() >0 &&
        null != strTargetOrigin && strTargetOrigin.length() >0) {
      Origin targetOrigin = OriginMaker.materialize(strTargetOrigin);
      {}//Logwriter.printOnConsole("target origin " + targetOrigin);
      r.record(sourceOrigin, targetOrigin);
      RecorderUtil.logActivity(getQxCtx(), "Synchronized",  sourceOrigin.getName() + "=" + targetOrigin.getName());
      {}//Logwriter.printOnConsole("synchronization completed in SynchronizeTargetOp");
    }

    /*else {
      {}//Logwriter.printOnConsole("Either src Origin or Target Origin is null;");
      throw new zws.exception.InvalidOrigin("Either src Origin or Target Origin is null;");
    }*/
 }
}
