package zws.pen.policy.op.pendata.element.status.eco.pending;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/



import zws.data.eco.ECO;
import zws.pen.policy.op.pendata.PENDataProcessor;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.repository.source.RepositoryECOSource;
import zws.service.pen.ECOElement;
//impoer zws.util.{}//Logwriter;

import java.util.Collection;
import java.util.Iterator;


/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public class LoadPendingECOsOp extends PENDataProcessor {
  public void process() throws Exception{
    {}//Logwriter.printOnConsole(this,"execute","start");
    String ecoNumber= null;
    String key = null;
    ECO eco = null;
    Repository targetRepository = this.getPenPolicy().getTargetRepository();
    RepositoryECOSource ecoSource = targetRepository.materializeECOSource();
    ECOElement ecoElement = this.getPenData().lookupECOElement(getCurrentItem());
    Collection c = ecoElement.getPendingECOList();
    {}//Logwriter.printOnConsole("pending ECO list " + c);
    if(c != null) {
      Iterator itr = c.iterator();
      while(itr.hasNext()) {
       key = (String) itr.next();
       ecoNumber = key;
       eco = ecoSource.findECO(getQxCtx(), ecoNumber, getAuthentication());
       if(null == eco) {
         {}//Logwriter.printOnConsole("eco " + ecoNumber+ " not found");
       } else {
         if(!getPenData().containsECO(eco.getNumber())) {
           {}//Logwriter.printOnConsole("loading eco " + key + " value " + eco);
           getPenData().addECO(key, eco);
           RecorderUtil.logActivity(getQxCtx(), "ECO added",  " ECO No." +ecoNumber + " Item No." + getCurrentItem());
         }
       }
      }
    }
  }
}
