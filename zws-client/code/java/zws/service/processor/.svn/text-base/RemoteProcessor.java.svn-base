/*
 * Created on Oct 23, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package zws.service.processor;

import zws.processor.Processor;
import zws.processor.ProcessorBase;
import zws.util.DomainContext;
/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RemoteProcessor extends ProcessorBase {

	public final boolean PARENT_CONTEXT_RUN = true;
	public final boolean CHILD_CONTEXT_RUN = false;

	protected void process(DomainContext ctx) {
		
		{} //System.out.println("Entering process");
		try{
			DomainContext dctx = ctx;
			if(!isUseParentContext())
				dctx = childProcess.getContext();
			service = getProcessorService(dctx);
			if(service == null){
				{} //System.out.println("No Service Found");
			}else{
				{} //System.out.println("before execute " + childProcess.getName());
				service.execute(childProcess);
			}
	
		}catch(Exception e){
			e.printStackTrace();	
		}
	}
	
	public void setChildProcess(Processor p) { 
		if(p.getContext() == null)
			p.setContext(new DomainContext());
		
		p.getContext().setParent(getContext());	
		childProcess = p; 
	}
	public Processor getChildProcess() { return childProcess; }
	
	public void setUseParentContext(boolean m) { 
		useParentContext = m; 
	}
	public boolean isUseParentContext() { return useParentContext; }
	
	private ProcessorServiceRemote getProcessorService(DomainContext ctx){
		String server = (String)ctx.get(SERVER);
		return EJBLocator.findService(server);
	}
	
	private ProcessorServiceRemote service = null;
	private Processor childProcess = null;
	private boolean useParentContext = CHILD_CONTEXT_RUN;
	
  private static String SERVER="server";
}
