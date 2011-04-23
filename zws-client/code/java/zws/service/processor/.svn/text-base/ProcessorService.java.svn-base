/*
 * Created on Oct 13, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package zws.service.processor;

/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import zws.processor.Processor;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Collection;

public interface ProcessorService extends Serializable{

	public Processor getProcessor(String name) throws RemoteException;
	public Collection getProcessors()throws RemoteException;
	public void execute(Processor p)throws RemoteException;
}
