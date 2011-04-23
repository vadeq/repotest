package zws.repository.ilink3.qx.client.op.workspace;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.repository.ilink3.qx.client.op.IntralinkOpBase;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class IntralinkWorkspaceOp extends IntralinkOpBase {

  protected abstract void createOpInstructionXML();
  
  protected void createInstructionXML() {
    instruction.write("<?xml version='1.0' encoding='latin1'?>" + endl);
    instruction.write("<qx output-encoding='LATIN1'>" + endl);
    instruction.write(" <ilink-qx>" + endl);
    instruction.write("  <open-repository username='"+xmlValue(getUsername())+"' password='"+xmlValue(getPassword())+"'>"+endl);
    instruction.write("   <open-sandbox ldb-path='"+xmlValue(getLDBPath())+"'>" + endl);
    instruction.write("    <open-workspace workspace='"+xmlValue(getWorkspaceName())+"'>" + endl);
    createOpInstructionXML();
    instruction.write("    </open-workspace>" + endl);
    instruction.write("   </open-sandbox>" + endl);
    instruction.write("  </open-repository>" + endl);
    instruction.write(" </ilink-qx>" + endl);
    instruction.write("</qx>");
    instruction.write(endl);
	}    

  protected boolean usePersonalWorkspace() { return usingPersonalWorkspace; }
  public void setUsingPersonalWorkspace(boolean b) { usingPersonalWorkspace=b; }

  protected Map findWriteableAttributes(Map attributes) {
    String field=null;
    boolean fieldIsWriteable;
    Map writeableFields = new HashMap();
    Iterator i = attributes.keySet().iterator();
    while (i.hasNext()) {
      fieldIsWriteable = true;
      field = i.next().toString();
      if (field.equalsIgnoreCase("branch")) fieldIsWriteable = false;
      else if (field.equalsIgnoreCase("generic")) fieldIsWriteable = false;
      else if (field.equalsIgnoreCase("version")) fieldIsWriteable = false;
      else if (field.equalsIgnoreCase("created-on")) fieldIsWriteable = false;
      else if (field.equalsIgnoreCase("instance")) fieldIsWriteable = false;
      else if (field.equalsIgnoreCase("origin")) fieldIsWriteable = false;
      if (fieldIsWriteable) writeableFields.put(field, attributes.get(field));
    }
    return writeableFields;
  }
  
  public String getWorkspaceName() { return workspaceName; }
  public void setWorkspaceName(String s) { workspaceName=s; }
  
  private String workspaceName=null;
  private boolean usingPersonalWorkspace=false;
}
