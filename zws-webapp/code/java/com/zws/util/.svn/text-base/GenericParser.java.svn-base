/*
 * Created on Sep 8, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.zws.util;

/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import java.io.File;
import java.io.FileInputStream;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class GenericParser {
	protected Class contentHandlerClass;
	private GenericSAXHandler handler = null;
	
	public GenericParser(Class contentHandlerClass) {
		this.contentHandlerClass = contentHandlerClass;
	}
	
	public GenericSAXHandler getHandler() { return handler; }
	
	public void parse(String filename){
		
		File file = new File(filename);
		InputSource src = null;
		try{
			src = new InputSource(new FileInputStream(file));	
			
		  	XMLReader rdr = XMLReaderFactory.createXMLReader(
			  "org.apache.xerces.parsers.SAXParser");
			
			handler = (GenericSAXHandler)contentHandlerClass.newInstance();
		  	rdr.setContentHandler(handler);
		  	rdr.parse(src);
		}catch(java.io.FileNotFoundException fnfe){
			fnfe.printStackTrace();
	  	}
		catch (Exception ex) {
		  ex.printStackTrace();
		}
	}
}


	
    
	
