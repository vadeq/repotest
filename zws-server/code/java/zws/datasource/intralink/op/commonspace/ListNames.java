package zws.datasource.intralink.op.commonspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.datasource.intralink.xml.ListNamesHandler;
import zws.datasource.intralink.xml.IntralinkResultHandler;

import java.io.*;

public class ListNames extends IntralinkRepositoryOp {
  public IntralinkResultHandler getXMLResultHandler() { return new ListNamesHandler(); }
  protected void writeRepositoryInstruction() throws IOException {
    openTag("list-names");
    closeTag();
  }
}
