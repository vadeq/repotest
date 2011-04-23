/*
 * Created on Oct 27, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package zws.action.intralink;

import zws.action.ActionBase;
import zws.datasource.intralink.IntralinkSource;
import zws.service.datasource.DatasourceSvc;
//import zws.util.{}//Logwriter;
/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class IntralinkAction extends ActionBase {

    public IntralinkSource getDatasource(){
		if(ds == null){
		  try{
		  
			{}//Logwriter.printOnConsole("looking for datasource " + getDatasourceName());
			ds = (IntralinkSource)DatasourceSvc.find(getDatasourceName());
		  }catch(Exception ex){
		  	ex.printStackTrace();	
		  }
		}
		return ds;
    }
    
	public String getDatasourceName() { 
		String datasourceName = "";
		if(getContext() != null){
			datasourceName = (String)getContext().get(DATASOURCE_NAME);
		}
		return datasourceName;
	}
	
	public void setDatasourceName(String s) { 
		if(getContext() != null){
			getContext().set(DATASOURCE_NAME, s);
		} 
	}
	
	public String getWorkspaceName() { 
		String workspaceName = "";
		if(getContext() != null){
			workspaceName = (String)getContext().get(WORKSPACE_NAME);
		}
		return workspaceName; 
	}
	
	public void setWorkspaceName(String s) { 
		if(getContext() != null){
			getContext().set(WORKSPACE_NAME, s);
		}
	} 
	
	private String datasourceName = null;
	private String workspaceName = null;
	private IntralinkSource ds = null;
	
	public static final String DATASOURCE_NAME = "datasource-name";
	public static final String WORKSPACE_NAME = "workspace-name";
}
