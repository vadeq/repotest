package zws.repository.agile.px;

/*
	DesignState - Design Compression Technology.
	@author: Arbind Thakur
	Created on Mar 3, 2006
	@version: 1.0
	Copywrite (c) 2006 Zero Wait-State Inc. All rights reserved
*/

import zws.repository.agile.event.EventClient;

import com.agile.api.IAgileSession;
import com.agile.api.IChange;
import com.agile.api.IDataObject;
import com.agile.api.INode;
import com.agile.px.ActionResult;
import com.agile.px.ICustomAction;


public class FireZWSEvent extends PxActionBase implements ICustomAction {

	public ActionResult doAction(IAgileSession session, INode actionNode, IDataObject affectedObject) {

	  try {

	    IChange change = null;

      StringBuffer xmlString = new StringBuffer();
	    if (affectedObject instanceof IChange ) {
	      change = (IChange) affectedObject;
	      xmlString.append("<event event-type='eco-update' ");
	      xmlString.append("number='").append(change.getName()).append("'").append(" ");
        xmlString.append("domain-name='").append(getProperty(DESIGNSTATE_DOMAIN_NAME)).append("'").append(" ");
        xmlString.append("server-name='").append(getProperty(DESIGNSTATE_SERVER_NAME)).append("'").append(" ");
	      xmlString.append("repository-name='").append(getProperty(DESIGNSTATE_REPOSITORY_NAME)).append("'").append(" ");
        xmlString.append("type='").append(change.getType()).append("'").append(" ");
        xmlString.append("status='").append(change.getStatus().getName()).append("'").append(" ");
	      xmlString.append("user='").append(session.getCurrentUser().getName()).append("'").append("/> ");
	      PersistedEventManager.getInstance().sendEvent(xmlString.toString());

	    }
    } catch (Exception e) {
      e.printStackTrace();
      debug(e.getMessage());
    }
    return new ActionResult(ActionResult.STRING, "Agile Px");
	}

	public static void main(String args[]) {
/*
		// start with a clean state
		PXFile pxFile = new PXFile(DAT_FILE);
		pxFile.removeFile();

		// jam stuff into the file as if the interface was down
		PX px = new PX();
		retVal = false;
		System.out.println("\n\t\t*** Simulating design state server down... ***");

		System.out.println("\nNew Event Fired!!!");
		px.fire("1","wip");

		System.out.println("\nNew Event Fired!!!");
		px.fire("2","pending");


		// now all 3 items should be sent
		retVal = true;
		System.out.println("\n\t\t*** Simulating design state server up... ***");
		System.out.println("\nNew Event Fired!!!");
		px.fire("3","promote");
*/
	}
}