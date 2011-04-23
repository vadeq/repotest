package com.zws.hi.util.treebuilder;

import java.io.*;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
//import org.apache.xerces.util.

/**
 * <p>Title: DesignState</p>
 * <p>Description: Design Compression Technology</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Zero Wait-State</p>
 * @author not attributable
 * @version 1.0
 */

public class TreeBuilder {

  protected Class contentHandlerClass;

  public TreeBuilder(Class contentHandlerClass) {
    this.contentHandlerClass = contentHandlerClass;
  }

  public TreeRoot createTreeFromFile(String filename, String defLink) throws TreeBuilderException{
    File file = new File(filename);
    InputSource src = null;
    try{
      src = new InputSource(new FileInputStream(file));
    }catch(java.io.FileNotFoundException fnfe){
      fnfe.printStackTrace();
      return null;
    }
    return parseTree(src, defLink);
  }

  public TreeRoot createTreeFromString(String xmlString, String defLink) throws TreeBuilderException{
    InputSource src = new InputSource( new StringReader(xmlString));
    return parseTree(src, defLink);
  }

  private TreeRoot parseTree(InputSource src, String defLink) throws TreeBuilderException{
    TreeContentHandler handler = null;
    if(contentHandlerClass == null)
      throw new TreeBuilderException("no valid content handler class provided");
    try{
      handler = (TreeContentHandler)contentHandlerClass.newInstance();
    }catch(java.lang.InstantiationException ie){
      throw new TreeBuilderException("error instantiating contenet handler class " + contentHandlerClass.getName());
    }catch(java.lang.IllegalAccessException iae){
      throw new TreeBuilderException("error instantiating contenet handler class " + contentHandlerClass.getName());
    }
    if(defLink != null && defLink.trim().length() > 0)
      handler.setLink(defLink);
    try {
      XMLReader rdr = XMLReaderFactory.createXMLReader(
          "org.apache.xerces.parsers.SAXParser");
      rdr.setContentHandler(handler);
      rdr.parse(src);
    }
    catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
    return handler.getTree();
  }
}



