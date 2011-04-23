/*
 * Created on Oct 13, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package zws.op.util;

/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import zws.exception.InvalidValue;
import zws.exception.NameNotFound;
import zws.op.OpBase;

import java.lang.reflect.Method;

public class Getter extends OpBase {
	
	public void execute()throws Exception{
		if(opObject == null) throw new InvalidValue("functorObject");
		if(propertyName == null) throw new InvalidValue("propertyName");
		char[] pn = propertyName.toCharArray();
		pn[0] = Character.toUpperCase(pn[0]);
		String uCPropertyName = new String(pn);
		String methodName = "get" + uCPropertyName;	
		try{
      Class[] noArgs=null;
      Object[] noObjs=null;
			Class objClass = opObject.getClass();
			Method 	propGetter = opObject.getClass().getMethod(methodName, noArgs);
			Object propertyValue = propGetter.invoke(opObject, noObjs);
			setResult(propertyValue);
		}
    catch(NoSuchMethodException nsme){ throw new NameNotFound(methodName, opObject.getClass().getName()); }
	}	

	public void setObject(Object obj) {opObject = obj;}
	public void setPropertyName(String name) { propertyName = name;}

  private Object opObject = null;
	private String propertyName = null;
}


  		

