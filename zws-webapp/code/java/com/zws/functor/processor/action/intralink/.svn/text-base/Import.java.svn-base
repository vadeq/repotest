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

import java.io.File;
/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Import extends Action {
	public void execute () throws Exception {
		Exception ex=null;
		com.zws.functor.intralink.Import action = new com.zws.functor.intralink.Import();
		
		IntralinkSource datasource = (IntralinkSource)DataSourceService.find(getDocument().get(datasourceMetadata));

		String fileName = getProperty("path") + File.separator + getProperty("filename");
		action.setFilename(fileName);
		action.setWorkspace(getProperty("workspaceName"));
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
	  public String getFilenameMetadata() { return filenameMetadata; }
	  public void setFilenameMetadata(String s) { filenameMetadata=s; }
	  public String getWorkspaceMetadata() { return workspaceMetadata; }
	  public void setWorkspaceMetadata(String s) { workspaceMetadata=s; }  
	  public String getDatasourceMetadata() { return datasourceMetadata; }
	  public void setDatasourceMetadata(String s) { datasourceMetadata=s; }
	  public String getPathMetadata() { return pathMetadata; }
	  public void setPathMetadata(String s) { pathMetadata=s; }
	  
	  
	  public String getFilename() { return filename; }
      public void setFilename(String s) { filename=s; } 
	  public String getPath() { return path; }
	  public void setPath(String s) { path=s; } 
	  public String getWorkspaceName() { return workspaceName; }
	  public void setWorkspaceName(String s) { workspaceName = s; }
	  public String getDataSource() { return dataSource; }
	  public void setDataSource(String s) { dataSource = s; }

	  private String filename=null;
	  private String workspaceName=null;
	  private String dataSource=null;
	  private String path=null;
	  
	  private String workspaceMetadata="name";
	  private String filenameMetadata="name";
	  private String pathMetadata=null;
	  private String datasourceMetadata="Constants.METADATA_DATA_SOURCE";
	  
}









