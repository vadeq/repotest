/*
 * Created on Sep 29, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.zws.functor;

/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import com.zws.exception.EntryNotFound;
import com.zws.exception.InvalidValue;

import java.lang.reflect.Method;

public class Getter extends Functor{
	
	private Object functorObject = null;
	private String propertyName = null;
	
	public void setObject(Object obj) {functorObject = obj;}
	public void setPropertyName(String name) { propertyName = name;}

	public void execute()throws Exception{
		if(functorObject == null)
			throw new InvalidValue("functorObject");
		if(propertyName == null)
			throw new InvalidValue("propertyName");
			
		//Object[] args = {};
 
		char[] pn = propertyName.toCharArray();
		pn[0] = Character.toUpperCase(pn[0]);
		String uCPropertyName = new String(pn);
		String methodName = "get" + uCPropertyName;	
		try{
      Object[] noArgs=null;
      Class[] noArgTypes=null;
			Class objClass = functorObject.getClass();
			Method 	propGetter = functorObject.getClass().getMethod(methodName, noArgTypes);
			String propertyValue = (String)propGetter.invoke(functorObject, noArgs);
			setResult(propertyValue);
		}catch(NoSuchMethodException nsme){
			throw new EntryNotFound(methodName);
		}
	}
		

}


  		
