/*
 * Created on Oct 22, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.zws.functor.processor.action.transformer.data;

/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import com.zws.datasource.DataSource;
import com.zws.domo.document.Document;
import com.zws.functor.intralink.AttribSet;
import com.zws.functor.processor.action.transformer.Transformer;
import com.zws.service.config.DataSourceService;
import com.zws.util.KeyValue;

import java.util.*;

public class DataTransformer extends Transformer{
	public void execute() throws Exception{
		try {
		  DataSource ds = getDataSource();
		  if(ds == null){
		  	ds = DataSourceService.find(getDataSourceName());
			setDataSource(ds);
		  }
		
		  actionMap = constructActionMap(ds);
		  Iterator keys = actionMap.keySet().iterator();
		  while(keys.hasNext()){ //set all to copy
			String key = (String)keys.next();
			Transformer a = (Transformer)actionMap.get(key);
			if(a != null)
				a.execute();	
		  }
		
		  AttribSet action = new AttribSet();
		  action.setUsername(ds.getUsername());
		  action.setPassword(ds.getPassword());
		  //action.setEXEToolkitEnv(ds.getEXEToolkitEnv());
		  action.setTarget(getTarget());
		
		
		  //action.setName(getProperty("targetName"));
		  //action.setWorkspace(getProperty("workspaceName"));
		  action.setWorkspace("tempspace");
		
		  action.execute();
		  
		}
		catch(Exception e) {
				  //getActionLog().log("Failed: converting "+inputFile+" to PDF: "+getOutputPath());
			e.printStackTrace();
		}
	}
	
	private HashMap constructActionMap(DataSource ds){
		
		
		setSource(getActionLog().getDocument());
		
		if(ds != null){
			HashMap actionMap = new HashMap(ds.getSchema());
			Iterator keys = actionMap.keySet().iterator();
			while(keys.hasNext()){ //set all to copy
				String key = (String)keys.next();
				Copy copyAction = new Copy();
				copyAction.setAttributeName(key);
				if(getTarget() == null){
					Document doc = new Document();
					doc.setName(getTargetName());
					setTarget(doc);	
					
				}
				copyAction.setSource(getSource());
				copyAction.setTarget(getTarget());
				actionMap.put(key, copyAction);
			}
			Iterator ignores = ignoreList.iterator();
			while(ignores.hasNext()){
				String key = (String)ignores.next();
				actionMap.put(key, null);
			}
			Iterator modifies = modifyList.iterator();
			while(modifies.hasNext()){
				Modify modifyAction = (Modify)modifies.next();
				String modKey = modifyAction.getAttributeName();
				if(actionMap.containsKey(modKey)){ 
					
					modifyAction.setTarget(getTarget());
					modifyAction.setSource(getSource());
					actionMap.put(modKey, modifyAction);
				}
			}
			return actionMap;
		}
		return null;	
	}
	
	public void addIgnore(KeyValue pair) { ignoreList.add(pair.getKey()); } 
	public void addModify(Modify m) { 
		modifyList.add(m); 
	}
	
	private HashMap actionMap = null;
	private Vector ignoreList = new Vector();
	private Vector modifyList = new Vector();
	
}
