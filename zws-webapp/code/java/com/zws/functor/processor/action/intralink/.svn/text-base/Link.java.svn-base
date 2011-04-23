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
public class Link extends Action {
	public void execute () throws Exception {
		Exception ex=null;
		com.zws.functor.intralink.Link action = new com.zws.functor.intralink.Link();
		IntralinkSource datasource = (IntralinkSource)DataSourceService.find(getDocument().get(datasourceMetadata));

		action.setComponent(getProperty("component"));
		action.setDependant(getProperty("dependant"));
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
	  
	  public String getComponent() { return component; }
      public void setComponent(String s) { component=s; } 
      public String getDependant() { return dependant; }
      public void setDependant(String s) { dependant = s; }
      public String getWorkspaceName() { return workspaceName; }
      public void setWorkspaceName(String s) { workspaceName = s; }
      public String getDataSource() { return dataSource; }
      public void setDataSource(String s) { dataSource = s; }
	  
      
      
	  public String getDependantMetadata() { return dependantMetadata; }
	  public void setDependantMetadata(String s) { dependantMetadata=s; }
	  public String getWorkspaceMetadata() { return workspaceMetadata; }
	  public void setWorkspaceMetadata(String s) { workspaceMetadata=s; }
	  public String getComponentMetadata() { return componentMetadata; }
	  public void setComponentMetadata(String s) { componentMetadata=s; }
	  public String getDatasourceMetadata() { return datasourceMetadata; }
	  public void setDatasourceMetadata(String s) { datasourceMetadata=s; }

	  private String component=null;
	  private String dependant=null;
	  private String workspaceName=null;
	  private String dataSource=null;
	  
	  private String workspaceMetadata="name";
	  private String componentMetadata="name";
	  private String dependantMetadata="name";
	  private String datasourceMetadata="Constants.METADATA_DATA_SOURCE";
	  
}








