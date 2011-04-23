package com.zws.functor.processor.action.airborn;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on April 8, 2004, 4:14 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import com.zws.domo.document.Document;
import com.zws.functor.processor.action.Action;
import com.zws.functor.util.file.UTF8Tidy;
import com.zws.xml.MetadataHandler;

import java.io.File;
import java.io.FileReader;
import java.util.Collection;
import java.util.Iterator;

import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;


public class MergeMetadataFromFile extends Action {
  public void execute() throws Exception {
   Document metadata = loadMetadata();
   Iterator i = metadata.getAttributeNames().iterator();
   String att;
   while (i.hasNext()) {
     att = (String) i.next();
     getDocument().set(att, metadata.get(att));
   }
  }
  
  private Document loadMetadata() throws Exception {
    UTF8Tidy tidy = new UTF8Tidy();
    tidy.setFilename(getFile().getAbsolutePath());
    tidy.execute();

    XMLReader xr = new SAXParser();
    MetadataHandler handler = new MetadataHandler();
    xr.setContentHandler(handler);
    xr.setErrorHandler(handler);
    FileReader r = new FileReader(getFile());
    xr.parse(new InputSource(r));
    Collection c = handler.getMetadata();
    if (null==c || 0==c.size()) return null;
    return (Document)c.toArray()[0];
  }
  
  
  public File getFile() { if (null==f) f = new File(getPath(), getFilename()); return f; }
  
  public String getFilename() { return filename; }
  public void setFilename(String s) { filename=s; }
  public String getPath() { return path; }
  public void setPath(String s) { path=s; }

  private String filename=null;
  private String path=null;
  private File f;
}
