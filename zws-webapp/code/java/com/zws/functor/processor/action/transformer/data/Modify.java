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


public class Modify extends Copy{
	public void execute(){
		//TODO add actual modification spe
		String val = (String)getSource().get(attributeName);
		getTarget().set(attributeName, value);
	}

    public void setValue(String s) { value = s; }
    public String getValue() { return value; }
    
    protected String value = null;

}
