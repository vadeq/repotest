package zws.pen.policy.op.pendata.element.status.eco.target;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/

import java.util.Iterator;
import java.util.Map;

import zws.data.eco.ECO;
import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.repository.target.RepositoryECOTarget;


/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public class DeleteTargetECO extends PENDataOpBase {

  public void execute() throws Exception {
	  Map ecoTableCopy = getPenData().getECOReferenceTableCopy();
	  if(null == ecoTableCopy) return;
	  String key = null;
	  ECO eco = null;
	  Repository targetRepository = null;
	  RepositoryECOTarget ecoTarget = null;
	  
	  Iterator itr = ecoTableCopy.keySet().iterator();
	  while(itr.hasNext()) {
		  key = (String) itr.next();
		  eco = (ECO) ecoTableCopy.get(key);
		  targetRepository = getPenPolicy().getTargetRepository();
		  ecoTarget = targetRepository.materializeECOTarget();
		  ecoTarget.deleteEmptyECO(getQxCtx(), eco.getNumber(), isHardDelete(), getAuthentication());
		  RecorderUtil.logActivity(getQxCtx(), "Examined  ECO to delete", eco.getNumber());
	  }

  }

  private boolean hardDelete = false;

public boolean isHardDelete() {
	return hardDelete;
}

public void setHardDelete(boolean b) {
	hardDelete = b;
}
}
