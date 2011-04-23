package zws.repository.ilink3.qx.client.op.sandbox;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.repository.ilink3.qx.client.op.IntralinkOpBase;

public abstract class IntralinkSandboxOp extends IntralinkOpBase {

  protected abstract void createOpInstructionXML();
  
  protected void createInstructionXML() {
    instruction.write("<?xml version='1.0' encoding='latin1'?>" + endl);
    instruction.write("<qx output-encoding='LATIN1'>" + endl);
    instruction.write(" <ilink-qx>" + endl);
    instruction.write("  <open-repository username='"+xmlValue(getUsername())+"' password='"+xmlValue(getPassword())+"'>"+endl);
    createOpInstructionXML();
    instruction.write("  </open-repository>" + endl);
    instruction.write(" </ilink-qx>" + endl);
    instruction.write("</qx>");
    instruction.write(endl);
	}

  protected boolean usePersonalWorkspace() { return usingPersonalWorkspace; }
  public void setUsingPersonalWorkspace(boolean b) { usingPersonalWorkspace=b; }

  private boolean usingPersonalWorkspace=false;
}
