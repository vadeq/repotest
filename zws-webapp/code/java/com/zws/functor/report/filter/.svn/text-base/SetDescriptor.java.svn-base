/*
 * Created on Oct 2, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.zws.functor.report.filter;

/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import com.zws.application.Properties;
import com.zws.domo.document.Document;
import com.zws.functor.filter.DocumentCollectiveFilter;
import com.zws.util.KeyValue;
import com.zws.util.Value;

import java.util.*;

public class SetDescriptor extends DocumentCollectiveFilter {


  public Object transform(Document doc) {
    setIgnores();
	Collection docAtts = doc.getAttributes();
	 if(docAtts != null){
		Iterator docAttsI = docAtts.iterator();
		while(docAttsI.hasNext()){
			KeyValue att = (KeyValue)docAttsI.next();
			Value descriptor = (Value)att.getDescriptor();
			String type = Properties.get(Properties.typeIlinkAttribute);
			
			if(descriptor != null && type.equals(((Value)descriptor).getDecsriptor())){
				if(!ignores.containsKey(att.getKey()))
					descriptor.setValue(att.getKey());
				else
				    descriptor.setDecsriptor("readOnly");
			}
		}
	} 

	return doc;
  }
  
  
  private void setIgnores(){
  	ignores= new HashMap();
  	if(ignore != null){
  	
  		StringTokenizer tok = new StringTokenizer(ignore, ",");
  		while(tok.hasMoreTokens()){
  			String val = tok.nextToken();
  			ignores.put(val, val);
  		}
  	}
  }
  
  
  private HashMap ignores=null;

  public String getUsing() { return using; }
  public void setUsing(String s) { using=s ; }
  
  public String getIgnore() { return ignore; }
  public void setIgnore(String s) { ignore=s ; }
 

  private String using=null;
  private String ignore=null;
  

  
}
