package zws.pen.policy.op.pendata.element.publish;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.data.Metadata;
import zws.data.MetadataSubComponent;
import zws.data.MetadataSubComponentBase;
import zws.data.eco.ECO;
import zws.pen.policy.op.pendata.PENDataProcessor;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.repository.target.RepositoryECOTarget;
import zws.service.pen.ECOElement;
import zws.service.pen.RedlineElement;
import zws.service.pen.TxDataElement;
//import zws.util.{}//Logwriter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class DeleteMeAddAffectedItemOP extends PENDataProcessor {
  public void process() throws Exception {
    {}//Logwriter.printOnConsole(this,"process","start");
    ECO eco = null;
    String currentItem = getCurrentItem();
    //ArrayList addedItemList = new ArrayList();
    //StatusElement statusElement = lookupStatusElement(currentItem);
    TxDataElement txDataElement = this.lookupTxDataElement(currentItem);
    ECOElement ecoElement = this.getPenData().lookupECOElement(currentItem);
    String tatgetECORefName = ecoElement.getTargetECO();
    eco = getPenData().getECO(tatgetECORefName);
    {}//Logwriter.printOnConsole("tatgetECO Ref Name " + tatgetECORefName);
    {}//Logwriter.printOnConsole("tatgetECONumber " + eco);
    if(null != tatgetECORefName && null!= eco) {
      Repository targetRepository = this.getPenPolicy().getTargetRepository();
      RepositoryECOTarget ecoTarget = targetRepository.materializeECOTarget();
      Metadata txData = getPenData().lookupTxMetaData(currentItem);
      RedlineElement reds = lookupRedlineElement(currentItem);
      //Collection childList = txDataElement.getSubComponents();
      {}//Logwriter.printOnConsole("sub components.... " );
      //zws.util.PrintUtil.print(childList);
      MetadataSubComponent subItem = null;
      ecoTarget.addAffectedItem(getQxCtx(), eco.getNumber(), txData.getName(), getAuthentication());
      Iterator itr;
      String subcomponent;

      itr = reds.getRedlineAddList().iterator();
      while(itr.hasNext()) {
        subcomponent = (String)itr.next();
        subItem = txDataElement.getSubComponent(subcomponent);
        if (null==subItem) throw new zws.exception.NameNotFound("redline add "+ subcomponent+ " not found as subcomponent of " + txData.getName());
        ecoTarget.redlineAdd(getQxCtx(), eco.getNumber(), txData.getName(), subcomponent, subItem.getQuantity(),null, getAuthentication());
        {}//Logwriter.printOnConsole(subcomponent + " redline add under " + txData.getName());
      }      

      itr = reds.getRedlineDeleteList().iterator();
      while(itr.hasNext()) {
        subcomponent = (String)itr.next();
        ecoTarget.redlineDelete(getQxCtx(), eco.getNumber(), txData.getName(), subcomponent, getAuthentication());
        {}//Logwriter.printOnConsole( subcomponent + " redline deleted under " + txData.getName());
      }      

      QxContext bomAttributes;
      itr = reds.getRedlineModifyList().iterator();
      while(itr.hasNext()) {
        subcomponent = (String)itr.next();
        subItem = txDataElement.getSubComponent(subcomponent);
        if (null==subItem) throw new zws.exception.NameNotFound("redline modify "+ subcomponent+ " not found as subcomponent of " + txData.getName());
        bomAttributes = txDataElement.getBOMAttributes(subcomponent);
        ecoTarget.redlineModify(getQxCtx(), eco.getNumber(), txData.getName(), subcomponent, bomAttributes, getAuthentication());
        {}//Logwriter.printOnConsole(subcomponent + " redline modify under " + txData.getName());
      }      
      
    } else {
      {}//Logwriter.printOnConsole(getCurrentItem() + " not added as affected Item");
    }
 }
}

