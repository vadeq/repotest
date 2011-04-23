package zws.datasource.intralink.op.workspace;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 29, 2004, 4:47 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.server.datasource.Names;
import zws.util.MapUtil;

import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class SetAttributeList extends IntralinkWorkspaceOp {
	public void handleResponse(String outputFilename) throws Exception {}

  protected void writeWorkspaceInstruction() throws IOException {
    openTag("set-attributes");
    endUnaryTag();
    Iterator j = attributeList.keySet().iterator();
    Map attributes;
    String field, value, name;
    while (j.hasNext()) {
      name = (String)j.next();
      attributes = (Map)attributeList.get(name);
      value = null;
      field = null;
      Iterator i = findWriteableAttributes(attributes).keySet().iterator();     
      openTag("metadata");
      writeParameter("name", name);
      while (i.hasNext()) {
        field = i.next().toString();
        if (!attributes.containsKey(field)) continue;
        value = attributes.get(field).toString();
        writeParameter(field, value);
      }
      closeTag();
    }
    closeTag("set-attributes");
  }

  public void setAttributes(String name, Map atts) {
    Map m = MapUtil.getMapFromMap(attributeList, name);
    m.putAll(atts);
  }
  
  //public String getWorkspaceName() { return workspaceName; }
	//public void setWorkspaceName(String s) { workspaceName = s; }

  private Map attributeList = new HashMap();  
	//private String workspaceName=null;
}
