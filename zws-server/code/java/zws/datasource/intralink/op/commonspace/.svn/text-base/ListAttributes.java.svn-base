package zws.datasource.intralink.op.commonspace; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Apr 12, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.datasource.intralink.xml.ListAttributesHandler;
import zws.datasource.intralink.xml.IntralinkResultHandler;
import java.io.IOException;

public class ListAttributes extends IntralinkRepositoryOp {
  public IntralinkResultHandler getXMLResultHandler() { return new ListAttributesHandler(); }
    
  protected void writeRepositoryInstruction() throws IOException {
    openTag("list-attributes");
    closeTag();
  }
}
