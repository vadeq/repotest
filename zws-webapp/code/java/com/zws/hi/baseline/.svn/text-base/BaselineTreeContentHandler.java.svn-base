package com.zws.hi.baseline;

import com.zws.hi.util.treebuilder.*;

import org.xml.sax.Attributes;

/**
 * <p>Title: DesignState</p>
 * <p>Description: Design Compression Technology</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Zero Wait-State</p>
 * @author not attributable
 * @version 1.0
 */
//non generic content handler
public class BaselineTreeContentHandler extends TreeContentHandler{
  protected Class contentHandlerClass = TreeContentHandler.class;
  private TreeFolder currentRevision = null;


  private String metadata = "";
  private String revision = "";

  public void startElement( String uri, String localName, String qName, Attributes attribs ) {

    String version = "";
    String attr = "";
    if( localName.equals( "metadata" ) ) {
      metadata = resolveAttrib( uri, "name", attribs, defLink );
      //attr = resolveAttrib( uri, "link", attribs, link );
      root = new TreeRoot(metadata, attr);


    }else if( localName.equals( "revision" ) ) {
      revision = resolveAttrib( uri, "value", attribs, defLink );
      //attr = resolveAttrib( uri, "link", attribs, link );
      currentRevision = new TreeFolder(revision, attr);
      root.addNode(currentRevision);


    }else if( localName.equals( "version" ) ) {
      version = resolveAttrib( uri, "value", attribs, defLink );
      String name = metadata + ":" + revision + ":" + version;
      attr = resolveAttrib( uri, "link", attribs, link + "&filename="  + metadata + "&filelocation=" + name);
      TreeLeaf currentVersion = new TreeLeaf(version, attr);
      currentRevision.addNode(currentVersion);
    }
  }
      // -----

  public void endElement( String uri, String localName, String qName ) {
  }

}
