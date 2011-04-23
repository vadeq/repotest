/*
 * Created on Oct 22, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package zws.action.transformer.data;

import zws.action.transformer.Transformer;

/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Copy extends Transformer {
	public void execute(){	
		String value = (String)grabMetadata().get(attributeName);
		if(value != null && value.trim().length() > 0)
			getTarget().set(attributeName, value);
	}

	public String getAttributeName() { return attributeName; }
	public void setAttributeName(String s) { attributeName = s; }
	
	protected String attributeName = null;
}
