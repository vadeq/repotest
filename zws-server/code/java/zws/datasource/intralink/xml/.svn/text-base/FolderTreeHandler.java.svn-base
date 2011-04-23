package zws.datasource.intralink.xml;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 6, 2004, 8:00 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.folder.IntralinkFolder;
import zws.log.failure.Failure;

import java.util.*;

import org.xml.sax.Attributes;

public class FolderTreeHandler extends IntralinkResultHandler {
  public void startElement (String uri, String name, String qName, Attributes atts) { 
    if ( qName.equalsIgnoreCase("folder")) { pushFolder(atts); return; }
    try {
      if ( qName.equalsIgnoreCase("failed-to-authenticate")) { getStorable().log(new Failure("err.invalid.authentication")); return; }
    }
    catch (Exception e) { e.printStackTrace(); }
  }

  public void endElement (String uri, String name, String qName) {
    if (!qName.equalsIgnoreCase("folder")) return; 
    IntralinkFolder parent=null;
    IntralinkFolder f = (IntralinkFolder)stack.pop();
    if (!stack.isEmpty()) parent = (IntralinkFolder)stack.peek();
    if (null==parent) rootFolder = f;
    else {
      try { parent.addSubFolder(f); }
      catch (Exception e) { e.printStackTrace(); } 
    }
  }

  private void pushFolder(Attributes atts) {
    try {
      IntralinkFolder f = unmarshallFolder(atts);
      stack.push(f);
    }
    catch (Exception e) { e.printStackTrace(); /*++ log this error*/ }
  }

  public IntralinkFolder getRootFolder() { return rootFolder; }
  
  private IntralinkFolder rootFolder = null;
  private Stack stack = new Stack();
}
