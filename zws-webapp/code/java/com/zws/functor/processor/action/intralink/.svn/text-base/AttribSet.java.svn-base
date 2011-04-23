/*
 * Created on Sep 30, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.zws.functor.processor.action.intralink;

/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import com.zws.application.Properties;
import com.zws.datasource.IntralinkSource;
import com.zws.functor.processor.action.Action;
import com.zws.service.config.DataSourceService;
import com.zws.util.KeyValue;
import com.zws.util.Value;

import java.util.*;


public class AttribSet extends Action {
	public void execute () throws Exception {
		
		IntralinkSource datasource = (IntralinkSource)DataSourceService.find(getDocument().get(datasourceMetadata));
	
		Exception ex=null;
		com.zws.functor.intralink.AttribSet action = new com.zws.functor.intralink.AttribSet();
		
		
		action.setName(getProperty("name"));
		action.setWorkspace(getProperty("workspaceName"));
		//action.setFolder(Properties.get(Properties.promoBaselineFolder));
		
		//Vector atts = getParentAttributes();
		//atts.add(new KeyValue("Revision", "D"));
		//atts.add(new KeyValue("Version", "5"));
		
		action.setUsername(datasource.getUsername());
		action.setPassword(datasource.getPassword());
		action.setEXEToolkitEnv(datasource.getEXEToolkitEnv());
		
		//action.setAttributes(getParentAttributes());
		//action.setDataSource(getProperty("dataSource"));
		
		try {
		  action.execute();
		  
		}
		catch(Exception e) {
		  //getActionLog().log("Failed: converting "+inputFile+" to PDF: "+getOutputPath());
		  ex = e;
		}
		if (null!=ex) throw ex;
	  }


      Vector getParentAttributes(){
      	Vector atts = new Vector();
      	if(getDocument() != null){
      	  Collection docAtts = getDocument().getAttributes();
      	  if(docAtts != null){
      	  	String type = Properties.get(Properties.typeIlinkAttribute);
      	  	Iterator docAttsI = docAtts.iterator();
      	  	while(docAttsI.hasNext()){
      	  		KeyValue att = (KeyValue)docAttsI.next();
      	  		Value descriptor = (Value)att.getDescriptor();
      	  		if(descriptor != null && type.equals(((Value)descriptor).getDecsriptor())){
      	  			if(att.getValue() != null && ((String)att.getValue()).trim().length() > 0 )
      	  				atts.add(new KeyValue((String)descriptor.getValue(), att.getValue()));
      	  		}	
      	  	}
      	  }
      	}
      	
      	return atts;
      }
	  //todo: add inputFileNameLogProperty so input filename is looked up from the log property
	  
 
	  public String getName() { return name; }
	  public void setName(String s) { name = s; }
	  public String getWorkspaceName() { return workspaceName; }
	  public void setWorkspaceName(String s) { workspaceName = s; }
	  //public String getDataSource() { return dataSource; }
	  //public void setDataSource(String s) { dataSource = s; }
      
      
	  public String getNameMetadata() { return nameMetadata; }
	  public void setNameMetadata(String s) { nameMetadata=s; }
	  public String getWorkspaceMetadata() { return workspaceMetadata; }
	  public void setWorkspaceMetadata(String s) { workspaceMetadata=s; }
	  public String getDatasourceMetadata() { return datasourceMetadata; }
	  public void setDatasourceMetadata(String s) { datasourceMetadata=s; }

	  private String name=null;
	  private String workspaceName=null;
	  //private String dataSource=null;
	  
	  private String workspaceMetadata="name";
	  
	  private String nameMetadata="name";
	  private String datasourceMetadata="Constants.METADATA_DATA_SOURCE";
	  
}








