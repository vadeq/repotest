package zws.datasource.intralink.op.workspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.datasource.intralink.xml.IntralinkResultHandler;

import java.io.*;

public class CheckoutLatest extends IntralinkWorkspaceOp {
  public IntralinkResultHandler getXMLResultHandler() { return null; }
  protected void writeWorkspaceInstruction() throws IOException {
    openTag("checkout-latest");
    writeParameter("name", getComponentName());
    closeTag();
  }

  //public String getWorkspaceName() { return workspaceName; }
  //public void setWorkspaceName(String s) { workspaceName = s; }
  public String getComponentName() { return componentName; }
  public void setComponentName(String s) { componentName = s; }

  private String componentName=null;
}
