/*
 * Created on Sep 8, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.zws.functor.intralink;

import com.zws.domo.baseline.Folder;
import com.zws.util.GenericSAXHandler;

import org.xml.sax.Attributes;
/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetFoldersContentHandler extends GenericSAXHandler{
	
	private Folder rootFolder = null;
	private Folder lastFolder = null;
	
	public Folder getFolder() { return rootFolder; }
	
	
	public void startElement( String uri, String localName, String qName, Attributes attribs ) {

		String name = "";
		String attr = "";
		if( localName.equals( "folder" ) ) {
		  name = resolveAttrib( uri, "name", attribs, "" );
		  if(rootFolder == null){
		  	rootFolder = new Folder(name);
		  	lastFolder = rootFolder;
		  }else{
		  	Folder child = new Folder(name);
		  	lastFolder.addChild(child);
		  	lastFolder = child;
		  }
		}
	  }
	  // -----

	  public void endElement( String uri, String localName, String qName ) {
		if( localName.equals( "folder" ) ) {
			lastFolder = lastFolder.getParent();	
		}
	  }

}
