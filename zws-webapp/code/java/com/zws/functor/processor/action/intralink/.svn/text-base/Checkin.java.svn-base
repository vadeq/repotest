/*
 * Created on Sep 26, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.zws.functor.processor.action.intralink;

import com.zws.datasource.IntralinkSource;
import com.zws.functor.processor.action.Action;
import com.zws.service.config.DataSourceService;
/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Checkin extends Action {
	public void execute () throws Exception {
		Exception ex=null;
		com.zws.functor.intralink.Checkin action = new com.zws.functor.intralink.Checkin();
		IntralinkSource datasource = (IntralinkSource)DataSourceService.find(getDocument().get(datasourceMetadata));

		
		action.setWorkspaceName(getProperty("workspaceName"));
		
		action.setUsername(datasource.getUsername());
		action.setPassword(datasource.getPassword());
		action.setEXEToolkitEnv(datasource.getEXEToolkitEnv());
		
		try {
		  action.execute();
		  
		}
		catch(Exception e) {
		  //getActionLog().log("Failed: converting "+inputFile+" to PDF: "+getOutputPath());
		  ex = e;
		}
		if (null!=ex) throw ex;
	  }

	  //todo: add inputFileNameLogProperty so input filename is looked up from the log property
	  public String getDatasourceMetadata() { return datasourceMetadata; }
	  public void setDatasourceMetadata(String s) { datasourceMetadata=s; }
	  public String getWorkspaceMetadata() { return workspaceMetadata; }
	  public void setWorkspaceMetadata(String s) { workspaceMetadata=s; }
	  
	  public String getWorkspaceName() { return workspaceName; }
	  public void setWorkspaceName(String s) { workspaceName = s; }
	  public String getDataSource() { return dataSource; }
	  public void setDataSource(String s) { dataSource = s; }
	  
	  private String workspaceName=null;
	  private String dataSource=null;
	  private String workspaceMetadata="name";
	  private String datasourceMetadata="Constants.METADATA_DATA_SOURCE";
	  
}








