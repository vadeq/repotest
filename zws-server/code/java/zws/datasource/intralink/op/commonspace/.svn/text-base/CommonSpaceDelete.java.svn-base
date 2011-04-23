package zws.datasource.intralink.op.commonspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.datasource.intralink.xml.IntralinkResultHandler;

import java.io.IOException;

public class CommonSpaceDelete extends IntralinkRepositoryOp {
  public IntralinkResultHandler getXMLResultHandler() { return null; }
  protected void writeRepositoryInstruction() throws IOException {
    openTag("delete");
    writeParameter("name", getComponentName());
    if (version!=null) {
      writeParameter("branch",getBranch());
      writeParameter("revision",getRevision());
      writeParameter("version",getVersion());
    }
    closeTag();
  }

  public String getComponentName() { return componentName; }
  public void setComponentName(String s) { componentName = s; }
  public String getBranch() { return branch; }
  public void setBranch(String s) { branch=s; }
  public String getRevision() { return revision; }
  public void setRevision(String s) { revision=s; }
  public String getVersion() { return version; }
  public void setVersion(String s) { version=s; }

  private String componentName=null;
  private String branch=null;
  private String revision=null;
  private String version=null;
}
