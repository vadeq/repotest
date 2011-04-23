/*
 * Created on Nov 17, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package zws.util;

/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import org.xml.sax.Attributes;

public class GenericSAXHandler extends org.xml.sax.helpers.DefaultHandler{
	
	
	protected String resolveAttrib( String uri, String localName,
									Attributes attribs, String defaultValue ) {

		String tmp = attribs.getValue( uri, localName );
		return (tmp!=null)?(tmp):(defaultValue);
	}
	
	public Object getResult() { return result; }
	public void setResult(Object s) { result = s; }
	
	private Object result;
}

