package zws.pen.policy.op.pendata.element.transform.structure;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/



import zws.data.Metadata;
import zws.data.eco.ECO;
import zws.pen.policy.op.pendata.PENDataProcessor;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.repository.target.RepositoryECOTarget;

public class RemoveECOAttachments extends PENDataProcessor{

	public void process() throws Exception {
	    ECO eco = getPenData().lookupTargetECO(getCurrentItem());
	    if(null == eco) return;
	    Metadata targetData = lookupTargetMetadata(getCurrentItem());
	    if(null == targetData) return;
	    Repository targetRepository = getPenPolicy().getTargetRepository();
	    RepositoryECOTarget ecoTarget = targetRepository.materializeECOTarget();	    
	    ecoTarget.removeAttachments(getQxCtx(), eco.getNumber(), targetData.getName(), getAuthentication());
	    RecorderUtil.logActivity(getQxCtx(), "remove all attachments on ECO" + eco.getName(),   getCurrentItem());	    
	  }
}
