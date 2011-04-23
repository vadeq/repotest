/*
 * Created on Aug 26, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.zws.functor.finder;

import com.zws.application.Config;
import com.zws.domo.document.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BaselineFinder extends IntralinkFinder {

	String baselineName;
	private ArrayList docs = new ArrayList();

	
	public void addDoc(Document doc){
		docs.add(doc);
	}
	
	public void setBaselineName(String baselineName){
		this.baselineName = baselineName;
	}
	
	public synchronized void find() throws Exception {
		
		Iterator docI = docs.iterator();
		clean();
		while (docI.hasNext()){
			Document doc = (Document)docI.next();
			setBinary(doc.getName());
			setComponentName(doc.getName());
			setWorkspaceName(doc.getName());	
			File f = new File(getOutputFileName());		
			checkout();
			export();
		}
		setBinary(baselineName +".zip");
		zip();
	}
	protected String getExportPath() {
	  return Config.getProperty(Config.DIR_TEMP); // + Config.FILE_SEPARATOR + getComponentName();
	}
	public String getComponentName() { return componentName==null?"":componentName; }
  	
}



